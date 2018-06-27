package ttps.persistence.DAO.impl;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.PublicadorDAO;
import ttps.persistence.model.user.impl.Publicador;

@Repository
public class PublicadorDAOjpa extends GenericDAO<Publicador> implements PublicadorDAO{

	public PublicadorDAOjpa() {
		super("nickname", Publicador.class);
	}

	public String getEntityName(){
		 return "Publicador";
	}
}
