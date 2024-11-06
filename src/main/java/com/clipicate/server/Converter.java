package com.clipicate.server;

import java.io.*;
import javax.xml.bind.DatatypeConverter;

public class Converter {

    public File fromBase64(String data) {

        byte[] dataBytes = DatatypeConverter.parseBase64Binary(data);

        String path = "src/main/resources/novovideo.mp4";
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

    public void fromVideoToGif(File videoFile) {

        try {
            String ffmpegPath = "src/main/resources/ffmpeg.exe";
            String ffprobePath = "src/main/resources/ffprobe.exe";

            // File videoFile = new File(path);
            
            VideoConverter videoConverter = new VideoConverter(ffmpegPath, ffprobePath);

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