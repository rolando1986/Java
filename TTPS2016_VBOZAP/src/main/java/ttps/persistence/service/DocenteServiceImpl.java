package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.DocenteDAO;
import ttps.persistence.model.user.impl.Docente;


@Service("docenteService")
@Transactional
public class DocenteServiceImpl implements DocenteService{

	@Autowired
	DocenteDAO userDAO;

	@Override
	public void create(Docente entity) {
		userDAO.create(entity);
	}

	@Override
	public void modify(Docente entity) {
		userDAO.modify(entity);
	}

	@Override
	public void delete(Docente entity) {
		userDAO.delete(entity);

	}

	@Override
	public List<Docente> find() {
		return userDAO.find();
	}

	@Override
	public Docente find(Serializable id) {
		return userDAO.find(id);
	}

	@Override
	public void create(List<Docente> entity) {
		create(entity);
	}

	@Override
	public void delete(Serializable id) {
		userDAO.delete(id);
	}

	@Override
	public void deleteAll() {
		userDAO.deleteAll();
	}

	@Override
	public boolean isUserExist(Docente user) {
		// TODO Auto-generated method stub
		return false;
	}
}
