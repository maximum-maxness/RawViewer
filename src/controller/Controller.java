package controller;

import backend.FileHelper;
import backend.photo.Photo;
import backend.photo.PreviewGenerator;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import runner.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Controller {

  @FXML Button folderChooser;

  @FXML AnchorPane mainPanel;

  @FXML ScrollPane scrollView;

  @FXML
  FlowPane verticalHolder;

  @FXML Label statusLabel;

  @FXML ProgressBar statusBar;

  public Controller() {}

  @FXML
  private void initialize() {
      mainPanel.setPrefWidth(1280);
      mainPanel.setPrefHeight(720);
  }

  @FXML
  private void folderChoose() {
    DirectoryChooser dc = new DirectoryChooser();
    File photoDir = dc.showDialog(Main.firstStage);
    if (photoDir != null) {
      String photoPath = photoDir.getAbsolutePath();
      String folderName = FileHelper.getFolderName(photoPath);
      FileHelper.createCacheFolder(folderName);
      List<String> photoList = FileHelper.getFilesFromFolder(photoPath);
      statusLabel.setText("Processed Photo 0 of " + photoList.size() + " (0%)");
      new Thread(
              () -> {
                int count = 0;
                for (String photo : photoList) {
                  try {
                    PreviewGenerator.generatePreview(photo, folderName);
                    count++;
                    int finalCount = count;
                    Platform.runLater(
                        () -> {
                          int percent = (int)(((finalCount / 1.0)  / (photoList.size() / 1.0)) * 100);
                          statusLabel.setText(
                              "Processed Photo "
                                  + finalCount
                                  + " of "
                                  + photoList.size()
                                  + " ("
                                  + percent
                                  + "%)");
                          statusBar.setProgress(percent/100.0);
                        });
                  } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                  }
                }
                Platform.runLater(this::doneLoading);
              })
          .start();
    }
  }

  private void doneLoading(){
      statusLabel.setText("Done!");
      for(Photo photo : Main.cs.currentPhotoList){
          try {
              ImageView view = new ImageView();
              Image displayImage = new Image(new FileInputStream(photo), 300, 200, false, false);
              view.setImage(displayImage);
              verticalHolder.getChildren().add(view);
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }
      }
  }
}
