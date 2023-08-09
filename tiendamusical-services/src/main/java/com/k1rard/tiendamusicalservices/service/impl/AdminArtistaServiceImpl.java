/**
 * 
 */
package com.k1rard.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.k1rard.tiendamusicaldata.dao.ArtistaDAO;
import com.k1rard.tiendamusicaldata.dao.GeneroDAO;
import com.k1rard.tiendamusicaldata.dao.NacionalidadDAO;
import com.k1rard.tiendamusicaldata.dao.SubGeneroDAO;
import com.k1rard.tiendamusicalentities.entities.Artista;
import com.k1rard.tiendamusicalentities.entities.Genero;
import com.k1rard.tiendamusicalentities.entities.Nacionalidad;
import com.k1rard.tiendamusicalentities.entities.SubGenero;
import com.k1rard.tiendamusicalservices.service.AdminArtistaService;

/**
 * @author k1rard
 * Clase que implementa los metodos de la logica de negocio para la administracion de artistas.
 */
@Service
public class AdminArtistaServiceImpl implements AdminArtistaService{

	@Autowired
	private ArtistaDAO artistaDAO;
	
	@Autowired
	private NacionalidadDAO nacionalidadDAO;
	
	@Autowired
	private GeneroDAO generoDAO;
	
	@Autowired
	private SubGeneroDAO subGeneroDAO;
	
	@Override
	public List<Artista> consultarArtistas() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("nombre"));
		Page<Artista> page = this.artistaDAO.findAll(pageable);
		return page.getContent();
	}

	@Override
	public List<Nacionalidad> consultarNacionalidades() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("descripcion"));
		Page<Nacionalidad> page = this.nacionalidadDAO.findAll(pageable);
		return page.getContent();
	}

	@Override
	public List<Genero> consultarGeneros() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("descripcion"));
		Page<Genero> page = this.generoDAO.findAll(pageable);
		return page.getContent();
	}

	@Override
	public List<SubGenero> consultarSubGeneroPorGenero(Long idGenero) {
		return this.subGeneroDAO.findAllByGenero(idGenero);
	}

	@Override
	public Artista guardarArtista(Artista artista) {
		if(artista.getIdArtista() == null) {
			artista.setFechaCreacion(LocalDateTime.now());
			artista.setEstatus(true);
		}
		artista.setFechaModificacion(LocalDateTime.now());
		
		return this.artistaDAO.save(artista);
	}

}
