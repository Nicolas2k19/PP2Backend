package vdg.model.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetalleRestriccion")
public class DetalleRestriccion {

    @Id
    @Column(name = "idDetalle")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idRestriccion")
    private RestriccionPerimetral restriccion;
    
    @Column
    private String Juez;
    
     @Column
     private String Detalle;
     
     @Column
    private String LinkSeguimiento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idJuzgado")
    private Juzgado juzgado;


    public DetalleRestriccion() {
    }


	public int getIdDetalle() {
		return idDetalle;
	}


	public void setIdDetalle(int idDetalle) {
		this.idDetalle = idDetalle;
	}


	public RestriccionPerimetral getRestriccion() {
		return restriccion;
	}


	public void setRestriccion(RestriccionPerimetral restriccion) {
		this.restriccion = restriccion;
	}


	public String getJuez() {
		return Juez;
	}


	public void setJuez(String juez) {
		Juez = juez;
	}


	public String getDetalle() {
		return Detalle;
	}


	public void setDetalle(String detalle) {
		Detalle = detalle;
	}


	public String getLinkSeguimiento() {
		return LinkSeguimiento;
	}


	public void setLinkSeguimiento(String linkSeguimiento) {
		LinkSeguimiento = linkSeguimiento;
	}


	public Juzgado getJuzgado() {
		return juzgado;
	}


	public void setJuzgado(Juzgado juzgado) {
		this.juzgado = juzgado;
	}
    
}

