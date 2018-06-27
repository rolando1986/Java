 package ttps.persistence.model.notification;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ttps.persistence.model.user.Usuario;
import ttps.persistence.model.user.impl.Alumno;

@Entity
@Table
@Inheritance
@DiscriminatorColumn(name="TIPO_MEDIO")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Medio implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -1147125279340757411L;

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="MEDIO_ID")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ALUMNO_ID", nullable = true)
	@JsonIgnore
	private Alumno alumno;

	public Medio() {
		super();
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String credencial;

	public Medio(String credencial) {
		super();
		this.credencial = credencial;
	}

	/*
	 * @param mensaje para enviar al cliente
	 * @param cliente que recibira el mensaje
	 * @return boolean indicando si se envio o no el mensaje
	 * @see Boolean
	 */
	public Boolean enviarAviso(Mensaje msj, Usuario user){
		return false;
	}

	/*
	 * @return String con las credenciales del medio para notificar cambios
	 * en carteleras
	 * @see String
	 */
	public String getCredencial() {
		return credencial;
	}

	/*
	 * @param credencial del cliente para enviar una notificacion
	 */
	public void setCredencial(String credencial) {
		this.credencial = credencial;
	}

	@Override
	public String toString() {
		return "Medio [id=" + id + "]";
	}

	public void actualizarAtributos(Medio medio) {
		this.setCredencial(medio.getCredencial());
	}

	public String getTipo(){
		return null;
	}
}
