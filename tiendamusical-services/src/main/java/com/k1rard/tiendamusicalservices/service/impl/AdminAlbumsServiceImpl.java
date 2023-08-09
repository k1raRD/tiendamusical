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

import com.k1rard.tiendamusicaldata.dao.AlbumDAO;
import com.k1rard.tiendamusicaldata.dao.ArtistaDAO;
import com.k1rard.tiendamusicaldata.dao.DisqueraDAO;
import com.k1rard.tiendamusicalentities.entities.Album;
import com.k1rard.tiendamusicalentities.entities.Artista;
import com.k1rard.tiendamusicalentities.entities.Disquera;
import com.k1rard.tiendamusicalservices.service.AdminAlbumsService;

/**
 * @author k1rard
 * Clase que implementa los metodos de la logica de negocio para la administracion de albums.
 */
@Service
public class AdminAlbumsServiceImpl implements AdminAlbumsService {

	@Autowired
	private ArtistaDAO artistaDAO;
	
	@Autowired
	private DisqueraDAO disqueraDAO;
	
	@Autowired
	private AlbumDAO albumDAO;
	
	@Override
	public List<Disquera> consultarDisqueras() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("descripcion"));
		Page<Disquera> page = this.disqueraDAO.findAll(pageable);
		return page.getContent();
	}

	@Override
	public List<Artista> consultarArtistas() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("nombre"));
		Page<Artista> page = this.artistaDAO.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Album guardarAlbum(Album album) {
		
		if(album.getIdAlbum() == null) {
			album.setFechaCreacion(LocalDateTime.now());
			album.setEstatus(true);
		}
		album.setFechaModificacion(LocalDateTime.now());
		return this.albumDAO.save(album);
	}

}
