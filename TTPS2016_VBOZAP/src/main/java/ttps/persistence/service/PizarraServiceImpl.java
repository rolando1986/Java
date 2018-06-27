package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.PizarraDAO;
import ttps.persistence.model.board.PizarraImpl;


@Service("pizarraService")
@Transactional
public class PizarraServiceImpl implements PizarraService{

	@Autowired
	PizarraDAO pizarraDAO;

	@Override
	public void create(PizarraImpl entity) {
		pizarraDAO.create((PizarraImpl) entity);
	}

	@Override
	public void modify(PizarraImpl entity) {
		pizarraDAO.modify(entity);
	}

	@Override
	public void delete(PizarraImpl entity) {
		pizarraDAO.delete(entity);

	}

	@Override
	public List<PizarraImpl> find() {
		return pizarraDAO.find();
	}

	@Override
	public boolean isExist(PizarraImpl entity){
		return findByName(entity.getNombre()) != null;

	}

	@Override
	public PizarraImpl findByName(String nombre) {
		return pizarraDAO.findByName(nombre);
	}

	@Override
	public PizarraImpl find(Serializable id) {
		return pizarraDAO.find(id);
	}

	@Override
	public void create(List<PizarraImpl> entity) {
		for(PizarraImpl p: entity){
			create(p);
		}
	}

	@Override
	public void delete(Serializable id) {
		pizarraDAO.delete(id);
	}

	@Override
	public void deleteAll() {
		pizarraDAO.deleteAll();
	}
}
