package com.imagenesMedicas.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estudio")
public class Estudio {
	
	@Column(name = "request_id")
	/** Atributo request_id de la tabla Estudio. */
	private String requestId;

	@Column(name = "personal_id")
	/** Atributo patient_id de la tabla Estudio. */
	private String personalId;
		
	@Id
	@Column(name = "study_id")
	/** Atributo study_id de la tabla Estudio. */
	private String studyId;

	@Column(name = "initial_date")
	/** Atributo initial_date de la tabla Estudio. */
	private Date initialDate;
	
	@Column(name = "utc_datetime")
	/** Atributo utc_datetime de la tabla Estudio. */
	private Date utcDatetime;

	@Column(name = "patient_description")
	/** Atributo patient_description de la tabla Estudio. */
	private String patientDescription;

	@Column(name = "accession_id")
	/** Atributo accessionId de la tabla Estudio. */
	private String accessionId;

	@Column(name = "exam_id")
	/** Atributo examenId de la tabla Estudio. */
	private String examenId;
	
	@Column(name = "body_part")
	/** Atributo body_part de la tabla Estudio. */	
	private String bodyPart;
	
	/** Atributo modality de la tabla Estudio. */	
	private String modality;
	
	@Column(name = "instance_uid")
	/** Atributo instance_uid de la tabla Estudio. */		
	private String instanceUid;
	
	/** Constructor por defecto. */
	public Estudio () {}
	
	/** Constructor. */
	public Estudio (String requestId, String patientId, String studyId, Date initialDate,
			Date utcDatetime, String patientDescription, String accessionId,
			String examenId, String bodyPart, String modality, String instanceUid) {

		this.setRequestId(requestId);
		this.setPersonalId(patientId);
		this.setStudyId(studyId);
		this.setInitialDate(initialDate);
		this.setUtcDatetime(utcDatetime);
		this.setPatientDescription(patientDescription);
		this.setAccessionId(accessionId);
		this.setExamenId(examenId);
		this.setBodyPart(bodyPart);
		this.setModality(modality);
		this.setInstanceUid(instanceUid);
				
	}

	/**
	 * Recupera el atributo requestId de la tabla Paciente.
	 * 
	 * @return the requestId
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * Modifica el atributo requestId de la tabla Paciente.
	 * 
	 * @param requestId the requestId to set
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * Recupera el atributo patientId de la tabla Paciente.
	 * 
	 * @return the patientId
	 */
	public String getPersonalId() {
		return personalId;
	}

	/**
	 * Modifica el atributo patientId de la tabla Paciente.
	 * 
	 * @param patientId the patientId to set
	 */
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}

	/**
	 * Recupera el atributo studyId de la tabla Paciente.
	 * 
	 * @return the studyId
	 */
	public String getStudyId() {
		return studyId;
	}

	/**
	 * Modifica el atributo studyId de la tabla Paciente.
	 * 
	 * @param studyId the studyId to set
	 */
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}

	/**
	 * Recupera el atributo initialDate de la tabla Paciente.
	 * 
	 * @return the initialDate
	 */
	public Date getInitialDate() {
		return initialDate;
	}

	/**
	 * Modifica el atributo initialDate de la tabla Paciente.
	 * 
	 * @param initialDate the initialDate to set
	 */
	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	/**
	 * Recupera el atributo utcDatetime de la tabla Paciente.
	 * 
	 * @return the utcDatetime
	 */
	public Date getUtcDatetime() {
		return utcDatetime;
	}

	/**
	 * Modifica el atributo utcDatetime de la tabla Paciente.
	 * 
	 * @param utcDatetime the utcDatetime to set
	 */
	public void setUtcDatetime(Date utcDatetime) {
		this.utcDatetime = utcDatetime;
	}

	/**
	 * Recupera el atributo patientDescription de la tabla Paciente.
	 *
	 * @return the patientDescription
	 */
	public String getPatientDescription() {
		return patientDescription;
	}

	/**
	 * Modifica el atributo patientDescription de la tabla Paciente.
	 * 
	 * @param patientDescription the patientDescription to set
	 */
	public void setPatientDescription(String patientDescription) {
		this.patientDescription = patientDescription;
	}

	/**
	 * Recupera el atributo accessionId de la tabla Paciente.
	 * 
	 * @return the accessionId
	 */
	public String getAccessionId() {
		return accessionId;
	}

	/**
	 * Modifica el atributo accessionId de la tabla Paciente.
	 * 
	 * @param accessionId the accessionId to set
	 */
	public void setAccessionId(String accessionId) {
		this.accessionId = accessionId;
	}

	/**
	 * Recupera el atributo examenId de la tabla Paciente.
	 *  
	 * @return the examenId
	 */
	public String getExamenId() {
		return examenId;
	}

	/**
	 * Modifica el atributo examenId de la tabla Paciente.
	 * 
	 * @param examenId the examenId to set
	 */
	public void setExamenId(String examenId) {
		this.examenId = examenId;
	}

	/**
	 * Recupera el atributo bodyPart de la tabla Paciente.
	 * 
	 * @return the bodyPart
	 */
	public String getBodyPart() {
		return bodyPart;
	}

	/**
	 * Modifica el atributo bodyPart de la tabla Paciente.
	 * 
	 * @param bodyPart the bodyPart to set
	 */
	public void setBodyPart(String bodyPart) {
		this.bodyPart = bodyPart;
	}

	/**
	 * Recupera el atributo modality de la tabla Paciente.
	 *  
	 * @return the modality
	 */
	public String getModality() {
		return modality;
	}

	/**
	 * Modifica el atributo modality de la tabla Paciente.
	 * 
	 * @param modality the modality to set
	 */
	public void setModality(String modality) {
		this.modality = modality;
	}

	/**
	 * Recupera el atributo instanceUid de la tabla Paciente.
	 *  
	 * @return the instanceUid
	 */
	public String getInstanceUid() {
		return instanceUid;
	}

	/**
	 * Modifica el atributo instanceUid de la tabla Paciente.
	 * 
	 * @param instanceUid the instanceUid to set
	 */
	public void setInstanceUid(String instanceUid) {
		this.instanceUid = instanceUid;
	}
	
	
}
