package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.board.Comentario;


public interface ComentarioService {

	public void create(Comentario entity);
	public void modify (Comentario entity);
	public void delete (Comentario entity);
	public List<Comentario> find();
	public Comentario find(Serializable id);
	void create(List<Comentario> entity);
	void delete(Serializable id);
	void deleteAll();
	public List<Comentario> findByPublicacionId(long id);
	public Comentario findByPublicacionId(long publicacionId, long id);
}
