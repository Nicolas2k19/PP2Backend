package vdg.model.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "grupo")
/**Clase que representa a la entidad grupo**/
public class Grupo {
	@Id
	@Column
	public Integer idGrupo;
	@Column
	int idUsuario;
	@Column
	int idRestriccion;
	@Column
	String horaInicial;
	@Column 
	String horaFinal;

	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public int getIdRestriccion() {
		return idRestriccion;
	}
	public void setIdRestriccion(int idRestriccion) {
		this.idRestriccion = idRestriccion;
	}
	public String getHoraInicial() {
		return horaInicial;
	}
	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}
	public String getHoraFinal() {
		return horaFinal;
	}
	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}
	
	
}