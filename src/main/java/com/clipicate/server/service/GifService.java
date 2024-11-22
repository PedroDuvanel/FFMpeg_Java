package com.clipicate.server.service;
import com.clipicate.server.classes.Gif;
import com.clipicate.server.repository.GifRepository;
import lombok.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.bramp.ffmpeg.*;
import net.bramp.ffmpeg.builder.*;
import net.bramp.ffmpeg.probe.*;


@Service
@Data
public class GifService {

    private final FFmpeg ffmpeg;
    private final FFprobe ffprobe;

    @Autowired
    private GifRepository gifRepository;

    @Autowired
    private Converter converter;

    public Gif getById(Long id){
        return gifRepository.findById(id).orElse(null);
    }

    public List<Gif> getAllGifs() {
        return gifRepository.findAll();
    }

    public File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("upload", file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

    public Gif createNewGif(MultipartFile file) throws IOException {
        
        // Converter pra vídeo
        File videoFile = convertMultipartFileToFile(file);

        // Converter pra gif
        FFmpegProbeResult probeResult = ffprobe.probe(videoFile.getAbsolutePath());
        File gifFile = File.createTempFile("upload", ".gif");
        FFmpegBuilder builder = new FFmpegBuilder()
            .setInput(probeResult.getFormat().filename)
            .overrideOutputFiles(true)
            .addOutput(gifFile.getAbsolutePath())
            .setFormat("gif")
            .done();

        ffmpeg.run(builder);

        // Converte o gif para salvar no banco
        byte[] gifData = Files.readAllBytes(gifFile.toPath());

        // Chama a camada de repository 
        Gif gif = new Gif();
        gif.setDescription("");
        gif.setFile64(gifData);

        return gifRepository.save(gif);
    }

    public void deleteGif(Long id){
        Gif gif = getById(id);
        gifRepository.delete(gif);
    }
}