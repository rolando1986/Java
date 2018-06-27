package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.user.impl.Alumno;

public interface AlumnoService {

	public void create(Alumno entity);
	public void modify (Alumno entity);
	public void delete (Alumno entity);
	public List<Alumno> find();
	public Alumno find(Serializable id);
	void create(List<Alumno> entity);
	void delete(Serializable id);
	void deleteAll();
	public boolean isUserExist(Alumno user);
}
