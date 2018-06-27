package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.media.Archivo;

public interface ArchivoService {

	public void create(Archivo entity);
	public void modify (Archivo entity);
	public void delete (Archivo entity);
	public List<Archivo> find();
	public Archivo find(Serializable id);
	void create(List<Archivo> entity);
	void delete(Serializable id);
	void deleteAll();
	public boolean isExist(Archivo user);
	Archivo findByName(Serializable idPub, Serializable name);
	public List<Archivo> findByPublicacionId(Serializable idPub);
	public Archivo findByPublicacionId(Serializable idPub, Serializable id);
	public Archivo findByName(String name);
}
