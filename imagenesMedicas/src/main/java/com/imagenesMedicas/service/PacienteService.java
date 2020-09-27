package com.imagenesMedicas.service;

import com.imagenesMedicas.modelo.Paciente;

public interface PacienteService {

	public Boolean savePaciente(Paciente paciente);
	
	public Paciente findByPacienteId(String personalId);
	
}
