package com.javafx_voltech_cps.cameramonitoringapp.model.entity;

import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.CamView;

import java.util.ArrayList;
import java.util.List;

public class Recorder {
    private int id;
    private String name;
    private String localizacao;
    private List<Camera> cameras;

    // Construtor, getters e setters
    public Recorder(int id, String name, String localizacao) {
        this.id = id;
        this.name = name;
        this.localizacao = localizacao;
        cameras = new ArrayList<>();
    }
    public Recorder(){
        cameras = new ArrayList<>();
        this.name = "GRAVADOR";
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }
}