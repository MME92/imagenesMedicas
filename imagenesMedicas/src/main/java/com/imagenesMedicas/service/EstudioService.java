package com.imagenesMedicas.service;

import java.util.Set;

import com.imagenesMedicas.modelo.Estudio;

public interface EstudioService {

	public Boolean saveEstudio(Estudio estudio);
	
	public Set<Estudio> findEstudioByPacienteId(String patientId);

}
