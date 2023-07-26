package com.k1rard.tiendamusicalservices.service;

import com.k1rard.tiendamusicalentities.entities.Persona;

/**
 * @author k1rard
 * Interface que realiza la logica de negocio para el inicio de sesion de la persona.
 */
public interface LoginService {

    /**
     * Metodo que permite consultar un usuario que trata de ingresar a sesion en la tienda musical.
     * @param usuario {@link String} Usuario capturado por la persona.
     * @param password {@link String} Password capturada por la persona.
     * @return {@link Persona} Usuario encontrado en la base de datos.
     */
    Persona consultarUsuarioLogin(String usuario, String password);
}
