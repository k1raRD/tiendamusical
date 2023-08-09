/**
 * 
 */
package com.k1rard.tiendamusicalservices.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.k1rard.tiendamusicaldata.dao.AlbumDAO;
import com.k1rard.tiendamusicalentities.dto.AlbumTopTenDTO;
import com.k1rard.tiendamusicalentities.entities.Album;
import com.k1rard.tiendamusicalservices.service.AlbumService;

/**
 * @author k1rard
 * Clase que implementa los metodos de la la logica de negocio de album.
 */
@Service
public class AlbumServiceImpl implements AlbumService {
	
	@Autowired
	private AlbumDAO albumDAO;

	@Override
	public List<AlbumTopTenDTO> consultarAlbumsTopTen() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("ca.album.nombre"));
		Page<AlbumTopTenDTO> page = this.albumDAO.consultarAlbumsTopTen(pageable);
		return page.getContent();
	}

	@Override
	public List<Album> consultarAlbums() {
		Pageable pageable = PageRequest.of(0, 20, Sort.by("nombre"));
		Page<Album> page = this.albumDAO.findAll(pageable);
		return page.getContent();
	}

}
