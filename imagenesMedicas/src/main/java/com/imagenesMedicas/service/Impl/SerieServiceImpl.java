package com.imagenesMedicas.service.Impl;

import java.util.Collections;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imagenesMedicas.modelo.Serie;
import com.imagenesMedicas.repository.SerieRepository;
import com.imagenesMedicas.service.SerieService;


@Service
public class SerieServiceImpl implements SerieService {

    @Autowired(required=true)
    private SerieRepository serieRepository;
    
    
    @Override
    public Boolean saveSerie(Serie serie) throws PersistenceException{
         try {
        	 serieRepository.save(serie);
              return true;
         } catch (PersistenceException e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);         }
    }
    
    @Override
    public Page<Serie> findByPacienteIdNumeroFotos(String patientId, Integer numeroFotos, Pageable pageable) {
        try {
       	 List<Serie> seriesPaciente = serieRepository.findByPacienteIdNumeroFotos(patientId, numeroFotos);
       	 return findPaginated(seriesPaciente, pageable);
        } catch (PersistenceException e) {
    		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);     	
        }
    }
    
    public Page<Serie> findPaginated(List<Serie> seriesPaciente, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Serie> list;
 
        if (seriesPaciente.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, seriesPaciente.size());
            list = seriesPaciente.subList(startItem, toIndex);
        }
 
        Page<Serie> seriesPage
          = new PageImpl<Serie>(list, PageRequest.of(currentPage, pageSize), seriesPaciente.size());
 
        return seriesPage;
    }
    
    public Serie findByEstudioIdSerieId(String studyId, String seriesId) {
        try {
       	 return serieRepository.findByEstudioIdSerieId(studyId, seriesId);
        } catch (PersistenceException e) {
    		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);     	
        }
    }
    
}
