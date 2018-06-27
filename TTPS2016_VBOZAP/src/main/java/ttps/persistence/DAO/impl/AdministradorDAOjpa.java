package ttps.persistence.DAO.impl;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.AdministradorDAO;
import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.model.user.impl.Administrador;

@Repository
public class AdministradorDAOjpa extends GenericDAO<Administrador> implements AdministradorDAO{

	public AdministradorDAOjpa() {
		super("nickname", Administrador.class);
	}

	public String getEntityName(){
		 return "Administrador";
	}

	@Override
	public PizarraImpl obtenerPizarra(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}
}
