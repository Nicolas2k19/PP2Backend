package vdg.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
	
	@Id
	@Column
	private int idUsuario;

	@Column
	private String email;

	@Column
	private String contrasena;
	
	@Column
	@Enumerated(EnumType.STRING)
	private RolDeUsuario rolDeUsuario;
	
	public Usuario() {
		
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public RolDeUsuario getRolDeUsuario() {
		return rolDeUsuario;
	}

	public void setRolDeUsuario(RolDeUsuario rolDeUsuario) {
		this.rolDeUsuario = rolDeUsuario;
	}
	
}
