package com.javafx_voltech_cps.cameramonitoringapp.model.entity;

public class Camera {
	private int id;
	private String source;
	private String nome;
	private String ip;
	private boolean principal;

	// Construtor, getters e setters
	public Camera(int id, String nome, String ip) {
		this.id = id;
		this.nome = nome;
		this.ip = ip;
	}

	public Camera(){}

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
		return "rtsp://admin:admin@127.0.0.1:554/live";
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isPrincipal() {
		return true;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
}