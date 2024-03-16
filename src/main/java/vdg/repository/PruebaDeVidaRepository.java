package vdg.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import vdg.model.domain.EstadoPruebaDeVida;
import vdg.model.domain.PruebaDeVida;

public interface PruebaDeVidaRepository extends Repository<PruebaDeVida, Integer>{

	public List<PruebaDeVida> findByIdRestriccion(int idRestriccion);
	public List<PruebaDeVida> findByIdPersonaRestriccionAndEstadoOrderByFechaDesc(int idPersonaRestriccion, EstadoPruebaDeVida estado);
	public List<PruebaDeVida> findByIdPersonaRestriccionOrderByFechaDesc(int idPersonaRestriccion);
	public PruebaDeVida save(PruebaDeVida pruebaDeVida);
}
