package ttps.persistence.model.board;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.persistence.model.media.Archivo;
import ttps.persistence.model.user.impl.Publicador;

@Entity
@Table
@OnDelete(action = OnDeleteAction.CASCADE)
public class Publicacion implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 2779539641706507506L;

	private int MAX_SIZE = 3;

	@Id @GeneratedValue
	@Column(name = "PUBLICACION_ID", unique = true, nullable = false)
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PIZARRA_ID", nullable = false)
	@JsonIgnore
//	@JsonBackReference(value="pizarra_publicacion")
	private PizarraImpl pizarra;

	private String titulo;

	private String texto;

	@OneToMany(cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "publicacion")
//	@JsonManagedReference(value="publicacion_comentarios")
	private List<Comentario> comentarios;

	@ManyToOne
	@JoinColumn(name = "publicador_USUARIO_ID", nullable = true)
//	@JsonBackReference(value="user_publicaciones")
	@JsonIgnore
	private Publicador publicador;

	private Boolean comentariosHabilitados;

	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "publicacion")
//	@JsonManagedReference(value="publicacion_archivo")
	private List<Archivo> archivos;

	private Timestamp fecha;

	public Publicacion() {
		super();
		this.comentariosHabilitados = true;
		this.fecha = new Timestamp(Calendar.getInstance().getTime().getTime());
		this.comentarios = new ArrayList<Comentario>();
		this.archivos = new ArrayList<Archivo>(MAX_SIZE);
	}

	public Publicacion(String titulo, String texto, PizarraImpl pizarra) {
		this();
		this.titulo = titulo;
		this.texto = texto;
		this.pizarra = pizarra;
	}

	public Publicacion(String titulo, String texto, List<Comentario> comentarios) {
		super();
		this.titulo = titulo;
		this.texto = texto;
		this.comentarios = comentarios;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Publicador getPublicador() {
		return publicador;
	}

	public void setPublicador(Publicador publicador) {
		this.publicador = publicador;
	}

	public List<Archivo> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<Archivo> archivos) {
		this.archivos = archivos;
	}

	public Boolean comentariosHabilitados() {
		return comentariosHabilitados;
	}

	public void toogleComentariosHabilitados(Boolean comentariosHabilitados) {
		this.comentariosHabilitados = !comentariosHabilitados;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
		if(getPizarra() != null)
			getPizarra().notificar();
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
		if(getPizarra() != null)
			getPizarra().notificar();
	}

	public Boolean add(Comentario comentario) {
		boolean change = false;
		if(comentariosHabilitados){
			change = this.comentarios.add(comentario);
			if(change){
				comentario.setPublicacion(this);
				getPizarra().notificar();
			}
		}
		return change;
	}

	public Boolean remove(Comentario comentario) {
		return this.comentarios.remove(comentario);
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public boolean add(Archivo file) {
		if(archivos.size() < MAX_SIZE ){
			getPizarra().notificar();
			return archivos.add(file);
		}
		else
			return false;
	}

	public boolean remove(Archivo file) {
		getPizarra().notificar();
		return this.archivos.remove(file);
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public PizarraImpl getPizarra() {
		return pizarra;
	}

	public void setPizarra(PizarraImpl pizarra) {
		this.pizarra = pizarra;
		this.setAttributes();
	}

	public int getMAX_SIZE() {
		return MAX_SIZE;
	}

	public void setMAX_SIZE(int mAX_SIZE) {
		MAX_SIZE = mAX_SIZE;
	}

	public Boolean getComentariosHabilitados() {
		return comentariosHabilitados;
	}

	public void setComentariosHabilitados(Boolean comentariosHabilitados) {
		this.comentariosHabilitados = comentariosHabilitados;
	}

	public void actualizarAtributos(Publicacion pub) {
		this.titulo = pub.getTitulo();
		this.texto = pub.getTexto();
//		this.pizarra = pub.getPizarra();
//		this.comentarios = pub.getComentarios();
//		this.archivos = pub.getArchivos();
		this.comentariosHabilitados = pub.getComentariosHabilitados();
//		this.publicador = pub.getPublicador();
//		this.observers = pub.getObservers();
		getPizarra().notificar();
	}

	@Override
	public String toString() {
		return "Publicacion [Id =" + id + " titulo=" + titulo + ", texto=" + texto + "]";
	}

	private void setAttributes(){
		if(this.fecha == null)
			this.fecha = new Timestamp(Calendar.getInstance().getTime().getTime());
		if(this.comentarios == null)
			this.comentarios = new ArrayList<Comentario>();
		if(this.archivos == null)
			this.archivos = new ArrayList<Archivo>(MAX_SIZE);
	}
}
