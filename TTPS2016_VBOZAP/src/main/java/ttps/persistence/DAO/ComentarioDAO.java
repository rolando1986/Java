package ttps.persistence.DAO;
import java.util.List;

import ttps.persistence.model.board.Comentario;

public interface ComentarioDAO extends DAO<Comentario>{
	public abstract List<Comentario> find(String tituloPublicacion);

	public abstract List<Comentario> findByPublicacionId(long id);
	public Comentario findByPublicacionId(long publicacionId, long id);
}
