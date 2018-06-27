package ttps.persistence.DAO.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.PizarraDAO;
import ttps.persistence.model.board.PizarraImpl;

@Repository
public class PizarraDAOjpa extends GenericDAO<PizarraImpl> implements PizarraDAO{

	public PizarraDAOjpa() {
		super("nombre", PizarraImpl.class);
	}

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "PizarraImpl";
	}

	@Override
	public void eliminarPublicacion(String nombrePublicacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void publicacion(String nombrePublicacion) {
		// TODO Auto-generated method stub

	}

	@Override
	public PizarraImpl findByName(String nombre) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
		+ " WHERE nombre = '" + nombre + "')");
		return (query.getResultList().isEmpty() ? null : (PizarraImpl) query.getSingleResult());
	}
}