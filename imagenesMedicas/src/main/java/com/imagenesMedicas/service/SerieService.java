package com.imagenesMedicas.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imagenesMedicas.modelo.Serie;

public interface SerieService {

	public Boolean saveSerie(Serie serie);

	public Page<Serie> findByPacienteIdNumeroFotos(String patientId, Integer numeroFotos, Pageable pageable);
	
    public Serie findByEstudioIdSerieId(String studyId, String seriesId);
}
