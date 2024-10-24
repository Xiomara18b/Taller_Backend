package culturemedia.service;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import culturemedia.model.Video;
import culturemedia.service.impl.CultureMediaServiceImpl;
import culturemedia.repository.impl.VideoRepositoryImpl;
import culturemedia.exception.VideoNotFoundException;

public class CultureMediaServiceTest {
    private CultureMediaServiceImpl cultureMediaService;

    @BeforeEach
    void init() {
        cultureMediaService = new CultureMediaServiceImpl();
    }

    void saveVideos() {

        List<Video> videos = List.of(new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4),
                new Video("04", "Título 4", "----", 3.5),
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1));


        for (Video video : videos) {
            cultureMediaService.save(video);
        }
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        saveVideos();
        List<Video> videos = cultureMediaService.findAll();
        assertEquals(6, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() throws VideoNotFoundException {
        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> cultureMediaService.findAll());
        assertEquals("¡No video found!", exception.getMessage());
    }
}
