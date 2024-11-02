package com.clipicate.server;

import java.io.*;
import java.util.Scanner;
import javax.xml.bind.DatatypeConverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        String data = "";

        try {
        File myObj = new File("src/main/resources/teste64.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            data += myReader.nextLine();
            System.out.println(data);
        }
        myReader.close();
        } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }

        byte[] dataBytes = DatatypeConverter.parseBase64Binary(data);
        String newVideoPath = "src/main/resources/novovideo.mp4";
        File file = new File(newVideoPath);
        
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(dataBytes);
        } catch (IOException e1) {
        // TODO Auto-generated catch block
            e1.printStackTrace();
		}


        try {
            // String ffmpegPath = "C:\\Users\\Pedro Duvanel\\Documents\\curso full stack senai\\server\\src\\main\\resources\\ffmpeg.exe";
            String ffmpegPath = "src/main/resources/ffmpeg.exe";
            String ffprobePath = "src/main/resources/ffprobe.exe";

            // File videoFile = new File("src/main/resources/testeclipicate.mp4");
            File videoFile = new File("src/main/resources/novovideo.mp4");
            
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
