package com.imagenesMedicas.service.Impl;

import javax.persistence.PersistenceException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagenesMedicas.modelo.Paciente;
import com.imagenesMedicas.repository.PacienteRepository;
import com.imagenesMedicas.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService{

    @Autowired(required=true)
    private PacienteRepository pacienteRepository;
    
    
    @Override
    public Boolean savePaciente(Paciente paciente) throws PersistenceException{
         try {
        	 pacienteRepository.save(paciente);
              return true;
         } catch (PersistenceException e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);         }
    }
    
    @Override
    public Paciente findByPacienteId(String personalId) throws PersistenceException{
    	try {
    		return pacienteRepository.findByPacienteId(personalId);
    	} catch (Exception e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);         }    		
    }
}
