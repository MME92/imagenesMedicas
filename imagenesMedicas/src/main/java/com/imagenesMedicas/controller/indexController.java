package com.imagenesMedicas.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.imagenesMedicas.modelo.Estudio;
import com.imagenesMedicas.modelo.Imagen;
import com.imagenesMedicas.modelo.Paciente;
import com.imagenesMedicas.modelo.Serie;
import com.imagenesMedicas.service.EstudioService;
import com.imagenesMedicas.service.ImageService;
import com.imagenesMedicas.service.PacienteService;
import com.imagenesMedicas.service.SerieService;

import ij.ImagePlus;
import ij.util.DicomTools;

@Controller
public class indexController {

	/** Properties urlParcial. */
	@Value("${urlParcial}")
	private String urlParcial;
	
	/** Content xml url. */
	@Value("${content}")
	private String content;
	
	/** Servicio de pacientes. */
	@Autowired(required = true)
	private PacienteService pacienteService;

	/** Servicio de series. */
	@Autowired(required = true)
	private SerieService serieService;

	/** Servicio de estudios. */
	@Autowired(required = true)
	private EstudioService estudioService;

	/** Servicio de imágenes. */
	@Autowired(required = true)
	private ImageService imageService;
	
	@GetMapping(value ="/cargaDatos")
	public String cargaDatos(ModelMap model) {
		File file = new File(content);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			NodeList nList = doc.getElementsByTagName("patient");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					String patientid = eElement.getAttribute("id");
					String personalId = eElement.getElementsByTagName("personal_id").item(0).getTextContent();
					String name = eElement.getElementsByTagName("name").item(0).getTextContent();
					String birthdate = eElement.getElementsByTagName("birthdate_invariant").item(0).getTextContent();
					Date birthdateFormatDate = new SimpleDateFormat("MM/dd/yyyy").parse(birthdate);
					String sex = eElement.getElementsByTagName("sex").item(0).getTextContent();
					Paciente paciente = new Paciente(personalId, patientid, name, birthdateFormatDate, sex);
					pacienteService.savePaciente(paciente);

					NodeList nListRequest = eElement.getElementsByTagName("request");
					for (int tempRequest = 0; tempRequest < nListRequest.getLength(); tempRequest++) {
						Node nNodeRequest = nListRequest.item(tempRequest);
						Element eElementRequest = (Element) nNodeRequest;

						String request = eElementRequest.getAttribute("id");
							NodeList nListStudy = eElementRequest.getElementsByTagName("study");
							for (int tempStudy = 0; tempStudy < nListStudy.getLength(); tempStudy++) {
								Node nNodeStudy = nListStudy.item(tempStudy);
								Element eElementStudy = (Element) nNodeStudy;
	
								String studyId = eElementStudy.getAttribute("id");
								String initialDate = eElementStudy.getElementsByTagName("datetime").item(0)
										.getTextContent();
								Date initialDateFormatDate = new SimpleDateFormat("MM/dd/yyyy hh:mm").parse(initialDate);
								String utcDatetime = eElementStudy.getElementsByTagName("utc_datetime").item(0)
										.getTextContent();
								Date utcDatetimeFormatDate = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss").parse(utcDatetime);
								String patientDescription = eElementStudy.getElementsByTagName("description").item(0)
										.getTextContent();
								String accessionId = eElementStudy.getElementsByTagName("accession_id").item(0)
										.getTextContent();
								String examenId = eElementStudy.getElementsByTagName("exam_id").item(0).getTextContent();
								String bodyPart = eElementStudy.getElementsByTagName("body_part").item(0).getTextContent();
								String modality = eElementStudy.getElementsByTagName("modality").item(0).getTextContent();
								String instanceUid = eElementStudy.getElementsByTagName("instance_uid").item(0)
										.getTextContent();
	
								Estudio estudio = new Estudio(request, personalId, studyId, initialDateFormatDate,
										utcDatetimeFormatDate, patientDescription, accessionId, examenId, bodyPart,
										modality, instanceUid);
								estudioService.saveEstudio(estudio);
	
								NodeList nListSeries = eElementRequest.getElementsByTagName("series");
								for (int tempSeries = 0; tempSeries < nListSeries.getLength(); tempSeries++) {
									Node nNodeSerie = nListSeries.item(tempSeries);
									Element eElementSerie = (Element) nNodeSerie;
	
									String seriesId = eElementSerie.getAttribute("id");
										String modalitySerie = eElementSerie.getElementsByTagName("modality").item(0)
												.getTextContent();
										Integer numeroFotos = Integer.parseInt(
												eElementSerie.getElementsByTagName("number").item(0).getTextContent());
										String descripcion = eElementSerie.getElementsByTagName("description").item(0)
												.getTextContent();
										String url = patientid + "/" + request + "/" + studyId + "/" + seriesId;
										Serie serie = new Serie(patientid, studyId, personalId, request, seriesId, modalitySerie, numeroFotos,
												descripcion, url);
										serieService.saveSerie(serie);
										
										String baseUrl = url + "/";
										NodeList nListImage = eElementSerie.getElementsByTagName("image");
										for (int tempImage = 0; tempImage < nListImage.getLength(); tempImage++) {
											Node nNodeImage = nListImage.item(tempImage);
											Element eElementImage = (Element) nNodeImage;		
											String imageId = eElementImage.getAttribute("id");
											String urlToImage = baseUrl + imageId;
											HashMap<Integer, String> base64Image = getBase64EncodeFromFile(urlToImage);									
											base64Image.forEach((k,v) -> imageService.saveImagen(new Imagen(request, patientid, imageId, studyId, seriesId, personalId, v, k)));
										}
								}								
							}
					}
				}
			}
		} catch (Exception e) {
			model.addAttribute("estado", "Ups, hubo un problema, ya tenías los datos cargados?");
			e.printStackTrace();
		}
		return "cargaDatos.html";
	}
	
	/** Muestra la pantalla de incio de aplicación. */
	@GetMapping("/")
	public String getDatosPaciente(ModelMap model) {
		model.addAttribute(new BusquedaImagenesForm());
		return "index.html";
	}

	@GetMapping(value = {"/buscar/{personalId}/{numeroFotos}/{page}/{size}", "/buscar/{personalId}/{numeroFotos}"})
	public String postDatosPaciente(ModelMap model,
			@ModelAttribute BusquedaImagenesForm busquedaImagenesForm,
			@PathVariable("personalId") String personalId,
		    @PathVariable("numeroFotos") String numeroFotos,
		    @PathVariable Optional<Integer> page, 
		    @PathVariable Optional<Integer> size) {

		Page<Serie> seriesPaciente;
		int currentPage = page.orElse(1);
		int pageSize = 10; 
	    Paciente paciente = pacienteService.findByPacienteId(personalId);


		seriesPaciente = serieService.findByPacienteIdNumeroFotos
				(personalId, Integer.parseInt(numeroFotos), PageRequest.of(currentPage - 1, pageSize));
		busquedaImagenesForm.setPersonalId(personalId);
		busquedaImagenesForm.setNumeroFotos(Integer.parseInt(numeroFotos));
		busquedaImagenesForm.setSeriesPaciente(seriesPaciente);
		model.addAttribute("seriesPaciente", seriesPaciente);
        int totalPages = seriesPaciente.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
		model.addAttribute(busquedaImagenesForm);
		model.addAttribute(paciente);
		String userDir = System.getProperty ("user.dir");
		System.out.println(userDir);
		return "index.html :: fragmentListImagenes";
	}


	public HashMap<Integer, String> getBase64EncodeFromFile(String path) throws IOException {
		
		HashMap<Integer, String> mapBase64InstanceNumber = new HashMap<>();	
    	String urlInstanceNumber = urlParcial + path;
		ImagePlus imp = new ImagePlus(urlInstanceNumber);
		String instaceNumber = DicomTools.getTag(imp, "0020,0013");
		String[] splitted = instaceNumber.split(" ");
		Integer instanceNumberInteger = Integer.parseInt(splitted[1]);
		byte[] encoded = Files.readAllBytes(Paths.get(urlInstanceNumber));
		String encodedStringPar = Base64.getEncoder().encodeToString(encoded);
		mapBase64InstanceNumber.put(instanceNumberInteger, path);
		
		return mapBase64InstanceNumber;
	}
	
	
	/** Carga la página que debe mostrar las imagenes. 
	 * @throws IOException */
	@PostMapping("/view")
	public String loadImagen(ModelMap model,
			@RequestParam String url) throws IOException {

		String [] imageIndex = url.split("/");
		Integer limit = imageService.countImagenes(imageIndex[1], imageIndex[3]);
		List<String> imagenes = new ArrayList<>();
		List<String> imagenesABase64 = new ArrayList<>();
		
		if (limit > 0) {
			if (limit == 1) {
				String imagen = imageService.imageneUnica(imageIndex[1], imageIndex[3]);
				imagenes.add(imagen);
			} else if (limit < 500) {
				imagenes = imageService.imagenes(imageIndex[1], imageIndex[3], limit);				
			} else {
				imagenes = imageService.imagenes(imageIndex[1], imageIndex[3], 100);
			}
		}


		for (String image: imagenes) {
			ImagePlus imp = new ImagePlus(urlParcial + image);
			String instaceNumber = DicomTools.getTag(imp, "0020,0013");
			String[] splitted = instaceNumber.split(" ");
			Integer instanceNumberInteger = Integer.parseInt(splitted[1]);
			byte[] encoded = Files.readAllBytes(Paths.get(urlParcial + image));
			String encodedStringPar = Base64.getEncoder().encodeToString(encoded);	
			imagenesABase64.add(encodedStringPar);
		}
		
		if (imagenes.size() > 0) {
			String image = imagenes.get(0);
			Serie serie = serieService.findByEstudioIdSerieId(imageIndex[2], imageIndex[3]);
			model.addAttribute("description", serie.getDescripcion());
			
			ImagePlus impDatosPaciente = new ImagePlus(urlParcial + image);
			String institucionName = DicomTools.getTag(impDatosPaciente, "0008,0080");
			model.addAttribute("institucionName", institucionName);
	
			String patientName = DicomTools.getTag(impDatosPaciente, "0010,0010");
			model.addAttribute("patientName", patientName);
	
//			String birthDate = DicomTools.getTag(impDatosPaciente, "0010,0030");
			model.addAttribute("birthDate", pacienteService.findByPacienteId(serie.getPersonalId()).getBirthdate());
	
			String sex = DicomTools.getTag(impDatosPaciente, "0010,0040");
			model.addAttribute("sex", sex);
		}
		model.addAttribute("limit", limit);
		model.addAttribute("base64", imagenesABase64);
		url = imageIndex[0] + "-" + imageIndex[1] + "-" + imageIndex[3] + "-" + imageIndex[3];
		model.addAttribute("url", url);
		return "view.html";		
	}
	
	/** Carga la página que debe mostrar las imagenes. 
	 * @throws IOException */
	@GetMapping(value = {"/viewImageSiguientes/{longitudActual}/{urlInicial}/{contador}"})
	public String loadImagenSiguientes(ModelMap model,  @PathVariable("longitudActual") int longitudActual,
			@PathVariable("urlInicial") String urlInicial, @PathVariable("contador") int contador) throws IOException {

		String [] imageIndex = urlInicial.split("-");
		Integer limit = imageService.countImagenes(imageIndex[1], imageIndex[3]);
		List<String> imagenes = new ArrayList<>();
		List<String> imagenesABase64 = new ArrayList<>();
		int diferenciaLimiteNuevaCargada = limit - (longitudActual + 100);
		int newLimit = longitudActual + 100;
		if (diferenciaLimiteNuevaCargada <= 0) {
			imagenes = imageService.imagenesSiguientes(imageIndex[1], imageIndex[3], limit, longitudActual);
		} else {
			imagenes = imageService.imagenesSiguientes(imageIndex[1], imageIndex[3], newLimit, longitudActual);
		}
		for (String image: imagenes) {
			ImagePlus imp = new ImagePlus(urlParcial + image);
			byte[] encoded = Files.readAllBytes(Paths.get(urlParcial + image));
			String encodedStringPar = Base64.getEncoder().encodeToString(encoded);	
			imagenesABase64.add(encodedStringPar);
		}
		
		if ((contador % 2) != 0) {
			model.addAttribute("base643", imagenesABase64);
		} else {
			model.addAttribute("base642", imagenesABase64);
		}
		model.addAttribute("contadorSiguiente", contador);
		return "view.html :: fragmentImagenes";		
	}

	/** Carga la página que debe mostrar las imagenes. 
	 * @throws IOException */
	@GetMapping(value = {"/viewNextImage/{instanceNumber}/{urlInicial}"})
	public String loadImage(ModelMap model,  @PathVariable("instanceNumber") int instanceNumber,
			@PathVariable("urlInicial") String urlInicial) throws IOException {

		String [] imageIndex = urlInicial.split("-");
		String imagen = "";
		imagen = imageService.imageneSiguiente(imageIndex[1], imageIndex[3], instanceNumber);


		model.addAttribute("base64", imagen);
		return "view.html :: fragmentImagenes";		
	}
	
	/** Carga la página que debe mostrar las imagenes. 
	 * @throws IOException */
	@GetMapping(value = "/webCarga")
	public String cargaDatos() throws IOException {
		return "cargaDatos.html";	

		
	}
	
	/** Carga la página que debe mostrar las imagenes. 
	 * @throws IOException */
	@GetMapping(value = "/contacto")
	public String contacto() throws IOException {
		return "contacto.html";		

		
	}
	
	/** Carga la página que debe mostrar las imagenes. 
	 * @throws IOException */
	@GetMapping(value = "/sobreLaWeb")
	public String sobreLaWeb() throws IOException {
		return "sobreLaWeb.html";	

		
	}
	
}
