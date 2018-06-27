package ttps.persistence.model.media;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.persistence.model.board.Publicacion;

@Entity
@Table
@NamedQuery(query = "Select e from Archivo e where publicacion = :ídPublicacion AND e.nombre = :nombre", name = "find by name")
public class Archivo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5811943702178508843L;

	@Id @GeneratedValue
	@Column(unique = true, nullable = false)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PUBLICACION_ID", nullable = false)
//	@JsonBackReference(value="publicacion_archivo")
	@JsonIgnore
	private Publicacion publicacion;

	private String nombre;

	private String uri;

	public Archivo() {
		super();
	}

	public Archivo(String nombre, String uRI) {
		super();
		this.nombre = nombre;
		uri = uRI;
	}

	public Archivo(String nombre, String uRI, Publicacion publicacion) {
		this(nombre,uRI);
		this.publicacion = publicacion;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	@Override
	public String toString() {
		return "Archivo [id=" + id + ", nombre=" + nombre + ", uri=" + uri + " publicacion=" + publicacion + "]";
	}
}
