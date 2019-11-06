package backend.photo;

import backend.MetadataScraper;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class Photo extends File {

    public Metadata photoMetadata;

    public Photo(String pathname, Metadata metadata){
        super(pathname);
        this.photoMetadata = metadata;

    }

    public Photo(String pathname) {
        super(pathname);
        try {
            this.photoMetadata = ImageMetadataReader.readMetadata(this);
        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
    }

    public Photo(URI uri) {
        super(uri);
        try {
            this.photoMetadata = ImageMetadataReader.readMetadata(this);
        } catch (ImageProcessingException | IOException e) {
            e.printStackTrace();
        }
    }
}
