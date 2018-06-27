package ttps.guarani.persistence.service;

import java.util.List;

import ttps.guarani.persistence.model.Alumno;
import ttps.guarani.persistence.model.Credencial;

public interface AlumnoService {

	public List<Alumno> find();
	public Alumno find(long id);
	public boolean chequearlogin(Credencial user);
}
