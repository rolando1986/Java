package ttps.persistence.DAO;
import java.util.List;

import ttps.persistence.model.board.Publicacion;
import ttps.persistence.model.user.impl.Alumno;
import ttps.persistence.model.user.impl.Docente;
public interface DocenteDAO extends DAO<Docente>{

	abstract Publicacion obtenerPublicacion(String nombrePizarra, String nombrePubli);
	abstract List<Alumno> listadoInteresados(String nombrePublicacion);

}
