package ttps.persistence.DAO.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.PublicacionDAO;
import ttps.persistence.model.board.Publicacion;

@Repository
public class PublicacionDAOjpa extends GenericDAO<Publicacion> implements PublicacionDAO{

	public PublicacionDAOjpa() {
		super("fecha", Publicacion.class);
	}

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "Publicacion";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Publicacion> findByPizarraId(Serializable pizarraId) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
				+ " WHERE PIZARRA_ID = " + pizarraId + ")" + " e order by e." + getColumnOrder());
		return (List<Publicacion>) query.getResultList();
	}

	@Override
	public Publicacion findByPizarraId(Serializable pizarraId, Serializable id) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
		+ " WHERE PIZARRA_ID = "
		+ pizarraId + " and id =" + id + ")" + " e order by e." + getColumnOrder());
		return  (query.getResultList().isEmpty() ? null : (Publicacion) query.getSingleResult());
	}
}
