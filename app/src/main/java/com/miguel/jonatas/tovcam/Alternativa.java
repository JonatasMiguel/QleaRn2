package com.miguel.jonatas.tovcam;

import java.io.Serializable;

public class Alternativa implements Serializable{
	private String texto;//talvez criar um limite
	private String status;
	public Alternativa() {
		super();
	}

	public Alternativa(String texto, String status) {
		super();
		this.texto = texto;
		this.status = status;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
