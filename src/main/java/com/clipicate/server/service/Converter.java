package com.clipicate.server.service;

import java.io.*;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;

@Service 
public class Converter {

    public File fromBase64(String data) {

        byte[] dataBytes = DatatypeConverter.parseBase64Binary(data);

        String path = "src/main/resources/novovideo.mov";
        File file = new File(path);
        
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(dataBytes);
        } catch (IOException e1) {
            e1.printStackTrace();
		}

        return file;
    }

    public byte[] toBase64(File file) {
        byte[] dataBytes = new byte[20000000];

        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
            inputStream.read(dataBytes);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        // String data = DatatypeConverter.printBase64Binary(dataBytes);

        return dataBytes;
    }

    public File fromVideoToGif(File videoFile) {

        String ffmpegPath = "src/main/resources/ffmpeg.exe";
        String ffprobePath = "src/main/resources/ffprobe.exe";
        
        try {
            // File videoFile = new File(path);
            
            VideoConverter videoConverter = new VideoConverter(ffmpegPath, ffprobePath);

            File gifFile = videoConverter.converter(videoFile);

            if (gifFile != null && gifFile.exists()) {
                System.out.println("GIF criado: " + gifFile.getAbsolutePath());
                return gifFile;
            } else {
                throw new RuntimeException("Falha ao criar GIF");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException ("Erro durante a conversão");
        }

    }
}