/**
 * 
 */
package com.k1rard.tiendamusicalweb.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.k1rard.tiendamusicalentities.entities.Artista;
import com.k1rard.tiendamusicalentities.entities.Genero;
import com.k1rard.tiendamusicalentities.entities.Nacionalidad;
import com.k1rard.tiendamusicalentities.entities.SubGenero;
import com.k1rard.tiendamusicalservices.service.AdminArtistaService;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author k1rard Clase que controla el flujo de la pantalla de administracion
 *         de artistas
 */
@Named
@ViewScoped
public class AdminArtistasController {

	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o
	 * en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(AdminArtistasController.class);

	/**
	 * lista que permite mostrar los artistas en el datatable
	 */
	private List<Artista> artistas;

	/**
	 * lista que permite filtrar los albums del datatable
	 */
	private List<Artista> artistasFiltrados;

	/**
	 * Lista que permite mostrar la lista de nacionalidades en el combo.
	 */
	private List<Nacionalidad> nacionalidades;

	/**
	 * Lista que permite mostrar la lista de generos en el combo.
	 */
	private List<Genero> generos;

	/**
	 * Lista que permite mostrar la lista de subgeneros en el combo.
	 */
	private List<SubGenero> subGeneros;

	/**
	 * Objeto artista a guardar o actualizar.
	 */
	private Artista artista;

	/**
	 * Objeto que permite almacenar de forma temporal una imagen o archivo
	 * seleccionado
	 */
	private UploadedFile uploadedFile;

	/**
	 * Objeto con el contenido del archivo
	 */
	private InputStream inputStream;
	
	/**
	 * Directorio donde se almacenan las imagenes de artistas del proyecto;
	 */
	private String absoultePath;

	/**
	 * Objeto o bean de Spring para utilizar los metodos de logica de negocio para
	 * la administracion de artistas.
	 */
	@Autowired
	private AdminArtistaService adminArtistaService;

	/**
	 * Metodo que inicializa la pantalla de administracion de artista
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Cargando pantalla de administracion de artista...");
	}
	
	/**
	 * Metodo que permite inicializar los servicios y la ruta donde se guardan las imagenes
	 */
	public void inicializarServicios() {
		String relativePath = "resources/images/artistas";
		this.absoultePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
	}
	

	public void initializer() {
		LOGGER.info("Cargando la informacion de la pantalla de administracion de artista...");
		this.consultar();
		this.cargarCombos();
		this.limpiarComponentes();
		this.inicializarServicios();
	}

	/**
	 * Metodo que permite consultar la lista de artistas
	 */
	public void consultar() {
		this.artistas = this.adminArtistaService.consultarArtistas();
	}

	/**
	 * Metodo que permite los combos de nacionalidades y generos
	 */
	public void cargarCombos() {
		this.nacionalidades = this.adminArtistaService.consultarNacionalidades();
		this.generos = this.adminArtistaService.consultarGeneros();
	}
	
	/**
	 * Metodo que permite cargar el artista seleccionado del datatable para ser editado.
	 * @param artistaCargado {@link Artista} objeto con el artista cargado a editar.
	 */ 
	public void cargarArtista(Artista artistaCargado) {
		this.subGeneros =  this.adminArtistaService.consultarSubGeneroPorGenero(artistaCargado.getSubGenero().getGenero().getIdGenero());
		this.artista = artistaCargado;
		
	}

	/**
	 * Metodo que permite limipar o inicializar componentes
	 */
	public void limpiarComponentes() {
		this.artista = new Artista();
		this.artista.setNacionalidad(new Nacionalidad());
		this.artista.setSubGenero(new SubGenero());
		this.artista.getSubGenero().setGenero(new Genero());
	}

	/**
	 * Metodo que permite obtener la lista de subgeneros de musica por el genero
	 * seleccionado
	 */
	public void consultarSubGenerosPorGenero() {
		Long idGenero = this.artista.getSubGenero().getGenero().getIdGenero();
		this.subGeneros = this.adminArtistaService.consultarSubGeneroPorGenero(idGenero);
	}

