package com.clipicate.server.controller;
import com.clipicate.server.service.GifService;
import com.clipicate.server.Gif;
import java.util.List;

public class GifController{

    private GifService gifService;

    public GifController() {
        this.gifService = new GifService();
    }

    public void addGif(String name, String path) {
        Gif gif = new Gif(0, name, path, null); 
        gifService.addGif(gif);
    }

    public List<Gif> listGifs() {
        return gifService.getAllGifs();
    }

    public void deleteAllGifs() {
        gifService.removeAllGifs();
    }
}