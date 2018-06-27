package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.AdministradorDAO;
import ttps.persistence.model.user.impl.Administrador;


@Service("adminService")
@Transactional
public class AdministradorServiceImpl implements AdministradorService{

	@Autowired
	AdministradorDAO userDAO;

	@Override
	public void create(Administrador entity) {
		userDAO.create(entity);
	}

	@Override
	public void modify(Administrador entity) {
		userDAO.modify(entity);
	}

	@Override
	public void delete(Administrador entity) {
		userDAO.delete(entity);

	}

	@Override
	public List<Administrador> find() {
		return userDAO.find();
	}

	@Override
	public Administrador find(Serializable id) {
		return userDAO.find(id);
	}

	@Override
	public void create(List<Administrador> entity) {
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
	public boolean isUserExist(Administrador user) {
		// TODO Auto-generated method stub
		return false;
	}
}
