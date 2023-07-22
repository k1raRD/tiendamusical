package com.k1rard.tiendamusicaldata.dao.impl;

import com.k1rard.tiendamusicaldata.common.CommonDAO;
import com.k1rard.tiendamusicaldata.dao.PersonaDAO;
import com.k1rard.tiendamusicalentities.entities.Persona;

/**
 * @author k1rard
 * Clase que implementa el CRUD Generico y las funciones de la interface de PersonaDAO.
 */
public class PersonaDAOImpl extends CommonDAO<Persona, PersonaDAO> {

    /**
     * Metodo que permite consultar una persona por su usuario y password
     * @param usuario {@link String} usuario capturado por la persona
     * @param password {@link String} password capturado por la persona
     * @return {@link Persona} persona encontrada.
     */
    public Persona findUsuarioAndPassword(String usuario, String password){
        return repository.findByUsuarioAndPassword(usuario, password);
    }
}
