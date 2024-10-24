package culturemedia.service.impl;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.service.CultureMediaService;
import culturemedia.exception.VideoNotFoundException;
import java.util.stream.Collectors;

import java.util.ArrayList;
import java.util.List;

public class CultureMediaServiceImpl implements CultureMediaService {
    private final List<Video> videos = new ArrayList<>();
    private final List<View> views = new ArrayList<>();

    @Override
    public List<Video> findAll() throws VideoNotFoundException {
        if (videos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return videos;
    }

    @Override
    public Video save(Video video){
        this.videos.add(video);
        return video;
    }

    @Override
    public View save(View view){
        this.views.add(view);
        return view;
    }

    @Override
    public List<Video> find(String title) throws VideoNotFoundException {
        List<Video> result = videos.stream()
                .filter(video -> video.title().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return result;
    }

    @Override
    public List<Video> find(double minDuration, double maxDuration) throws VideoNotFoundException {
        List<Video> filteredVideos = new ArrayList<Video>();
        for (Video video : videos) {
            if (video.duration() > minDuration && video.duration() < maxDuration) {
                filteredVideos.add(video);
            }
        }
        if (filteredVideos.isEmpty()) {
            throw new VideoNotFoundException();
        }
        return filteredVideos;
    }
}
