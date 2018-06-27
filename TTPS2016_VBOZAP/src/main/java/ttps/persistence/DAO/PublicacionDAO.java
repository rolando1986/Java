package ttps.persistence.DAO;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.board.Publicacion;

public interface PublicacionDAO extends DAO<Publicacion>{
	abstract List<Publicacion> findByPizarraId(Serializable pizarraId);
	abstract Publicacion findByPizarraId(Serializable pizarraId, Serializable id);
}
