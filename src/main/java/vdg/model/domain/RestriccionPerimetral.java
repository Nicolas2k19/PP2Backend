package vdg.model.domain;

import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "RestriccionPerimetral")
public class RestriccionPerimetral {
	
	@Id
	@Column
	private int idRestriccion;

	@Column
	private int idUsuario;

	@Column
	private int idDamnificada;
	
	@Column
	private int idVictimario;
	
	@Column
	private int distancia;
	
	@Column
	private Date fechaSentencia;

	public RestriccionPerimetral() {
		
	}

	public int getIdRestriccion() {
		return idRestriccion;
	}

	public void setIdRestriccion(int idRestriccion) {
		this.idRestriccion = idRestriccion;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdDamnificada() {
		return idDamnificada;
	}

	public void setIdDamnificada(int idDamnificada) {
		this.idDamnificada = idDamnificada;
	}

	public int getIdVictimario() {
		return idVictimario;
	}

	public void setIdVictimario(int idVictimario) {
		this.idVictimario = idVictimario;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public Date getFechaSentencia() {
		return fechaSentencia;
	}

	public void setFechaSentencia(Date fechaSentencia) {
		this.fechaSentencia = fechaSentencia;
	}
	
	
	
}
