package ttps.persistence.DAO.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.AccesoDAO;
import ttps.persistence.model.access.Visualizacion;

@Repository
public class AccesoDAOjpa extends GenericDAO<Visualizacion> implements AccesoDAO{

	public AccesoDAOjpa() {
		super("posicion", Visualizacion.class);
	}

	@Override
	public String getEntityName() {
		return "Visualizacion";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Visualizacion> findByUserId(Serializable id) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
		+ " WHERE USUARIO_ID = " + id + ")" + " order by " + getColumnOrder());
		return query.getResultList();
	}
}