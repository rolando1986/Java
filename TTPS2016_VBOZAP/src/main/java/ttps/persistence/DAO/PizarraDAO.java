package ttps.persistence.DAO;

import ttps.persistence.model.board.PizarraImpl;

public interface PizarraDAO extends DAO<PizarraImpl> {
	abstract void publicacion(String nombrePublicacion);
	abstract void eliminarPublicacion(String nombrePublicacion);
	abstract PizarraImpl findByName(String nombre);
}
