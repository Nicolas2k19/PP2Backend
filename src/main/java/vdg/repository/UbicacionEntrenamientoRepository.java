package vdg.repository;


import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Persona;
import vdg.model.domain.UbicacionesEntrenamiento;


public interface UbicacionEntrenamientoRepository extends Repository<UbicacionesEntrenamiento, Integer>{
	
	public List<UbicacionesEntrenamiento> findAllByIdPersona(Persona idPersona);
	public void save(UbicacionesEntrenamiento ubicacion);
	public void delete(UbicacionesEntrenamiento ubicacion);
}
