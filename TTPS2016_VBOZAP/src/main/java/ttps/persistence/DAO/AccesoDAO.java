package ttps.persistence.DAO;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.access.Visualizacion;

public interface AccesoDAO extends DAO<Visualizacion>{
	public List<Visualizacion> findByUserId(Serializable id);
}
