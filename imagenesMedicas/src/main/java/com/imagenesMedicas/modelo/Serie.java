package com.imagenesMedicas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "serie")
public class Serie {

	/** Id del paciente al que hace referencia la serie. */
	@Column(name = "patient_id")
	private String patient_id;
	
	/** Atributo study_id de la tabla Serie. */
	@Column(name = "study_id")
	private String studyId;
	
	/** Carpeta del estudio. */
	@Column(name = "request_study")
	private String requestStudy;
	
	/** Atributo patient_id de la tabla Serie. */
	@Column(name = "personal_id")
	private String personalId;
	
	/** Atributo series_id de la tabla Serie. */
	@Id	
	@Column(name = "series_id")
	private String seriesId;
	
	/** Atributo modality de la tabla Serie. */
	private String modality;
	
	/** Atributo numeroFotos de la tabla Serie. */
	@Column(name = "numero_fotos")
	private Integer numeroFotos;
	
	/** Atributo descripcion de la tabla Serie. */
	@Column(name = "descricion")
	private String descripcion;
	
	/** Atributo Url donde se encuentra la serie. */
	private String url;

	public Serie() {
		
	}

	public Serie(String patient_id, String studyId, String patientId, String request_study, String seriesId, String modality,
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
	 * Recupera el atributo studyId de la tabla Serie.
	 * 
	 * @return the studyId
	 */
	public String getStudyId() {
		return studyId;
	}

	/**
	 * Modifica el atributo studyId de la tabla Serie.
	 * 
	 * @param studyId the studyId to set
	 */
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	/**
	 * Recupera el atributo patientId de la tabla Serie.
	 * 
	 * @return the patientId
	 */
	public String getPersonalId() {
		return personalId;
	}

	/**
	 * Modifica el atributo patientId de la tabla Serie.
	 * 
	 * @param patientId the patientId to set
	 */
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	/**
	 * Recupera el atributo seriesId de la tabla Serie.
	 * 
	 * @return the seriesId
	 */
	public String getSeriesId() {
		return seriesId;
	}

	/**
	 * Modifica el atributo seriesId de la tabla Serie.
	 * 
	 * @param seriesId the seriesId to set
	 */
	public void setSeriesId(String seriesId) {
		this.seriesId = seriesId;
	}

	/**
	 * Recupera el atributo modality de la tabla Serie.
	 * 
	 * @return the modality
	 */
	public String getModality() {
		return modality;
	}

	/**
	 * Modifica el atributo modality de la tabla Serie.
	 *  
	 * @param modality the modality to set
	 */
	public void setModality(String modality) {
		this.modality = modality;
	}

	/**
	 * Recupera el atributo numeroFotos de la tabla Serie.
	 * 
	 * @return the numeroFotos
	 */
	public Integer getNumeroFotos() {
		return numeroFotos;
	}

	/**
	 * Modifica el atributo numeroFotos de la tabla Serie.
	 * 
	 * @param numeroFotos the numeroFotos to set
	 */
	public void setNumeroFotos(Integer numeroFotos) {
		this.numeroFotos = numeroFotos;
	}

	/**
	 * Recupera el atributo descripcion de la tabla Serie.
	 * 
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Modifica el atributo descripcion de la tabla Serie.
	 * 
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Recupera el atributo request_study de la tabla Serie.
	 * 
	 * @return the requestStudy
	 */
	public String getRequestStudy() {
		return requestStudy;
	}

	/**
	 * Modifica el atributo request_study de la tabla Serie.
	 * 
	 * @param requestStudy the requestStudy to set
	 */
	public void setRequestStudy(String requestStudy) {
		this.requestStudy = requestStudy;
	}

	/**
	 * Recupera el atributo url de la tabla Serie.
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Modifica el atributo url de la tabla Serie.
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
}
