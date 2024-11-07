package culturemedia.service.impl;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.service.CultureMediaService;
import culturemedia.exception.VideoNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CultureMediaServiceImpl implements CultureMediaService {
    VideoRepository videoRepository;
    ViewsRepository viewsRepository;

    public CultureMediaServiceImpl(VideoRepository videorepository, ViewsRepository viewsrepository) {
        this.videoRepository = videorepository;
        this.viewsRepository = viewsrepository;
    }

    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        List<Video> videos = this.videoRepository.findAll();
        if (videos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return videos;
    }

    @Override
    public Video save(Video video){
        this.videoRepository.save(video);
        return video;
    }

    @Override
    public View save(View view){
        this.viewsRepository.save(view);
        return view;
    }

    @Override
    public List<Video> find(String title) throws VideoNotFoundException {
        List<Video> videos = this.videoRepository.find(title);
        if (videos.isEmpty()) {
            throw new VideoNotFoundException("¡No video found!");
        }
        return videos;
    }

    @Override
    public List<Video> find(double minDuration, double maxDuration) throws VideoNotFoundException {
        List<Video> videos = this.videoRepository.find(minDuration, maxDuration);
        if (videos.isEmpty()) {
            throw new VideoNotFoundException("¡No video found!");
        }
        return videos;
    }
}
