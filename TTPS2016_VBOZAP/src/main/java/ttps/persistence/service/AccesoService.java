package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.access.Visualizacion;


public interface AccesoService {

	public void create(Visualizacion entity);
	public void modify (Visualizacion entity);
	public void delete (Visualizacion entity);
	public List<Visualizacion> find();
	public List<Visualizacion> findByUserId(Serializable id);
	public Visualizacion find(Serializable id);
	void create(List<Visualizacion> entity);
	void delete(Serializable id);
	void deleteAll();
	public boolean isExist(Visualizacion user);
}
