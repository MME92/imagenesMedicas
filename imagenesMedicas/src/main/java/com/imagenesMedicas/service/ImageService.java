/**
 * 
 */
package com.imagenesMedicas.service;

import java.util.List;

import com.imagenesMedicas.modelo.Imagen;

/**
 * @author melan
 *
 */
public interface ImageService {

	public Boolean saveImagen(Imagen image);
	
	public Integer countImagenes(String request, String serie_id);
	
	public List<String> imagenes(String request, String serie_id, Integer limit);
	
	public List<String> imagenesSiguientes(String request, String serie_id, Integer limit, Integer lastImageShow);

	public String imageneSiguiente(String request, String serie_id, Integer instanceNumber);
	
	public String imageneUnica(String request, String serie_id);


}
