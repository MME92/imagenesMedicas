/**
 * 
 */
package com.imagenesMedicas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author melan
 *
 */
@Entity
@Table(name = "imagen")
public class Imagen {

	/** Atributo series_id de la tabla Paciente. */
	@Column(name = "paciente_id")
	private String pacienteId;

	/** Atributo series_id de la tabla Paciente. */
	private String request;
	
	/** Atributo series_id de la tabla Paciente. */
	@Column(name = "study_id")
	private String studyId;
	
	/** Atributo series_id de la tabla Paciente. */
	@Column(name = "series_id")
	private String seriesId;
	
	/** Atributo personal_id de la tabla Paciente. */
	@Column(name = "personal_id")
	private String personalId;
	
	/** Atributo base64Encode de la tabla Paciente. */
	@Column(name = "base64_encode", columnDefinition="LONGTEXT")
	private String base64Encode;
	
	/** Atributo instanceNumber de la tabla Paciente. */
	@Column(name = "instance_number")
	private Integer instanceNumber;
	
	@Id
	@Column(name = "image_id")
	private String imageId;

	/**
	 * Constructor por defecto.
	 */
	public Imagen() {
		
	}
	
	/**
	 * Constructor con atributos.
	 */
	public Imagen(String request, String pacienteId, String imageId, String studyId, String seriesId,  String personalId, String base64Encode, Integer instanceNumber) {
		this.studyId = studyId;
		this.request = request;
		this.seriesId = seriesId;
		this.personalId = personalId;
		this.base64Encode = base64Encode;
		this.instanceNumber =  instanceNumber;
		this.imageId = imageId;
		this.pacienteId = pacienteId;
	}
	/**
	 * @return the base64Encode
	 */
	public String getBase64Encode() {
		return base64Encode;
	}

	/**
	 * @param base64Encode the base64Encode to set
	 */
	public void setBase64Encode(String base64Encode) {
		this.base64Encode = base64Encode;
	}

	/**
	 * @return the instanceNumber
	 */
	public Integer getInstanceNumber() {
		return instanceNumber;
	}

	/**
	 * @param instanceNumber the instanceNumber to set
	 */
	public void setInstanceNumber(Integer instanceNumber) {
		this.instanceNumber = instanceNumber;
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
	 * @return the imageId
	 */
	public String getImageId() {
		return imageId;
	}

	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the pacienteId
	 */
	public String getPacienteId() {
		return pacienteId;
	}

	/**
	 * @param pacienteId the pacienteId to set
	 */
	public void setPacienteId(String pacienteId) {
		this.pacienteId = pacienteId;
	}

	/**
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(String request) {
		this.request = request;
	}

}
