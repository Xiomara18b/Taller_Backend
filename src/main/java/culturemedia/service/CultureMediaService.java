package culturemedia.service;

import java.util.List;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.exception.VideoNotFoundException;

public interface CultureMediaService {
    List<Video> findAll() throws VideoNotFoundException;
    Video save(Video video);
    View save(View view);
    List<Video> find(String title) throws VideoNotFoundException;
    List<Video> find(double minDuration, double maxDuration) throws VideoNotFoundException;
}
