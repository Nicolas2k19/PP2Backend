package vdg.model.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PruebaDeVidaMultiple")
public class PruebaDeVidaMultiple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idPruebaDeVidaMultiple;

    @Column
    private String descripcion;

    @Column
    private Long idPruebaDeVida;

    public PruebaDeVidaMultiple() {
        super();
    }

    public Long getIdPruebaDeVidaMultiple() {
        return idPruebaDeVidaMultiple;
    }

    public void setIdPruebaDeVidaMultiple(Long id) {
        this.idPruebaDeVidaMultiple = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	public Long getIdPruebaDeVida() {
		return idPruebaDeVida;
	}

	public void setIdPruebaDeVida(Long idPruebaDeVida) {
		this.idPruebaDeVida = idPruebaDeVida;
	}


}
