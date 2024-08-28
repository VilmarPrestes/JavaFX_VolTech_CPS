package com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements;

import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Camera;
import com.javafx_voltech_cps.cameramonitoringapp.model.entity.Recorder;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecorderTreeView extends TreeView<String> {

    private static final String RECORDER_ICON_PATH = "/com/javafx_voltech_cps/cameramonitoringapp/images/recorder.png";
    private static final String CAMERA_ICON_PATH = "/com/javafx_voltech_cps/cameramonitoringapp/images/camera.png";

    private final TreeItem<String> rootItem;
    private final Map<Recorder, TreeItem<String>> recorderMap;

    public RecorderTreeView() {
        rootItem = new TreeItem<>("Recorders");
        rootItem.setExpanded(true);
        setRoot(rootItem);
        setShowRoot(false);
        recorderMap = new HashMap<>();
    }

    public void addRecorder(Recorder recorder) {
        if (!recorderMap.containsKey(recorder)) {
            TreeItem<String> recorderItem = new TreeItem<>(recorder.getName(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(RECORDER_ICON_PATH)))));
            recorderMap.put(recorder, recorderItem);
            sortList(recorder, recorderItem);
            insertRecorderItemInOrder(recorderItem);
        }
    }

    public void removeRecorder(Recorder recorder) {
        TreeItem<String> recorderItem = recorderMap.remove(recorder);
        if (recorderItem != null) {
            rootItem.getChildren().remove(recorderItem);
        }
    }

    public void updateRecorder(Recorder recorder) {
        TreeItem<String> recorderItem = recorderMap.get(recorder);
        if (recorderItem != null) {
            recorderItem.getChildren().clear();
            sortList(recorder, recorderItem);
        } else {
            addRecorder(recorder);
        }
    }

    private void sortList(Recorder recorder, TreeItem<String> recorderItem) {
        List<Camera> sortedCameras = recorder.getCameras().stream()
                .sorted((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()))
                .toList();
        for (Camera camera : sortedCameras) {
            TreeItem<String> cameraItem = new TreeItem<>(camera.getName(), new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream(CAMERA_ICON_PATH)))));
            recorderItem.getChildren().add(cameraItem);
        }
    }

    private void insertRecorderItemInOrder(TreeItem<String> recorderItem) {
        String recorderName = recorderItem.getValue();
        int index = 0;
        for (TreeItem<String> item : rootItem.getChildren()) {
            if (recorderName.compareToIgnoreCase(item.getValue()) < 0) {
                break;
            }
            index++;
        }
        rootItem.getChildren().add(index, recorderItem);
    }
}
