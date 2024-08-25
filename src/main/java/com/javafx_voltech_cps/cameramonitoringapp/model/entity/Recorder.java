package com.javafx_voltech_cps.cameramonitoringapp.model.entity;

import com.javafx_voltech_cps.cameramonitoringapp.view.custom_elements.CamView;

import java.util.ArrayList;
import java.util.List;

public class Recorder {
    private int id;
    private String nome;
    private String localizacao;
    private List<Camera> cameras;

    // Construtor, getters e setters
    public Recorder(int id, String nome, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        cameras = new ArrayList<>();
    }
    public Recorder(){
        cameras = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public List<Camera> getCameras() {
        return cameras;
    }

    public void setCameras(List<Camera> cameras) {
        this.cameras = cameras;
    }
}