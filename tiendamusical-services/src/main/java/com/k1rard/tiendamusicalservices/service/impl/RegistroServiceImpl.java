/**
 * 
 */
package com.k1rard.tiendamusicalservices.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.k1rard.tiendamusicaldata.dao.PersonaDAO;
import com.k1rard.tiendamusicalentities.entities.Persona;
import com.k1rard.tiendamusicalentities.entities.Rol;
import com.k1rard.tiendamusicalservices.enums.RolEnum;
import com.k1rard.tiendamusicalservices.service.RegistroService;

/**
 * @author k1rard
 * Clase que implementa los metodos de logica de negocio para el registro de usuarios en el sistema 
 */
@Service
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	private PersonaDAO personaDAO;
	
	@Override
	public Persona registrarPersona(Persona persona) {
		persona.getRol().setIdRol(RolEnum.CLIENTE.getClave());	
		persona.setNumeroIdentificacion("1234567");
		persona.setFechaCreacion(LocalDateTime.now());
		persona.setFechaModificacion(LocalDateTime.now());
		persona.setEstatus(true);
		persona.getCarrito().setPersona(persona);
		return personaDAO.save(persona);
	}

}
