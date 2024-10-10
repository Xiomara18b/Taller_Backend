package culturemedia.exception;

public class VideoNotFoundException extends CultureMediaException{
    public VideoNotFoundException(){
        super("¡No video found!");
    }
    public VideoNotFoundException(String title){
        super(title);
    }
}
