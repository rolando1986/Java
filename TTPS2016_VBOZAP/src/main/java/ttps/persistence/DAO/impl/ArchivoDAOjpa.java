package ttps.persistence.DAO.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.ArchivoDAO;
import ttps.persistence.model.media.Archivo;

@Repository
public class ArchivoDAOjpa extends GenericDAO<Archivo> implements ArchivoDAO{

	public ArchivoDAOjpa() {
		super("nombre", Archivo.class);
	}

	@Override
	public String getEntityName() {
		return "Archivo";
	}

	@Override
	public Archivo findByName(Serializable idPub, Serializable name) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
		+ " WHERE PUBLICACION_ID = "
		+ idPub + " AND nombre = " + name + ")" + " order by " + getColumnOrder());
		return (Archivo) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Archivo> findByPublicacionId(Serializable idPub) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
		+ " WHERE PUBLICACION_ID = "
		+ idPub + ")" + " order by " + getColumnOrder());
		return query.getResultList();
	}

	@Override
	public Archivo findByPublicacionId(Serializable idPub, Serializable id) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
		+ " WHERE PUBLICACION_ID = "
		+ idPub + " AND id = " + id + ")" + " order by " + getColumnOrder());
		return  (query.getResultList().isEmpty() ? null : (Archivo) query.getSingleResult());
	}

	@Override
	public Archivo findByName(String name) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
		+ " WHERE nombre = " + name + ")" + " order by " + getColumnOrder());
		return (Archivo) query.getSingleResult();
	}
}