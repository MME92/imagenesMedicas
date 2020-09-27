package com.imagenesMedicas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imagenesMedicas.modelo.Serie;

@Repository
public interface SerieRepository  extends JpaRepository<Serie, String> {

	@Query("Select s from Serie s where s.personalId =:personalId and s.numeroFotos >=:numeroFotos")
	public List<Serie> findByPacienteIdNumeroFotos(@Param ("personalId") String personalId,
			@Param ("numeroFotos") Integer numeroFotos);
	
	@Query("Select s from Serie s where s.studyId =:studyId and s.seriesId =:seriesId")
	public Serie findByEstudioIdSerieId(@Param ("studyId") String studyId,
			@Param ("seriesId") String seriesId);
}
