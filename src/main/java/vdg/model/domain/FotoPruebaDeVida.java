package vdg.model.domain;

import javax.persistence.*;

@Entity
@Table(name = "FotoPruebaDeVida")
public class FotoPruebaDeVida {
	
	@Id
	@Column
	private int idFoto;

	@Column
	@Lob
	private byte[] foto;
	
	@Column
	private int idPruebaDeVida;
	
	public FotoPruebaDeVida() {
		// TODO Auto-generated constructor stub
	}

	public int getIdFoto() {
		return idFoto;
	}

	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public int getIdPruebaDeVida() {
		return idPruebaDeVida;
	}

	public void setIdPruebaDeVida(int idPruebaDeVida) {
		this.idPruebaDeVida = idPruebaDeVida;
	}

}
