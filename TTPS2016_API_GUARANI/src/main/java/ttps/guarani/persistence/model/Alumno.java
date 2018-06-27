package ttps.guarani.persistence.model;

public class Alumno extends Usuario {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public Alumno(long id, String nombres, String apellidos, String email, String clave) {
		super(id,nombres,apellidos,email,clave);
	}

	@Override
	public String getTipo() {
		return "ALUMNO";
	}
}
