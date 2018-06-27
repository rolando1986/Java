package ttps.persistence.DAO;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.media.Archivo;

public interface ArchivoDAO extends DAO<Archivo>{

	public abstract Archivo findByName(Serializable idPub, Serializable name);

	public abstract List<Archivo> findByPublicacionId(Serializable idPub);

	public abstract Archivo findByPublicacionId(Serializable idPub, Serializable id);

	public abstract Archivo findByName(String name);
}
