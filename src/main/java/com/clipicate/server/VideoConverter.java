package com.clipicate.server;
import java.io.File;
import java.util.concurrent.TimeUnit;

import net.bramp.ffmpeg.*;
import net.bramp.ffmpeg.builder.*;
import net.bramp.ffmpeg.probe.*;

public class VideoConverter {

    private final FFmpeg ffmpeg;
    private final FFprobe ffprobe;
    private final DatabaseHelper dbHelper;

    public VideoConverter(String ffmpegPath, String ffprobePath, DatabaseHelper dbHelper) throws Exception {
        this.ffmpeg = new FFmpeg(ffmpegPath);
        this.ffprobe = new FFprobe(ffprobePath);
        this.dbHelper = dbHelper;
    }

    public File converter(File videoFile) {
        try {
            String inputFile = videoFile.getPath();
            String outputFile = inputFile.replace(".mp4", ".gif");

            FFmpegProbeResult probeResult = ffprobe.probe(inputFile);

            FFmpegBuilder builder = new FFmpegBuilder()
                    .setInput(probeResult) // Arquivo de entrada em mp4
                    .overrideOutputFiles(true) // Sobrescreve o arquivo de saída se já existir
                    .addOutput(outputFile) // Arquivo de saida em gif
                    .setFormat("gif") // Define o formato em gif
                    .setVideoFrameRate(10) // Define a taxa de quadros
                    .setVideoResolution(320, 240) // Define a resolução de saida
                    .setDuration(10,TimeUnit.SECONDS)
                    .done();
                        
            FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
            executor.createJob(builder).run();

            dbHelper.insertGif(videoFile.getName(), outputFile);

            return new File(outputFile);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}