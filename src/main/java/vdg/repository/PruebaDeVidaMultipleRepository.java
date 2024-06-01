package vdg.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vdg.model.domain.PruebaDeVidaMultiple;

@Repository
public interface PruebaDeVidaMultipleRepository extends JpaRepository<PruebaDeVidaMultiple, Long> {

	@Query("SELECT p FROM PruebaDeVidaMultiple p WHERE p.idPersona = :idPersona")
	public Collection<PruebaDeVidaMultiple> findAllByIdPersona(@Param("idPersona") Long idPersona);

}