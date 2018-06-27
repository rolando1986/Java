package ttps.guarani.persistence.model;

import java.io.Serializable;

public class Usuario implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String nombres;
	private String apellidos;
	private String email;
	private String clave;


	public Usuario(long id, String nombres, String apellidos, String email, String clave) {
		super();
		this.id = id;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.email = email;
		this.setClave(clave);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipo() {
		return null;
	}

	@Override
	public String toString() {
		return "Usuario [Id =" + id + " nombres=" + nombres + " apellidos=" + nombres + " email=" + email + " tipo=" + getTipo() + "]";
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
