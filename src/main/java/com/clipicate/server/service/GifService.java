package com.clipicate.server.service;
import com.clipicate.server.classes.Gif;
import com.clipicate.server.repository.GifRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GifService {

    @Autowired
    private GifRepository gifRepository;

    public Gif getById(Long id){
        return gifRepository.findById(id).orElse(null);
    }

    public List<Gif> getAllGifs() {
        return gifRepository.findAll();
    }

    public void addGif(Gif gif) {
        gifRepository.save(gif);
    }

    public void deleteGif(Long id){
        Gif gif = getById(id);
        gifRepository.delete(gif);
    }
}
