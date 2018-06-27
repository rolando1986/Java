package ttps.guarani.persistence.model;

import java.io.Serializable;

public class Credencial implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String usuario;
	private String clave;

	public Credencial() {
		super();
	}

	public Credencial(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
