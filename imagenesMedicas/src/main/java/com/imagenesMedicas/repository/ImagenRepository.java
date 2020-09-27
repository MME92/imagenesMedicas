/**
 * 
 */
package com.imagenesMedicas.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.imagenesMedicas.modelo.Imagen;

/**
 * @author melan
 *
 */
@Repository
public interface ImagenRepository extends JpaRepository<Imagen, String>{

	@Query("Select count(*) from Imagen i where i.request =:request and i.seriesId =:seriesId")
	public Integer countImagenes(@Param ("request") String request, @Param ("seriesId") String series_id);
	
	@Query("Select i.base64Encode from Imagen i where i.request =:request and i.seriesId =:seriesId and i.instanceNumber <=:limit order by i.instanceNumber")
	public List<String> imagenes(@Param ("request") String request, @Param ("seriesId") String series_id, @Param ("limit") Integer limit);
		
	@Query("Select i.base64Encode from Imagen i where i.request =:request and i.seriesId =:seriesId and i.instanceNumber BETWEEN :lastImageShow and :limit order by i.instanceNumber")
	public List<String> imagenesSiguientes(@Param ("request") String request, @Param ("seriesId") String series_id, @Param ("limit") Integer limit, @Param ("lastImageShow") Integer lastImageShow);

	@Query("Select i.base64Encode from Imagen i where i.request =:request and i.seriesId =:seriesId and i.instanceNumber =:instanceNumber")
	public String imageneSiguiente(@Param ("request") String request, @Param ("seriesId") String series_id, @Param ("instanceNumber") Integer instanceNumber);

	@Query("Select i.base64Encode from Imagen i where i.request =:request and i.seriesId =:seriesId")
	public String imageneUnica(@Param ("request") String request, @Param ("seriesId") String series_id);
}
