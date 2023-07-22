package com.k1rard.tiendamusicalservices.service.impl;

import com.k1rard.tiendamusicaldata.dao.PersonaDAO;
import com.k1rard.tiendamusicalentities.entities.Persona;
import com.k1rard.tiendamusicalservices.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author k1rard
 * Clase que implementa las funciones para la logica negocio, para la pantalla de login.
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PersonaDAO personaDAOImpl;

    @Override
    public Persona consultarUsuarioLogin(String usuario, String password) {
        return this.personaDAOImpl.findByUsuarioAndPassword(usuario, password);
    }
}
