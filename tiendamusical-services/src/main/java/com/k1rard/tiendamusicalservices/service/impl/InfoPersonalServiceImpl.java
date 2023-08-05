/**
 * 
 */
package com.k1rard.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k1rard.tiendamusicaldata.dao.PersonaDAO;
import com.k1rard.tiendamusicalentities.entities.Persona;
import com.k1rard.tiendamusicalservices.service.InfoPersonalService;

/**
 * @author k1rard
 * Clase que implementa los metodos de la logica de negocio de informacion personal
 */
@Service
public class InfoPersonalServiceImpl implements InfoPersonalService{
	
	@Autowired
	private PersonaDAO personaDAO;

	@Override
	public Persona actualizarPersona(Persona persona) {
		persona.setFechaModificacion(LocalDateTime.now());
		return this.personaDAO.save(persona);
	}

}
