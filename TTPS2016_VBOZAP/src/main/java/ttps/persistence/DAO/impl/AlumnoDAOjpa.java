package ttps.persistence.DAO.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.AlumnoDAO;
import ttps.persistence.model.user.impl.Alumno;

@Repository
public class AlumnoDAOjpa extends GenericDAO<Alumno> implements AlumnoDAO{

	public AlumnoDAOjpa() {
		super("nickname",Alumno.class);
	}

	@Override
	public String getEntityName() {
		return "Alumno";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Alumno> seguidores(long pizarraId) {
		Query query = getEntityManager().createQuery("from " + getEntityName()
				+ "WHERE USUARIO_ID IN (SELECT observers_USUARIO_ID FROM pizarraimpl_alumno WHERE Pizarraimpl_PIZARRA_ID = "
				+ pizarraId + ")" + " e order by e." + getColumnOrder());
		return (List<Alumno>) query.getResultList();
	}
}
