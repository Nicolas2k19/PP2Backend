package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Persona;

public interface PersonaRepository extends Repository<Persona, Integer>{
	
	public List<Persona> findAll();
	public Persona save(Persona persona);
	public void delete(Persona persona);
	public List<Persona> findById(int idPersona);
	public List<Persona> findByDni(String dni);
	public List<Persona> findByIdUsuario(int idUsuario);
}
