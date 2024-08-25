package com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements;

import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CamMonitoring extends ScrollPane {
    private Recorder recorder;
    private VBox vbox;
    private HBox hbox;
    private List<CamView> camViews;
    private GridPane gridPane;

    public CamMonitoring() {
        camViews = new ArrayList<>() {
            @Override
            public CamView removeLast() {
                CamView camRemoved = super.removeLast();
                if (camRemoved.isRunning()) {
                    camRemoved.cancel();
                }
                return null;
            }
        };

        vbox = new VBox();
        hbox = new HBox();
        vbox.setPadding(new Insets(0));
        vbox.setSpacing(5);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hbox);

        gridPane = new GridPane();
        gridPane.setPadding(new Insets(0));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        vbox.getChildren().add(gridPane);

        setContent(vbox);
        setFitToHeight(false);
        vbox.setFillWidth(true);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        widthProperty().addListener((obs, oldWidth, newWidth) -> updateImageViewsWidth());
        viewportBoundsProperty().addListener((obs, oldBounds, newBounds) -> updateImageViewsWidth());
    }

    public void loadPrincipalCamera(CamView firstCamera) {
        ImageView imageView = firstCamera.getView();
        imageView.setPreserveRatio(true);
        VBox.setVgrow(imageView, Priority.ALWAYS);
        hbox.getChildren().removeAll();
        hbox.getChildren().add(imageView);
        updateImageViewsWidth();
    }

    private void loadCameras() throws Exception {
        if (recorder != null) {
            int numberOfCameras = recorder.getCameras().size();
            while (camViews.size() > numberOfCameras) {
                camViews.removeLast();
            }
            for (int i = 0; i < numberOfCameras; i++) {
                if (i < camViews.size()) {
                    camViews.get(i).setCamera(recorder.getCameras().get(i));
                } else {
                    CamView camView = new CamView();
                    camView.setCamera(recorder.getCameras().get(i));
                    camViews.add(camView);
                }
            }
        } else {
            throw new Exception("Record is null");
        }
    }

    private void loadGrid() throws Exception {
        gridPane.getChildren().clear();
        int column = 0;
        int row = 0;
        for (int i = 0; i < camViews.size(); i++) {
            CamView camView = camViews.get(i);
            if (camView.getCamera().isPrincipal()) {
                loadPrincipalCamera(camView);
            } else {
                ImageView imageView = camView.getView();
                imageView.setPreserveRatio(true);
                GridPane.setVgrow(imageView, Priority.ALWAYS);
                GridPane.setHgrow(imageView, Priority.ALWAYS);
                gridPane.add(imageView, column, row);
                column++;
                if (column == 2) {
                    column = 0;
                    row++;
                }
            }
        }
        updateImageViewsWidth();
    }

    private void updateImageViewsWidth() {
        double availableWidth = getViewportBounds().getWidth();
        for (var child : vbox.getChildren()) {
            if (child instanceof HBox) {
                for (var hboxChild : ((HBox) child).getChildren()) {
                    if (hboxChild instanceof ImageView) {
                        ((ImageView) hboxChild).setFitWidth(availableWidth - 2);
                    }
                }
            }
            if (child instanceof GridPane) {
                GridPane gridPane = (GridPane) child;
                for (var node : gridPane.getChildren()) {
                    if (node instanceof ImageView) {
                        ((ImageView) node).setFitWidth((availableWidth - 10) / 2 - 2);  // Divide a largura disponível pelas colunas
                    }
                }
            }
        }
    }

    private void messageError(Exception e) {
        e.printStackTrace();
    }

    public void start() throws Exception {
        for(CamView camView : camViews){
            if(camView.getVideoState() != CamView.PLAY){
                camView.play();
            }
        }
    }

    public Recorder getRecorder() {
        return recorder;
    }

    public void setRecorder(Recorder recorder) throws Exception {
        this.recorder = recorder;
        loadCameras();
        loadGrid();
    }
}
