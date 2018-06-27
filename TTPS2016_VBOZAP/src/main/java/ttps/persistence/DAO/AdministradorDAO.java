package ttps.persistence.DAO;
import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.model.user.impl.Administrador;
public interface AdministradorDAO extends DAO<Administrador>{

	abstract PizarraImpl obtenerPizarra(String nombre);
}
