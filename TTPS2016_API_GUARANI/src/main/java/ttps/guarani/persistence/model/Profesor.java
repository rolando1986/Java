package ttps.guarani.persistence.model;

import java.util.ArrayList;

public class Profesor extends Usuario {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private  ArrayList<Integer> anios;


	public Profesor(long id, String nombres, String apellidos, String email, String clave, ArrayList<Integer> ciclos) {
		super(id,nombres,apellidos,email,clave);
		anios = ciclos;
	}

	@Override
	public String toString() {
		return "Usuario [Id =" + getId() + " nombres=" + getNombres() + " apellidos=" + getApellidos() + " email=" + getEmail() + " tipo=" + getTipo() + " ciclos asignados=" + anios + "]";
	}

	@Override
	public String getTipo() {
		return "PROFESOR";
	}
}
