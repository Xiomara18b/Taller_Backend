package culturemedia.service;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import culturemedia.model.Video;
import culturemedia.service.impl.CultureMediaServiceImpl;
import culturemedia.repository.VideoRepository;
import culturemedia.repository.ViewsRepository;
import culturemedia.exception.VideoNotFoundException;

public class CultureMediaServiceTest {

    private CultureMediaService cultureMediaService;

    @Mock
    private VideoRepository videoRepository;
    @Mock
    private ViewsRepository viewsRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        cultureMediaService = new CultureMediaServiceImpl(videoRepository, viewsRepository);
    }

    @Test
    void when_FindAll_all_videos_should_be_returned_successfully() throws VideoNotFoundException {
        doReturn(List.of(new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4),
                new Video("04", "Título 4", "----", 3.5),
                new Video("05", "Clic 5", "----", 5.7),
                new Video("06", "Clic 6", "----", 5.1))).when(videoRepository).findAll();
        List<Video> videos = cultureMediaService.findAll();
        assertEquals(6, videos.size());
    }

    @Test
    void when_FindAll_does_not_find_any_video_an_VideoNotFoundException_should_be_thrown_successfully() {
        doReturn(new ArrayList<>()).when(videoRepository).findAll();

        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> cultureMediaService.findAll());
        assertEquals("¡No video found!", exception.getMessage());
    }

   @Test
   void when_Video_is_saved_it_should_be_added_to_the_list_successfully() {
       Video video = new Video("07", "Nuevo Video", "----", 6.2);
       doReturn(video).when(videoRepository).save(video);

       Video savedVideo = cultureMediaService.save(video);

       assertEquals("Nuevo Video", savedVideo.title());
       verify(videoRepository).save(video);
   }

    @Test
    void when_FindByTitle_only_videos_which_contains_the_word_in_the_title_should_be_returned_successfully() throws VideoNotFoundException {
        List<Video> videos = List.of(
                new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5),
                new Video("03", "Título 3", "----", 4.4));

        doReturn(videos).when(videoRepository).find("Título");

        List<Video> result = cultureMediaService.find("Título");

        assertEquals(3, result.size());
    }

   @Test
   void when_FindByTitle_only_videos_which_contains_the_word_in_the_title_should_be_returned_an_VideoNotFoundException() {
       doReturn(new ArrayList<>()).when(videoRepository).find("inexistente");

       VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () -> cultureMediaService.find("inexistente"));
       assertEquals("¡No video found!", exception.getMessage());
   }

    @Test
    void when_FindByDuration_only_videos_between_the_range_should_be_returned_successfully() throws VideoNotFoundException {
        List<Video> videos = List.of(
                new Video("01", "Título 1", "----", 4.5),
                new Video("02", "Título 2", "----", 5.5)
        );

        doReturn(videos).when(videoRepository).find(4.0, 5.0);

        List<Video> result = cultureMediaService.find(4.0, 5.0);

        assertEquals(2, result.size());
    }

    @Test
    void when_FindByDuration_only_videos_between_the_range_should_be_returned_an_VideoNotFoundException() {
        doReturn(new ArrayList<>()).when(videoRepository).find(6.5, 7.0);

        VideoNotFoundException exception = assertThrows(VideoNotFoundException.class, () ->
                cultureMediaService.find(6.5, 7.0));
        assertEquals("¡No video found!", exception.getMessage());
    }
}