package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.Juzgado;



public interface JuzgadoRepository extends Repository<Juzgado, Integer> {
	
	
	public Juzgado save(Juzgado juzgado);
	
	public void delete(Juzgado juzgado);
	
	public Juzgado findByidJuzgado(int id);

	public List<Juzgado> findAll();
}
