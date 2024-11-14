package culturemedia.controller;

import java.util.*;

import culturemedia.exception.VideoNotFoundException;
import culturemedia.model.Video;
import culturemedia.service.CultureMediaService;
import org.springframework.web.bind.annotation.*;


@RestController
public class CultureMediaController {

    private final CultureMediaService cultureMediaService;

    public CultureMediaController(CultureMediaService cultureMediaService) {
        this.cultureMediaService = cultureMediaService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/videos")
    public List<Video> findAllVideos() throws VideoNotFoundException {
        List<Video> videos = cultureMediaService.findAll();
        return videos;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/videos")
    public Video save(@RequestBody Video video){
        cultureMediaService.save(video);
        return video;
    }
}