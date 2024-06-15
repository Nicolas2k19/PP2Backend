package vdg.repository;


import org.springframework.data.repository.Repository;
import vdg.model.domain.UbicacionesEntrenamiento;


public interface UbicacionEntrenamientoRepository extends Repository<UbicacionesEntrenamiento, Integer>{
	
	public UbicacionesEntrenamiento findByIdPersona(int idDamnificada);
	public void save(UbicacionesEntrenamiento ubicacion);
	public void delete(UbicacionesEntrenamiento ubicacion);
}
