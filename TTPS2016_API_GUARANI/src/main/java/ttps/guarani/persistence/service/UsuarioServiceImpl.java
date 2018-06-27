package ttps.guarani.persistence.service;

import org.springframework.beans.factory.annotation.Autowired;

import ttps.guarani.persistence.DAO.UserDAOMockImpl;

public class UsuarioServiceImpl{

	@Autowired
	private UserDAOMockImpl dao;

	public UserDAOMockImpl getDAO() {
		return dao;
	}

	public void setDAO(UserDAOMockImpl dao) {
		this.dao = dao;
	}
}
