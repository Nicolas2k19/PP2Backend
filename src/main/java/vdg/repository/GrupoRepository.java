package vdg.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import vdg.model.domain.Grupo;

public interface GrupoRepository extends Repository<Grupo, Integer> {

	/**Obtiene todos los grupo de la base de datos**/
	public List<Grupo> findAll();
	/**
	 * Encuentra al grupo que coincida con el id pasado por parametro
	 * **/
	public Grupo findByidGrupo(int idgrupo);
	/**
	 * Guarda al grupo pasado por párametro
	 * **/
	public void save(Grupo grupo);
	/**
	 * Elimina al grupo pasado por parametro
	 * ***/
	public void delete(Grupo grupo);
	

}
