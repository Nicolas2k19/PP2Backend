package vdg.model.domain;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "PruebaDeVida")
public class PruebaDeVida {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int idPruebaDeVida;

	@Column
	private Timestamp fecha;

	@Column
	private String descripcion;
	
	@Column
	@Enumerated(EnumType.STRING)
	private EstadoPruebaDeVida estado;
	
	@Column
	private int idRestriccion;
	
	@Column
	private int idPersonaRestriccion;
	
	public PruebaDeVida() {
		// TODO Auto-generated constructor stub
	}

	public int getIdPruebaDeVida() {
		return idPruebaDeVida;
	}

	public void setIdPruebaDeVida(int idPruebaDeVida) {
		this.idPruebaDeVida = idPruebaDeVida;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public EstadoPruebaDeVida getEstado() {
		return estado;
	}

	public void setEstado(EstadoPruebaDeVida estado) {
		this.estado = estado;
	}

	public int getIdRestriccion() {
		return idRestriccion;
	}

	public void setIdRestriccion(int idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	public int getIdPersonaRestriccion() {
		return idPersonaRestriccion;
	}

	public void setIdPersonaRestriccion(int idPersonaRestriccion) {
		this.idPersonaRestriccion = idPersonaRestriccion;
	}
	
}
