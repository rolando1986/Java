package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.board.Publicacion;


public interface PublicacionService {

	public void create(Publicacion entity);
	public void modify (Publicacion entity);
	public void delete (Publicacion entity);
	public List<Publicacion> find();
	public Publicacion find(Serializable id);
	public List<Publicacion> findByPizarraId(Serializable id);
	public Publicacion findByPizarraId(Serializable pizarraId, Serializable id);
	void create(List<Publicacion> entity);
	void delete(Serializable id);
	void deleteAll();
	public boolean isExist(Publicacion entity);
}
