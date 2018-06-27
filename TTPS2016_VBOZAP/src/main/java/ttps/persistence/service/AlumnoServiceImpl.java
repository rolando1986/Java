package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.AlumnoDAO;
import ttps.persistence.model.user.impl.Alumno;


@Service("alumnoService")
@Transactional
public class AlumnoServiceImpl implements AlumnoService{

	@Autowired
	AlumnoDAO userDAO;

	@Override
	public void create(Alumno entity) {
		userDAO.create(entity);
	}

	@Override
	public void modify(Alumno entity) {
		userDAO.modify(entity);
	}

	@Override
	public void delete(Alumno entity) {
		userDAO.delete(entity);

	}

	@Override
	public List<Alumno> find() {
		return userDAO.find();
	}

	@Override
	public Alumno find(Serializable id) {
		return userDAO.find(id);
	}

	@Override
	public void create(List<Alumno> entity) {
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
	public boolean isUserExist(Alumno user) {
		// TODO Auto-generated method stub
		return false;
	}
}
