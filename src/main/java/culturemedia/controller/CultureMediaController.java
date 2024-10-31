package culturemedia.controller;

import java.util.*;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CultureMediaService;

public class CultureMediaController {

    private final CultureMediaService cultureMediaService;


    public CultureMediaController(CultureMediaService cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }


    public List<Video> findAllVideos() throws VideoNotFoundException {
        List<Video> videos = cultureMediaService.findAll();
        return videos;
    }

    public Video save(Video video){
        cultureMediaService.save(video);
        return video;
    }
}