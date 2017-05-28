package com.nbakaev.cityguide.backend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ya on 5/28/2017.
 */
@ApiModel("Цифровой контент")
public class GuideContent {

    @ApiModelProperty("Адрес главной картинки")
    private String imageUrl;

    @ApiModelProperty("Адреса остальных картинок")
    private List<String> imageUrls = new ArrayList<>();

//    @ApiModelProperty("Адреса на видеоконтент, например видео в youtube")
//    private List<String> videoUrls = new ArrayList<>();
//
//    @ApiModelProperty("Адреса на аудиоконтент, например mp3 аудиогиды")
//    private List<String> audioUrls = new ArrayList<>();

    @ApiModelProperty("Адрес на видеоконтент, например видео в youtube")
    private String videoUrl;

    @ApiModelProperty("Адрес на аудиоконтент, например mp3 аудиогиды")
    private String audioUrl;


    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

//    public List<String> getVideoUrls() {
//        return videoUrls;
//    }
//
//    public void setVideoUrls(List<String> videoUrls) {
//        this.videoUrls = videoUrls;
//    }
//
//    public List<String> getAudioUrls() {
//        return audioUrls;
//    }
//
//    public void setAudioUrls(List<String> audioUrls) {
//        this.audioUrls = audioUrls;
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

}
