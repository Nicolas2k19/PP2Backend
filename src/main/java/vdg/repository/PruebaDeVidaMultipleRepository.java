package vdg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vdg.model.domain.PruebaDeVidaMultiple;

@Repository
public interface PruebaDeVidaMultipleRepository extends JpaRepository<PruebaDeVidaMultiple, Long> {
}