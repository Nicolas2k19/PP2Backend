package vdg.model.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class UbicacionesEntrenamiento {
	
	
	@Column
	String dia;
	@Id
	@Column
	private int idDato;
	@Column
	private BigDecimal latitud;
	@Column
	private BigDecimal longitud;
	@Column
	private Timestamp fecha;
	@ManyToOne
	@JoinColumn(name = "idPersona")
    private Persona idPersona;
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public int getIdDato() {
		return idDato;
	}
	public void setIdDato(int idDato) {
		this.idDato = idDato;
	}
	public BigDecimal getLatitud() {
		return latitud;
	}
	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}
	public BigDecimal getLongitud() {
		return longitud;
	}
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public Persona getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Persona idPersona) {
		this.idPersona = idPersona;
	}
	

	

}
