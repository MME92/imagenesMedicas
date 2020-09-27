/**
 * 
 */
package com.imagenesMedicas.service.Impl;

import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.imagenesMedicas.modelo.Imagen;
import com.imagenesMedicas.repository.ImagenRepository;
import com.imagenesMedicas.service.ImageService;

/**
 * @author melan
 *
 */
@Service
public class ImageServiceImpl implements ImageService{

    @Autowired(required=true)
    private ImagenRepository imagenRepository;
    
    
    @Override
    public Boolean saveImagen(Imagen imagen) throws PersistenceException{
         try {
        	 imagenRepository.save(imagen);
             return true;
         } catch (PersistenceException e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);       
         }
    }
   
    public Integer countImagenes(String request, String series_id) {
    	try {
    		return imagenRepository.countImagenes(request, series_id);
    	} catch (PersistenceException e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);       			
		}
    	
    }
    
	public List<String> imagenes(String request, String serie_id, Integer limit){
		try {
			return imagenRepository.imagenes(request, serie_id, limit);
		} catch (PersistenceException e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);       			
		}
	}
	
	public List<String> imagenesSiguientes(String request, String serie_id, Integer limit, Integer lastImageShow){
		try {
			return imagenRepository.imagenesSiguientes(request, serie_id, limit, lastImageShow);
		} catch (PersistenceException e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);       			
		}
	}

	public String imageneSiguiente(String request, String serie_id, Integer instanceNumber){
		try {
			return imagenRepository.imageneSiguiente(request, serie_id, instanceNumber);
		} catch (PersistenceException e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);       			
		}
	}
	
	public String imageneUnica(String request, String serie_id){
		try {
			return imagenRepository.imageneUnica(request, serie_id);
		} catch (PersistenceException e) {
     		throw new ServiceException("Error database :  " + e.getLocalizedMessage(), e);       			
		}
	}
}
