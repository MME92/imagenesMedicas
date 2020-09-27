package com.imagenesMedicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imagenesMedicas.modelo.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String>{
	
	@Query("Select p from Paciente p where p.personalId =:personalId")
	public Paciente findByPacienteId(@Param ("personalId") String personalId);

}
