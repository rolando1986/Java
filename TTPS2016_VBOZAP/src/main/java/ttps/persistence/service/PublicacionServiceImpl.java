package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.PublicacionDAO;
import ttps.persistence.model.board.Publicacion;


@Service("publicacionService")
@Transactional
public class PublicacionServiceImpl implements PublicacionService{

	@Autowired
	PublicacionDAO publicacionDAO;

	@Override
	public void create(Publicacion entity) {
		publicacionDAO.create(entity);
	}

	@Override
	public void modify(Publicacion entity) {
		publicacionDAO.modify(entity);
	}

	@Override
	public void delete(Publicacion entity) {
		publicacionDAO.delete(entity);

	}

	@Override
	public List<Publicacion> find() {
		return publicacionDAO.find();
	}

	@Override
	public Publicacion find(Serializable id) {
		return publicacionDAO.find(id);
	}

	@Override
	public void create(List<Publicacion> entity) {
		publicacionDAO.create(entity);
	}

	@Override
	public void delete(Serializable id) {
		publicacionDAO.delete(id);
	}

	@Override
	public void deleteAll() {
		publicacionDAO.deleteAll();
	}

	@Override
	public boolean isExist(Publicacion entity) {
		return find(entity.getId()) != null;
	}

	@Override
	public List<Publicacion> findByPizarraId(Serializable id) {
		return publicacionDAO.findByPizarraId(id);
	}

	@Override
	public Publicacion findByPizarraId(Serializable pizarraId, Serializable id) {
		return publicacionDAO.findByPizarraId(pizarraId, id);
	}
}
