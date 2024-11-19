package com.clipicate.server.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFprobe;

@Configuration
public class FFmpegConfig {

    @Bean
    public FFmpeg ffmpeg() throws IOException {
        return new FFmpeg(getFFmpegPath());
    }

    @Bean
    public FFprobe ffprobe() throws IOException {
        return new FFprobe(getFFprobePath());
    }

    public String getFFmpegPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String ffmpegPath;

        if (os.contains("win")) {
            ffmpegPath = "src/main/resources/win/ffmpeg.exe";
        } else if (os.contains("mac")) {
            ffmpegPath = "src/main/resources/mac/ffmpeg";
        } else {
            throw new IllegalArgumentException("Sistema operacional não identificado");
        }

        return ffmpegPath;
    }

    public String getFFprobePath() {
        return getFFmpegPath().replace("ffmpeg", "ffprobe");
    }
}
