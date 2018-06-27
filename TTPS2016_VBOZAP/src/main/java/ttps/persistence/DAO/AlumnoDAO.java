package ttps.persistence.DAO;

import java.util.List;

import ttps.persistence.model.user.impl.Alumno;
public interface AlumnoDAO extends DAO<Alumno>{

	public abstract List<Alumno> seguidores(long pizarraId);
}
