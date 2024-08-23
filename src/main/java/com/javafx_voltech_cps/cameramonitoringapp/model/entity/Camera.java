package com.javafx_voltech_cps.cameramonitoringapp.model.entity;

public class Camera {
	private int id;
	private String nome;
	private String ip;

	// Construtor, getters e setters
	public Camera(int id, String nome, String ip) {
		this.id = id;
		this.nome = nome;
		this.ip = ip;
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIp() {
		return ip;
	}

	public String getSource() {
		return "source rtsp";
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}