package ttps.persistence.model.board;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table
@Entity
public class Comentario implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name = "COMENTARIO_ID", unique = true, nullable = false)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PUBLICACION_ID", nullable = true)
//	@JsonBackReference(value="publicacion_comentarios")
	@JsonIgnore
	private Publicacion publicacion;

	private String nombre;

	private String texto;

	private Timestamp fecha;

	public Comentario() {
		this.fecha = new Timestamp(Calendar.getInstance().getTime().getTime());
	}

	public Comentario(String nombre, String texto) {
		super();
		this.nombre = nombre;
		this.texto = texto;
		this.fecha = new Timestamp(Calendar.getInstance().getTime().getTime());
	}

	public Comentario(String nombre, String texto, Publicacion publicacion) {
		this(nombre,texto);
		this.publicacion = publicacion;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public long getId() {
		return id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.fecha = new Timestamp(Calendar.getInstance().getTime().getTime());
		this.publicacion = publicacion;
	}

	public void actualizarAtributos(Comentario comentario) {
		this.nombre = comentario.getNombre();
		this.texto = comentario.getTexto();
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", nombre=" + nombre + ", texto=" + texto + ", publicaciones=" + publicacion + "]";
	}
}
