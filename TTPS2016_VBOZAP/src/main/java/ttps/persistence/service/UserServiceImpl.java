package ttps.persistence.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.UsuarioImplDAO;
import ttps.persistence.model.user.Usuario;
import ttps.persistence.model.user.impl.UsuarioImpl;


@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UsuarioImplDAO userDAO;

	@Override
	public void create(Usuario entity) {
		userDAO.create((UsuarioImpl) entity);
	}

	@Override
	public void modify(Usuario entity) {
		userDAO.modify((UsuarioImpl) entity);
	}

	@Override
	public void delete(Usuario entity) {
		userDAO.delete((UsuarioImpl) entity);
	}

	@Override
	public List<Usuario> find() {
		List<Usuario> aux = new ArrayList<Usuario>();
		aux.addAll(userDAO.find());
		return aux;
	}

	@Override
	public Usuario find(Serializable id) {
		return userDAO.find(id);
	}

	@Override
	public void create(List<Usuario> entity) {
		for(Usuario user: entity){
			create(user);
		}
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
	public boolean isUserExist(Usuario user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario findByUserName(Serializable username) {
		return userDAO.findByUserName(username);
	}
}
