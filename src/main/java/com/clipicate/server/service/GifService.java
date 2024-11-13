package com.clipicate.server.service;
import com.clipicate.server.service.Converter;
import com.clipicate.server.classes.Gif;
import com.clipicate.server.repository.GifRepository;
import lombok.Data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.bramp.ffmpeg.*;
import net.bramp.ffmpeg.builder.*;
import net.bramp.ffmpeg.probe.*;

@Data
@Service
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

    // public void addGif(Gif gif) {
    //     byte[] gifConverted =
    //         converter.toBase64(
    //             converter.fromVideoToGif(
    //                 converter.fromBase64(gif.getFile64())
    //             )
    //         );
    //     gif.setFile64(gifConverted);
    //     gifRepository.save(gif);
    // }

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
        File gifFile = converter.fromVideoToGif(videoFile);

        // Converte o gif para salvar no banco
        byte[] gifData = converter.toBase64(gifFile);

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