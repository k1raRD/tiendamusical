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

import com.k1rard.tiendamusicaldata.dao.RolDAO;
import com.k1rard.tiendamusicalentities.entities.Rol;
import com.k1rard.tiendamusicalservices.service.AdminPerfilesService;

/**
 * @author k1rard
 * Clase que implementa los servicios o metodos de logica de negocio para la administracion de perfiles.
 */
@Service
public class AdminPerfilesServiceImpl implements AdminPerfilesService {
	
	@Autowired
	private RolDAO rolDAO;

	@Override
	public List<Rol> consultarPerfiles() {
		Pageable pageable = PageRequest.of(0,  20, Sort.by("nombre"));
		Page<Rol> page = this.rolDAO.findAll(pageable);
		return page.getContent();
	}

	@Override
	public Rol guardarPefil(Rol rol) {
		if(rol.getIdRol() == null) {
			rol.setFechaCreacion(LocalDateTime.now());
			rol.setEstatus(true);
		}
		
		rol.setFechaModificacion(LocalDateTime.now());
		
		return this.rolDAO.save(rol);
	}

}
