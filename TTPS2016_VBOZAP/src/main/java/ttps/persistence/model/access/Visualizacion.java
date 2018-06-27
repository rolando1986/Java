package ttps.persistence.model.access;

import java.io.Serializable;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.persistence.model.board.Pizarra;
import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.model.board.Publicacion;
import ttps.persistence.model.user.impl.UsuarioImpl;

@Entity
@Table
@Inheritance // Herencia con tabla simple
@DiscriminatorColumn(name="TIPO_ACCESO")
@DiscriminatorValue("VISUALIZACION")
public class Visualizacion implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7818997051238659620L;

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	protected long id;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USUARIO_ID", nullable = false)
	@JsonIgnore
	protected UsuarioImpl usuario;

	@OneToOne
//	@JsonIgnore
	protected PizarraImpl pizarra;

	private Boolean visible;

	private long posicion;

	public Visualizacion() {
	}

	public Visualizacion(Pizarra pizarra) {
		this.pizarra = (PizarraImpl) pizarra;
		visible = true;
	}

	public Visualizacion(long posicion, Pizarra pizarra) {
		this(pizarra);
		this.posicion = posicion;
	}

	public UsuarioImpl getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioImpl usuario) {
		this.usuario = usuario;
	}

	public void setPizarra(PizarraImpl pizarra) {
		this.pizarra = pizarra;
	}

	public void setPosicion(long posicion) {
		this.posicion = posicion;
	}

	public PizarraImpl getPizarra() {
		return pizarra;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	/*
	 * Lee una pizarra y retorna todas sus publicaciones
	 * @return List<Publicacion> publicaciones que contiene la pizarra
	 * @see List<Publicacion>
	 */
	public List<Publicacion> leer() throws Exception {
		return this.pizarra.getPublicaciones();

	}

	/*
	 * Escribe una publicacion en la pizarra y retorna un boolean indicando
	 * el resultado de la operacion
	 * @param Pizarra en donde se agregara la publicacion
	 * @param Publicacion a ser agregada
	 * @return Boolean indicando el resultado de la operacion
	 * @see Boolean
	 */
	public Boolean escribir(Publicacion publicaciones) {
		return false;
	}
	public Boolean getVisible() {
		return visible;
	}

	public Boolean cambiarVisibilidad() {
		visible = !visible;
		return visible;
	}

	public Boolean hasEscritura() {
		return false;
	}

	public long getPosicion() {
		return posicion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void actualizarAtributos(Visualizacion acceso) {
		 this.posicion = acceso.getPosicion();
	     this.visible = acceso.getVisible();
//	     this.pizarra = acceso.getPizarra();
	}

	@Override
	public String toString() {
		return "Acceso [id=" + id + " posicion=" + posicion + " Acceso=Visualizacion" + " visible="+ getVisible() + " pizarra=" + pizarra.toString() + "]";
	}

	public String getTipo() {
		return "LECTURA";
	}
}
