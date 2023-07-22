package com.k1rard.tiendamusicaldata.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author k1rard
 * Clase generica que representa los metodos del CRUD que se reutilzan en todas las entidades del proyecto
 * E - Significa la entidad a utilizar en el CRUD
 * R - Significa el repositorio a utilizar de JPA para ejecutar el CRUD
 */
public class CommonDAO<E, R extends JpaRepository<E, Long>> {
	
    @Autowired
    protected R repository;

    /**	
     * Metodo que permite consultar una lista de datos paginable de una entidad
     * @param desde {@link Integer} Indica a partir de que valor se obtendrían los resultados.
     * @param hasta {@link Integer} Indica el límite de resultados paginados a obtener.
     * @param orderBy {@link String} Indica a partir de que campo se ordenaran los resultados
     * @return {@link List} Lista con los datos consultados y paginados.
     */
    public List<E> consultarListaPaginable(int desde, int hasta, String orderBy){
        Pageable pageable = PageRequest.of(desde, hasta, Sort.by(orderBy));
        Page<E> page = this.repository.findAll(pageable);
        return page.getContent();
    }

    /**
     * Metodo que permite persistir la informacion de cualquier entidad.
     * @param e {@link Object} Objeto o entidad a persistir.
     * @return {@link Object} Objeto recuperado despues de persistir el registro.
     */
    public E guardar(E e){
        return this.repository.save(e);
    }

    /**
     * Metodo que permite actualizar la informacion de cualquier entidad.
     * @param e {@link Object} Objeto o entidad a actualizar.
     * @return {@link Object} Objeto recuperado despues de actualizar el registro.
     */
    public E actualizar(E e){
        return this.repository.save(e);
    }

    /**
     * Metodo que permite eliminar la informacion de cualquier entidad.
     * @param e {@link Object} Objeto o entidad a eliminar.
     */
    public void eliminar(E e){
        this.repository.delete(e);
    }
}
