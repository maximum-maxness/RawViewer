package controller;

import backend.photo.Photo;

import java.util.ArrayList;
import java.util.List;

public class CurrentSession {
    public List<Photo> currentPhotoList;
    public String currentFolderPath;

    public CurrentSession(){
        currentPhotoList = new ArrayList<>();
    }

    public void addPhotoToCurrentList(Photo photo){
        currentPhotoList.add(photo);
    }

    public void setCurrentFolderPath(String path){
        this.currentFolderPath = path;
    }

}
