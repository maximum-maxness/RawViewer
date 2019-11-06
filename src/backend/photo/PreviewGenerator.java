package backend.photo;

import backend.FileHelper;
import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Metadata;
import runner.Main;

import java.io.*;

public class PreviewGenerator {
  public static void generatePreview(String pathToPhoto, String folderName)
      throws IOException, InterruptedException {
    if (!previewAlreadyGenerated(pathToPhoto, folderName)) {
      String os = System.getProperty("os.name").substring(0, 3).toLowerCase();
      switch (os) {
        case "win":
          String pathToPhotoQ = "\"" + pathToPhoto + "\"";
          System.out.println(pathToPhotoQ);
          Process p;
          p =
              Runtime.getRuntime()
                  .exec(
                      "cmd /c .\\src\\scripts\\bin\\exiv2win.exe -eXp3 -l .\\cache\\"
                          + folderName
                          + " "
                          + pathToPhotoQ);
          p.waitFor();
          BufferedReader reader=new BufferedReader(new InputStreamReader(
                  p.getInputStream()));
          String line;
          while((line = reader.readLine()) != null) {
            System.out.println(line);
          }
      }
    }
    String previewPath = getPreviewPathFromRawPath(pathToPhoto, folderName);
    Photo photo;
    try {
      photo = new Photo(previewPath, ImageMetadataReader.readMetadata(new File(pathToPhoto)));
    } catch (ImageProcessingException e) {
      e.printStackTrace();
      photo = new Photo(previewPath);
    }
    Main.cs.addPhotoToCurrentList(photo);
  }

  public static boolean previewAlreadyGenerated(String pathToPhoto, String folderName) {
    String cachedPhotoPath = getPreviewPathFromRawPath(pathToPhoto, folderName);
    return new File(cachedPhotoPath).exists();
  }

  private static String getPreviewPathFromRawPath(String pathToPhoto, String folderName) {
    String photoName = FileHelper.getFolderName(pathToPhoto);
    photoName = photoName.substring(0, photoName.length() - 4);
    photoName = photoName + "-preview3.jpg";
    String cachedPhotoPath = ".\\cache\\" + folderName + "\\" + photoName;
    return cachedPhotoPath;
  }
}
