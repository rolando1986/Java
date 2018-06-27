package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.user.impl.Docente;

public interface DocenteService {

	public void create(Docente entity);
	public void modify (Docente entity);
	public void delete (Docente entity);
	public List<Docente> find();
	public Docente find(Serializable id);
	void create(List<Docente> entity);
	void delete(Serializable id);
	void deleteAll();
	public boolean isUserExist(Docente user);
}
