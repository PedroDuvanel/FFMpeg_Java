package com.clipicate.server.service;
import com.clipicate.server.Gif;
import com.clipicate.server.repository.GifRepository;

import java.util.List;

public class GifService {

    private GifRepository gifRepository;

    public GifService() {
        this.gifRepository = new GifRepository();
        this.gifRepository.createTableIfNotExists();
    }

    public void addGif(Gif gif) {
        gifRepository.insertGif(gif);
    }

    public List<Gif> getAllGifs() {
        return gifRepository.listGifs();
    }

    public void removeAllGifs() {
        gifRepository.deleteGifs();
    }
}
