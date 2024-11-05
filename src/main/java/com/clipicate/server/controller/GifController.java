package com.clipicate.server.controller;
import com.clipicate.server.classes.Gif;
import com.clipicate.server.service.GifService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class GifController{

    @Autowired
    private GifService gifService;

    // public GifController() {
    //     this.gifService = new GifService();
    // }

    // public void addGif(String name, String path) {
    //     Gif gif = new Gif(0, name, path, null); 
    // }

    @GetMapping
    public ResponseEntity<List<Gif>> getAll(){
        List<Gif> gifs = gifService.getAllGifs();
        return ResponseEntity.ok(gifs);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Gif> getById(@PathVariable Long id){
        Gif gif = gifService.getById(id);
        if(gif == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gif);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Gif gif){
        gifService.addGif(gif);
        return ResponseEntity.ok(gif);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        gifService.deleteGif(id);
        return ResponseEntity.noContent().build();
    }
}