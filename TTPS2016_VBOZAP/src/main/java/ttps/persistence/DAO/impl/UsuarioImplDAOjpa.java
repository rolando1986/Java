package ttps.persistence.DAO.impl;

import java.io.Serializable;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.UsuarioImplDAO;
import ttps.persistence.model.user.Usuario;
import ttps.persistence.model.user.impl.UsuarioImpl;

@Repository
public class UsuarioImplDAOjpa extends GenericDAO<UsuarioImpl> implements UsuarioImplDAO{

	public UsuarioImplDAOjpa() {
		super("nickname", UsuarioImpl.class);
	}

	public String getEntityName(){
		return "UsuarioImpl";
	}

	@Override
	public Usuario findByUserName(Serializable username) {
		Query query = getEntityManager().createNamedQuery("find by username");
		query.setParameter("username", username);
		return  (query.getResultList().isEmpty() ? null : (Usuario) query.getSingleResult());
	}
}
