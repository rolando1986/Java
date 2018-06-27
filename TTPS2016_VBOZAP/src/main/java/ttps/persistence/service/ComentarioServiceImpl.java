package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.ComentarioDAO;
import ttps.persistence.model.board.Comentario;


@Service("coemntarioService")
@Transactional
public class ComentarioServiceImpl implements ComentarioService{

	@Autowired
	ComentarioDAO comentarioDAO;

	@Override
	public void create(Comentario entity) {
		if(entity.getPublicacion().comentariosHabilitados())
			comentarioDAO.create(entity);
	}

	@Override
	public void modify(Comentario entity) {
		if(entity.getPublicacion().comentariosHabilitados())
			comentarioDAO.modify(entity);
	}

	@Override
	public void delete(Comentario entity) {
		if(entity.getPublicacion().comentariosHabilitados())
			comentarioDAO.delete(entity);
	}

	@Override
	public List<Comentario> find() {
		return comentarioDAO.find();
	}

	@Override
	public Comentario find(Serializable id) {
		return comentarioDAO.find(id);
	}

	@Override
	public void create(List<Comentario> entity) {
		comentarioDAO.create(entity);
	}

	@Override
	public void delete(Serializable id) {
		comentarioDAO.delete(id);
	}

	@Override
	public void deleteAll() {
		comentarioDAO.deleteAll();
	}

	@Override
	public List<Comentario> findByPublicacionId(long id) {
		return comentarioDAO.findByPublicacionId(id);
	}

	@Override
	public Comentario findByPublicacionId(long publicacionId, long id) {
		return comentarioDAO.findByPublicacionId(publicacionId, id);
	}
}
