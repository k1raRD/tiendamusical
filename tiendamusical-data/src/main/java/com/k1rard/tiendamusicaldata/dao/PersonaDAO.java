package com.k1rard.tiendamusicaldata.dao;

import com.k1rard.tiendamusicalentities.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author k1rard
 * Clase DAO que realiza el CRUD con SpringJPA para la tabla de persona.
 */
public interface PersonaDAO extends JpaRepository<Persona, Long> {

    /**
     * Metodo que permite consultar el usuario que trata de ingresar a sesion
     * @param usuario {@link String} usuario capturado por la persona.
     * @param password {@link String} password capturado por la persona.
     * @return {@link Persona} Objeto con la persona encontrada.
     */
    @Query("SELECT p FROM Persona p WHERE p.usuario = ?1 AND p.password = ?2")
    Persona findByUsuarioAndPassword(String usuario, String password);
}
