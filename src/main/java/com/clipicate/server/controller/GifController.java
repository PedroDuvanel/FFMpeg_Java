package com.clipicate.server.controller;
import com.clipicate.server.classes.Gif;
import com.clipicate.server.service.GifService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Autowired;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/gif")
public class GifController{

    @Autowired
    private GifService gifService;

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

    @GetMapping("/gifs")
    public ResponseEntity<List<Gif>> getAllGifs(){
        List<Gif> gifs = gifService.getAllGifs();
        if(gifs == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(gifs);
    }


    // @PostMapping
    // public ResponseEntity<?> create(@RequestBody Gif gif){
    //     gifService.addGif(gif);
    //     return ResponseEntity.ok(gif);
    // }

    @PostMapping("/create-gif")
    public ResponseEntity<?> createNewGif(@RequestParam("file") MultipartFile file){
        System.out.println("requisição feita");
        try{
            Gif createdGif = gifService.createNewGif(file);
            return ResponseEntity.ok(createdGif);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar o vídeo" + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        gifService.deleteGif(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("hello");
    } 
}