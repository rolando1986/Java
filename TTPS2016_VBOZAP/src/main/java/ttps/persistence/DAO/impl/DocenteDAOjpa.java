package ttps.persistence.DAO.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.DocenteDAO;
import ttps.persistence.model.board.Publicacion;
import ttps.persistence.model.user.impl.Alumno;
import ttps.persistence.model.user.impl.Docente;

@Repository
public class DocenteDAOjpa extends GenericDAO<Docente> implements DocenteDAO{

	public DocenteDAOjpa() {
		super("nickname", Docente.class);
	}

	public String getEntityName(){
		return "Docente";
	}

	@Override
	public Publicacion obtenerPublicacion(String nombrePizarra, String nombrePubli) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Alumno> listadoInteresados(String nombrePublicacion) {
		// TODO Auto-generated method stub
		return null;
	}
}
