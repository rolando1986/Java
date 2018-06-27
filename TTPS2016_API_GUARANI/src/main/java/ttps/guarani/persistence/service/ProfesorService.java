package ttps.guarani.persistence.service;

import java.util.List;

import ttps.guarani.persistence.model.Credencial;
import ttps.guarani.persistence.model.Profesor;

public interface ProfesorService {

	public List<Profesor> find();
	public Profesor find(long id);
	public boolean chequearlogin(Credencial user);
}
