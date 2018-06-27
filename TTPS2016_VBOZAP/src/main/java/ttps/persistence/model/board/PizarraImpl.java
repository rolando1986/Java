package ttps.persistence.model.board;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ttps.persistence.model.notification.Mensaje;
import ttps.persistence.model.user.impl.Alumno;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "OBSERVADO_ID")
@OnDelete(action = OnDeleteAction.CASCADE)
public class PizarraImpl implements Pizarra, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 4944603447751628243L;

	@Id @GeneratedValue
    @Column (name =  "PIZARRA_ID" , unique = true  , nullable = false  )
	private long id;

	@Column(unique = true, nullable = false)
	private String nombre;

	private String descripcion;

	@OneToMany(cascade = {CascadeType.REFRESH}, fetch = FetchType.EAGER)
	protected List<Alumno> observers;

	@OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "pizarra")
//	@JsonManagedReference(value="pizarra_publicacion")
	private List<Publicacion> publicaciones;


	public PizarraImpl() {
		super();
	}

	public PizarraImpl(String nombre, List<Publicacion> publicaciones) {
		super();
		this.nombre = nombre;
		if (publicaciones == null)
			this.publicaciones = new ArrayList<Publicacion>();
		else
			this.publicaciones = publicaciones;
	}

	public PizarraImpl(String nombre, List<Publicacion> publicaciones, String descripcion) {
		this(nombre, publicaciones);
		this.descripcion = descripcion;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	@Override
	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	@Override
	public Boolean add(Publicacion publicacion) {
		boolean change = getPublicaciones().add(publicacion);
		if (change) {
			notificar();
		}
		return change;
	}

	@Override
	public Boolean remove(Publicacion publicacion) {
		return getPublicaciones().remove(publicacion);
	}

	private Mensaje crearMensaje() {
		return new Mensaje("Pizarra: " + getNombre(), "Se han agregado nuevas publicaciones a la pizarra");
	}

	@Override
	public String toString() {
		return "Pizarra [id=" + getId() + ", nombre=" + nombre + ", texto=" + descripcion + ", Publicaciones="
				+ publicaciones.toString() + "]";
	}

	@Override
	public void actualizarAtributos(Pizarra pizarra) {
		this.nombre = pizarra.getNombre();
		this.descripcion = pizarra.getDescripcion();
		this.publicaciones = pizarra.getPublicaciones();
		this.observers = pizarra.getObservers();
	}

	public List<Alumno> getObservers() {
		if(observers == null)
			observers = new ArrayList<Alumno>();
		return observers;
	}

	public void setObservers(List<Alumno> observers) {
		this.observers = observers;
	}

	public Boolean add(Alumno observer) {
		for(Alumno alu: getObservers()){
			if(alu.getId() == observer.getId())
				return false;
		}
		return getObservers().add(observer);
	}

	public Boolean remove(Alumno observer) {
		return getObservers().remove(observer);
	}

	/*
	 * Avisa a los notificadores que se realizaron
	 * cambios en el elemento observado
	 */
	protected void notificar(){

		for(Alumno observer: getObservers()){
			observer.enviarAviso(crearMensaje());
		}
	}
}
