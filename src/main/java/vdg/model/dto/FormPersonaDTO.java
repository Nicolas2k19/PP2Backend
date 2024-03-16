package vdg.model.dto;

import vdg.model.domain.Direccion;
import vdg.model.domain.Persona;
import vdg.model.domain.Usuario;

public class FormPersonaDTO {

	private Persona persona;
	private Usuario usuario;
	private Direccion direccion;
	private String foto;
	
	public Persona getPersona() {
		return this.persona;
	}
	
	public Usuario getUsuario( ) {
		return this.usuario;
	}

	public Direccion getDireccion() {
		return this.direccion;
	}
	
	public String getFoto() {
		return this.foto;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
}
