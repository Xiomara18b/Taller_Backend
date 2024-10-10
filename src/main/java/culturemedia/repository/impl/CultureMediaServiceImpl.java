package culturemedia.repository.impl;

import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.repository.CultureMediaService;
import culturemedia.exception.CultureMediaException;
import culturemedia.exception.DurationNotValidException;
import culturemedia.exception.VideoNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CultureMediaServiceImpl implements CultureMediaService {
    private final List<Video> videos = new ArrayList<>();
    private final List<View> views = new ArrayList<>();

    @Override
    public List<Video> listAll() {
        return new ArrayList<>(videos);
    }

    @Override
    public Video add(Video video) throws CultureMediaException {
        validateVideoDuration(video);
        videos.add(video);
        return video;
    }

    @Override
    public View add(View view) throws CultureMediaException {
        Optional<Video> videoFound = videos.stream()
                .filter(video -> video.code().equals(view.video().code()))
                .findFirst();

        if (videoFound.isEmpty()) {
            throw new VideoNotFoundException(view.video().title());
        }

        views.add(view);
        return view;
    }

    private void validateVideoDuration(Video video) throws DurationNotValidException {
        if (video.duration() <= 0) {
            throw new DurationNotValidException(video.title(), video.duration());
        }
    }
}
