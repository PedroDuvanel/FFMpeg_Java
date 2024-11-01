package com.clipicate.server;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        try {
            String ffmpegPath = "C:\\Users\\Pedro Duvanel\\Documents\\curso full stack senai\\server\\src\\main\\resources\\ffmpeg.exe";
            String ffprobePath = "C:\\Users\\Pedro Duvanel\\Documents\\curso full stack senai\\server\\src\\main\\resources\\ffprobe.exe";

            File videoFile = new File("C:\\Users\\Pedro Duvanel\\Documents\\curso full stack senai\\server\\src\\main\\resources\\testeclipicate.mp4");
            
            DatabaseHelper dbHelper = new DatabaseHelper();
            
            VideoConverter videoConverter = new VideoConverter(ffmpegPath, ffprobePath, dbHelper);

            File gifFile = videoConverter.converter(videoFile);

            if (gifFile != null && gifFile.exists()) {
                System.out.println("GIF criado: " + gifFile.getAbsolutePath());
            } else {
                System.out.println("Falha ao criar GIF");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
