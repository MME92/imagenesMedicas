package com.imagenesMedicas.controller;


public class SerieForm {


	/** Id del paciente al que hace referencia la serie. */
	private String patient_id;
	
	/** Atributo study_id de la tabla Serie. */
	private String studyId;
	
	/** Carpeta del estudio. */
	private String requestStudy;
	
	/** Atributo patient_id de la tabla Serie. */
	private String personalId;
	
	/** Atributo series_id de la tabla Serie. */
	private String seriesId;
	
	/** Atributo modality de la tabla Serie. */
	private String modality;
	
	/** Atributo numeroFotos de la tabla Serie. */
	private Integer numeroFotos;
	
	/** Atributo descripcion de la tabla Serie. */
	private String descripcion;
	
	/** Atributo Url donde se encuentra la serie. */
	private String url;
	
	public SerieForm() {
		
	}

	public SerieForm(String patient_id, String studyId, String patientId, String request_study, String seriesId, String modality,
			Integer numeroFotos, String descripcion, String url) {
		
		this.setPatient_id(patient_id);
		this.setRequestStudy(request_study);
		this.setStudyId(studyId);
		this.setPersonalId(patientId);
		this.setSeriesId(seriesId);
		this.setModality(modality);
		this.setNumeroFotos(numeroFotos);
		this.setDescripcion(descripcion);
		this.setUrl(url);
		
	}

	/**
	 * @return the patient_id
	 */
	public String getPatient_id() {
		return patient_id;
	}

	/**
	 * @param patient_id the patient_id to set
	 */
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}

	/**
	 * @return the studyId
	 */
	public String getStudyId() {
		return studyId;
	}

	/**
	 * @param studyId the studyId to set
	 */
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	/**
	 * @return the requestStudy
	 */
	public String getRequestStudy() {
		return requestStudy;
	}

	/**
	 * @param requestStudy the requestStudy to set
	 */
	public void setRequestStudy(String requestStudy) {
		this.requestStudy = requestStudy;
	}

	/**
	 * @return the personalId
	 */
	public String getPersonalId() {
		return personalId;
	}

	/**
	 * @param personalId the personalId to set
	 */
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	/**
	 * @return the seriesId
	 */
	public String getSeriesId() {
		return seriesId;
	}

	/**
	 * @param seriesId the seriesId to set
	 */
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	/**
	 * @return the modality
	 */
	public String getModality() {
		return modality;
	}

	/**
	 * @param modality the modality to set
	 */
	public void setModality(String modality) {
		this.modality = modality;
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
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
}