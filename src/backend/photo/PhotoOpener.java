package backend.photo;

public class PhotoOpener {
    public static Photo openPhoto(String pathToPhoto){
        Photo photo = new Photo(pathToPhoto);
        return photo;
    }
}
