package ttps.persistence.DAO.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.ComentarioDAO;
import ttps.persistence.model.board.Comentario;

@Repository
public class ComentarioDAOjpa extends GenericDAO<Comentario> implements ComentarioDAO{

	public ComentarioDAOjpa() {
		super("nombre", Comentario.class);
	}

	@Override
	public String getEntityName() {
		// TODO Auto-generated method stub
		return "Comentario";
	}

	@Override
	public List<Comentario> find(String tituloPublicacion) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Comentario> findByPublicacionId(long id) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
		+ " WHERE PUBLICACION_ID = "
		+ id + ")" + " order by " + getColumnOrder());
		return query.getResultList();
	}

	@Override
	public Comentario findByPublicacionId(long publicacionId, long id) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
		+ " WHERE PUBLICACION_ID = "
		+ publicacionId + " and id =" + id + ")" + " order by " + getColumnOrder());
		return  (query.getResultList().isEmpty() ? null : (Comentario) query.getSingleResult());
	}
}