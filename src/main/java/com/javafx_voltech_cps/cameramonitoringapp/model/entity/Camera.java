package com.javafx_voltech_cps.cameramonitoringapp.model.entity;

public class Camera {
	private int id;
	private String source;
	private String name;
	private String ip;
	private boolean principal;

	// Construtor, getters e setters
	public Camera(int id, String nome, String ip) {
		this.id = id;
		this.name = name;
		this.ip = ip;
	}

	public Camera(){
		this.name = "CAMERA";
	}

	// Getters e Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public String getSource() {
		return "rtsp://admin:FAMILIA19@192.168.0.108:554/cam/realmonitor?channel=1&subtype=0";
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public boolean isPrincipal() {
		return principal;
	}

	public void setPrincipal(boolean principal) {
		this.principal = principal;
	}
}