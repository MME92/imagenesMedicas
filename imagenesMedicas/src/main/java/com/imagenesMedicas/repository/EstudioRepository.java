package com.imagenesMedicas.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imagenesMedicas.modelo.Estudio;

@Repository
public interface EstudioRepository  extends JpaRepository<Estudio, String> {

	@Query("Select estudio from Estudio estudio where estudio.personalId =:personalId")
	public Set<Estudio> findEstudioByPacienteId(@Param ("personalId") String personalId);
	
}
