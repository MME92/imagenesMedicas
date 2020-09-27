package com.imagenesMedicas.service.Impl;

import java.util.Set;

import javax.persistence.PersistenceException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imagenesMedicas.modelo.Estudio;
import com.imagenesMedicas.repository.EstudioRepository;
import com.imagenesMedicas.service.EstudioService;

@Service
public class EstudioServiceImpl implements EstudioService{

    @Autowired(required=true)
    private EstudioRepository estudioRepository;
    
    
    @Override
    public Boolean saveEstudio(Estudio estudio) throws PersistenceException{
         try {
        	 estudioRepository.save(estudio);
             return true;
         } catch (PersistenceException e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);       
         }
    }
    
    
    public Set<Estudio> findEstudioByPacienteId(String patientId) {
        try {
        	return estudioRepository.findEstudioByPacienteId(patientId);
        } catch (PersistenceException e) {
    		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);      	   	
        }
    }
    
}
