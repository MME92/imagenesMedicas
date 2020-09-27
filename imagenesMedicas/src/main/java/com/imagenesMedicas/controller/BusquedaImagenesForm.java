package com.imagenesMedicas.controller;

import org.springframework.data.domain.Page;

import com.imagenesMedicas.modelo.Serie;

public class BusquedaImagenesForm {

	private String personalId;
	
	private Integer numeroFotos;
	
	private Page<Serie> seriesPaciente;
	
	public BusquedaImagenesForm() {
		
	}

	/**
	 * @return the patientId
	 */
	public String getPersonalId() {
		return personalId;
	}

	/**
	 * @param patientId the patientId to set
	 */
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	/**
	 * @return the numeroFotos
	 */
	public Integer getNumeroFotos() {
		return numeroFotos;
	}

	/**
	 * @param numeroFotos the numeroFotos to set
	 */
	public void setNumeroFotos(Integer numeroFotos) {
		this.numeroFotos = numeroFotos;
	}

	/**
	 * @return the seriesPaciente
	 */
	public Page<Serie> getSeriesPaciente() {
		return seriesPaciente;
	}

	/**
	 * @param seriesPaciente the seriesPaciente to set
	 */
	public void setSeriesPaciente(Page<Serie> seriesPaciente) {
		this.seriesPaciente = seriesPaciente;
	}

	
}
