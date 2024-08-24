package com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class CamMonitoring extends ScrollPane {

    private VBox vbox;
    private List<CamView> cameras;

    public CamMonitoring() {
        vbox = new VBox();
        vbox.setPadding(new Insets(0));
        vbox.setSpacing(5);
        setContent(vbox);
        setFitToHeight(false);
        vbox.setFillWidth(true);
        setHbarPolicy(ScrollBarPolicy.NEVER);
        widthProperty().addListener((obs, oldWidth, newWidth) -> updateImageViewsWidth());
        viewportBoundsProperty().addListener((obs, oldBounds, newBounds) -> updateImageViewsWidth());
    }

    public void loadFirstCamera(CamView firstCamera) {
        ImageView imageView = firstCamera.getView();
        imageView.setPreserveRatio(true);  // Mantém a proporção da imagem
        VBox.setVgrow(imageView, Priority.ALWAYS);  // Permite que o VBox cresça com o conteúdo
        HBox hbox = new HBox(imageView);
        hbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hbox);
        updateImageViewsWidth();
    }

    public void loadCameraGrid() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(0));
        gridPane.setHgap(5); // Espaçamento horizontal entre as colunas
        gridPane.setVgap(5); // Espaçamento vertical entre as linhas
        int column = 0;
        int row = 0;
        for (int i = 1; i < cameras.size(); i++) {
            CamView camView = cameras.get(i);
            ImageView imageView = camView.getView();
            imageView.setPreserveRatio(true);
            GridPane.setVgrow(imageView, Priority.ALWAYS);
            GridPane.setHgrow(imageView, Priority.ALWAYS);
            gridPane.add(imageView, column, row);
            column++;
            if (column == 2) {  // Se atingiu o número de colunas, avança para a próxima linha
                column = 0;
                row++;
            }
        }
        vbox.getChildren().add(gridPane);
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

    public List<CamView> getCameras() {
        return cameras;
    }

    public void setCameras(List<CamView> cameras) {
        this.cameras = cameras;
    }
}
