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

import com.k1rard.tiendamusicalentities.entities.Album;
import com.k1rard.tiendamusicalentities.entities.Artista;
import com.k1rard.tiendamusicalentities.entities.Disquera;
import com.k1rard.tiendamusicalservices.service.AdminAlbumsService;
import com.k1rard.tiendamusicalservices.service.AlbumService;
import com.k1rard.tiendamusicalweb.utils.CommonUtils;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

/**
 * @author k1rard
 * Clase que controla el flujo de la pantala de albums para el administrador
 */
@Named
@ViewScoped
public class AdminAlbumsController {

	/**
	 * Objeto que permite mostrar los mensajes de log en la consola del servidor o
	 * en un archivo externo.
	 */
	private static final Logger LOGGER = LogManager.getLogger(AdminAlbumsController.class);
	
	/**
	 * Albums a mostrarse en el datatable
	 */
	private List<Album> albums;
	
	/**
	 * Albums filtrados por los encabezados del datatable.
	 */
	private List<Album> albumsFiltrados;
	
	/**
	 * Artistas a seleccionar
	 */
	private List<Artista> artistas;
	
	/**
	 * Disqueras a seleccionar
	 */
	private List<Disquera> disqueras;
	
	/**
	 * Objeto que guarda o actualiza un album 
	 */
	private Album album;
	
	/**
	 * Objeto que se utiliza para almacenar el archivo de la imagen del album a cargar de forma temporal.
	 */
	private UploadedFile uploadedFile;
	
	/**
	 * Objeto que contendra el flujo de bytes del archivo de imagen a cargar
	 */
	private InputStream inputStream;
	
	/**
	 * Directorio donde se almacenan las imagenes de albums del proyecto;
	 */
	private String absoultePath = null;
	
	/**
	 * Bean de spring inyectado con JSF para ocupar los metodos de logica de negocio para albums
	 */
	@Autowired
	private AlbumService albumService;
	
	/**
	 * Bean de spring inyectado con JSF para ocupar los metodos de logica de negocio para albums
	 */
	@Autowired
	private AdminAlbumsService adminAlbumsService;
	
	/**
	 * Inicializando la pantalla de albums
	 */
	@PostConstruct
	public void init() {
		LOGGER.info("Inicializando la pantalla de albums...");
	}
	
	public void initialize() {
		this.consultar();
		this.inicializarServicios();
		this.limpiarComponentes();
	}
	
	/**
	 * Permite consultar la informacion de los albums de la base de datos
	 */
	public void consultar() {
		LOGGER.info("Consultando el listado de albums...");
		this.albums = this.albumService.consultarAlbums();
	}
	
	/**
	 * Metodo que permite inicializar la informacion de los componentes de carga en los desplegables.
	 */
	public void inicializarServicios() {
		this.disqueras = this.adminAlbumsService.consultarDisqueras();
		this.artistas =  this.adminAlbumsService.consultarArtistas();
		
		String relativePath = "resources/images/albums";
		this.absoultePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
	}
	
	/**
	 * Metodo que permite inicializar o limpiar los componentes u onjetos utilizados en el formulario
	 */
	public void limpiarComponentes() {
		this.album = new Album();
		this.album.setDisquera(new Disquera());
		this.album.setArtista(new Artista());
		this.uploadedFile = null;
	}
	
	/**
	 * Metodo que permite inicializar una imagen de carga temporal para un album.
	 * @param fileUploadEvent {@link FileUploadEvent} Objeto que carga la imagen de forma temporal.
	 */
	public void handleFileUpload(FileUploadEvent fileUploadEvent) {
		this.uploadedFile = fileUploadEvent.getFile();
		
		try {
			this.inputStream = uploadedFile.getInputStream();
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un problema al cargar el archivo, verifica que no este corrupto");
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que permite guardar el album
	 */
	public void guardar() {
		try {
			CommonUtils.guardarImagen(this.absoultePath, this.uploadedFile.getFileName(), this.inputStream);
		} catch (IOException e) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_ERROR, "ERROR", "Hubo un problema al guardar la imagen en el directorio indicado, favor de contactar con soporte.");
			e.printStackTrace();
		}
		
		this.album.setImagen(this.uploadedFile.getFileName());
		
		Album albumGuardado = this.adminAlbumsService.guardarAlbum(this.album);
		
		if(albumGuardado.getIdAlbum() != null) {
			CommonUtils.mostrarMensaje(FacesMessage.SEVERITY_INFO, "OK", "Album " + albumGuardado.getNombre() + " guardado exitosamente.");
			
			PrimeFaces.current().executeScript("PF('dlgAlbums').hide()");
			
			this.consultar();
		}
	}
	
	/**
	 * Metodo que permite precargar el album seleccionado para actualizar
	 * @param albumSeleccionado {@link Album} Album seleccionado a actualizar.
	 */
	public void cargarAlbum(Album albumSeleccionado) {
		album = albumSeleccionado;
	}

	/**
	 * @return the albums
	 */
	public List<Album> getAlbums() {
		return albums;
	}

	/**
	 * @param albums the albums to set
	 */
	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	/**
	 * @return the albumsFiltrados
	 */
	public List<Album> getAlbumsFiltrados() {
		return albumsFiltrados;
	}

	/**
	 * @param albumsFiltrados the albumsFiltrados to set
	 */
	public void setAlbumsFiltrados(List<Album> albumsFiltrados) {
		this.albumsFiltrados = albumsFiltrados;
	}

	/**
	 * @return the albumService
	 */
	public AlbumService getAlbumService() {
		return albumService;
	}

	/**
	 * @param albumService the albumService to set
	 */
	public void setAlbumService(AlbumService albumService) {
		this.albumService = albumService;
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
	 * @return the disqueras
	 */
	public List<Disquera> getDisqueras() {
		return disqueras;
	}

	/**
	 * @param disqueras the disqueras to set
	 */
	public void setDisqueras(List<Disquera> disqueras) {
		this.disqueras = disqueras;
	}

	/**
	 * @return the adminAlbumsService
	 */
	public AdminAlbumsService getAdminAlbumsService() {
		return adminAlbumsService;
	}

	/**
	 * @param adminAlbumsService the adminAlbumsService to set
	 */
	public void setAdminAlbumsService(AdminAlbumsService adminAlbumsService) {
		this.adminAlbumsService = adminAlbumsService;
	}

	/**
	 * @return the album
	 */
	public Album getAlbum() {
		return album;
	}

	/**
	 * @param album the album to set
	 */
	public void setAlbum(Album album) {
		this.album = album;
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
	
	
}
