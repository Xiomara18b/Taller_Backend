package culturemedia.repository;

import java.util.List;
import culturemedia.model.Video;
import culturemedia.model.View;
import culturemedia.exception.CultureMediaException;

public interface CultureMediaService {
    List<Video> listAll();
    Video add(Video video) throws CultureMediaException;
    View add(View view) throws CultureMediaException;

}
