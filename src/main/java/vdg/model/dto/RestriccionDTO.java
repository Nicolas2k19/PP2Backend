package vdg.model.dto;

import vdg.model.domain.Persona;
import vdg.model.domain.RestriccionPerimetral;
import vdg.model.domain.Usuario;

public class RestriccionDTO {

	private Persona damnificada;
	private Persona victimario;
	private Usuario administrativo;
	private RestriccionPerimetral restriccion;

	public RestriccionDTO(Persona damnificada, Persona victimario, Usuario administrativo, RestriccionPerimetral restriccion) {
		this.damnificada = damnificada;
		this.victimario = victimario;
		this.administrativo = administrativo;
		this.restriccion = restriccion;
	}

	public Persona getDamnificada() {
		return damnificada;
	}

	public void setVictima(Persona damnificada) {
		this.damnificada = damnificada;
	}

	public Persona getVictimario() {
		return victimario;
	}

	public void setVictimario(Persona victimario) {
		this.victimario = victimario;
	}

	public Usuario getAdministrativo() {
		return administrativo;
	}

	public void setAdministrativo(Usuario administrativo) {
		this.administrativo = administrativo;
	}

	public RestriccionPerimetral getRestriccion() {
		return restriccion;
	}

	public void setRestriccion(RestriccionPerimetral restriccion) {
		this.restriccion = restriccion;
	}

	public void setDamnificada(Persona damnificada) {
		this.damnificada = damnificada;
	}
	
}
