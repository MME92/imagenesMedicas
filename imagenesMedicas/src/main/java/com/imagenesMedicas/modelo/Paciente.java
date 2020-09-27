package com.imagenesMedicas.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "paciente")
public class Paciente {

	@Id
	@Column(name = "personal_id")
	/** Atributo patient_id de la tabla Paciente. */
	private String personalId;
	
	@Column(name = "patient_id")
	/** Atributo patient_id de la tabla Paciente. */
	private String patientId;
	
	/** Atributo patient_name de la tabla Paciente. */
	@Column(name = "patient_name")
	private String patientName;

	/** Atributo birthdate de la tabla Paciente. */
	private Date birthdate;

	/** Atributo patient_sex de la tabla Paciente. */
	@Column(name = "patient_sex")
	private String patientSex;

	/** Constructor por defecto. */
	public Paciente() {}

	/** Constructor. */
	public Paciente(String personalId, String patientId, String patientName, Date birthdate, String patientSex) {
		this.setPersonalId(personalId);
		this.setPatientId(patientId);
		this.setPatientName(patientName);
		this.setBirthdate(birthdate);
		this.setPatientSex(patientSex);
	}



	/**
	 * Recupera el atributo personalId de la tabla Paciente.
	 * 
	 * @return the personalId
	 */
	public String getPersonalId() {
		return personalId;
	}

	/**
	 * Modifica el atributo personalId de la tabla Paciente.
	 * 
	 * @param personalId the personalId to set
	 */
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
	
	/**
	 * Recupera el atributo patientId de la tabla Paciente.
	 * 
	 * @return the patientId
	 */
	public String getPatientId() {
		return patientId;
	}

	/**
	 * Modifica el atributo patientId de la tabla Paciente.
	 * 
	 * @param patientId the patientId to set
	 */
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	/**
	 * Recupera el atributo patientName de la tabla Paciente.
	 * 
	 * @return the patientName
	 */
	public String getPatientName() {
		return patientName;
	}

	/**
	 * Modifica el atributo patientName de la tabla Paciente.
	 * 
	 * @param patientName the patientName to set
	 */
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	/**
	 * Recupera el atributo birthdate de la tabla Paciente.
	 * 
	 * @return the birthdate
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * Modifica el atributo birthdate de la tabla Paciente.
	 * 
	 * @param birthdate the birthdate to set
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * Recupera el atributo patientSex de la tabla Paciente.
	 * 
	 * @return the patientSex
	 */
	public String getPatientSex() {
		return patientSex;
	}

	/**
	 * Modifica el atributo patientSex de la tabla Paciente.
	 * 
	 * @param patientSex the patientSex to set
	 */
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}

	@Override
	public String toString() {
		return String.format(
				"Customer[patientId=%d, patientName='%s', birthdate='%s', patientSex='%s']",
				getPatientId(), getPatientName(), getBirthdate(), getPatientSex());
	}
}