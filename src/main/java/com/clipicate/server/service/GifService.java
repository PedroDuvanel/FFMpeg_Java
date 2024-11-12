package com.clipicate.server.service;
import com.clipicate.server.service.Converter;
import com.clipicate.server.classes.Gif;
import com.clipicate.server.repository.GifRepository;
import lombok.Data;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public void addGif(Gif gif) {
        byte[] gifConverted =
            converter.toBase64(
                converter.fromVideoToGif(
                    converter.fromBase64(gif.getFile64())
                )
            );
        gif.setFile64(gifConverted);
        gifRepository.save(gif);
    }

    public void deleteGif(Long id){
        Gif gif = getById(id);
        gifRepository.delete(gif);
    }
}