	/**
	 * Metodo que permite cargar la imagen seleccionada por el usuario
	 * 
	 * @param fileUploadEvent {@link FileUploadEvent} objeto con el evento de carga
	 *                        de imagen.
	 */
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		this.uploadedFile = fileUploadEvent.getFile();

		try {
			this.inputStream = uploadedFile.getInputStream();
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR",
					"Hubo un problema al cargar el archivo, verifica que no este corrupto");
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que permite guardar un artista 
	 */
	public void guardar() {
		
		try {
			CommonUtils.guardarImagen(this.absoultePath, this.uploadedFile.getFileName(), this.inputStream);
			this.artista.setImagen(this.uploadedFile.getFileName());
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un problema al guardar la imagen en el directorio indicado, favor de contactar con soporte.");
			e.printStackTrace();
		}
		
		Artista artistaGuardado = this.adminArtistaService.guardarArtista(artista);

		if (artistaGuardado.getIdArtista() != null) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK!",
					"El artista " + artistaGuardado.getNombre() + " se ha guardado o actualizado exitosammente.");
			PrimeFaces.current().executeScript("PF('dlgArtista').hide()");
			this.limpiarComponentes();
		} else {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un erro al guardar un artista, favor de intentarlo mas tarde.");
		}
		this.consultar();
	}

	/**
	 * @return the artistas
	 */
	public List<Artista> getArtistas() {
		return artistas;
	}

	/**
	 * @param artistas the artistas to set
	 */
	public void setArtistas(List<Artista> artistas) {
		this.artistas = artistas;
	}

	/**
	 * @return the artistasFiltrados
	 */
	public List<Artista> getArtistasFiltrados() {
		return artistasFiltrados;
	}

	/**
	 * @param artistasFiltrados the artistasFiltrados to set
	 */
	public void setArtistasFiltrados(List<Artista> artistasFiltrados) {
		this.artistasFiltrados = artistasFiltrados;
	}

	/**
	 * @return the adminArtistaService
	 */
	public AdminArtistaService getAdminArtistaService() {
		return adminArtistaService;
	}

	/**
	 * @param adminArtistaService the adminArtistaService to set
	 */
	public void setAdminArtistaService(AdminArtistaService adminArtistaService) {
		this.adminArtistaService = adminArtistaService;
	}

	/**
	 * @return the nacionalidades
	 */
	public List<Nacionalidad> getNacionalidades() {
		return nacionalidades;
	}

	/**
	 * @param nacionalidades the nacionalidades to set
	 */
	public void setNacionalidades(List<Nacionalidad> nacionalidades) {
		this.nacionalidades = nacionalidades;
	}

	/**
	 * @return the generos
	 */
	public List<Genero> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	/**
	 * @return the subgeneros
	 */
	public List<SubGenero> getSubGeneros() {
		return subGeneros;
	}

	/**
	 * @param subgeneros the subgeneros to set
	 */
	public void setSubGeneros(List<SubGenero> subGeneros) {
		this.subGeneros = subGeneros;
	}

	/**
	 * @return the artista
	 */
	public Artista getArtista() {
		return artista;
	}

	/**
	 * @param artista the artista to set
	 */
	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	/**
	 * @return the uploadedFile
	 */
	public UploadedFile getUploadedFile() {
		return uploadedFile;
	}

	/**
	 * @param uploadedFile the uploadedFile to set
	 */
	public void setUploadedFile(UploadedFile uploadedFile) {
		this.uploadedFile = uploadedFile;
	}

	/**
	 * @return the inputStream
	 */
	public InputStream getInputStream() {
		return inputStream;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * @return the absoultePath
	 */
	public String getAbsoultePath() {
		return absoultePath;
	}

	/**
	 * @param absoultePath the absoultePath to set
	 */
	public void setAbsoultePath(String absoultePath) {
		this.absoultePath = absoultePath;
	}

	
}
