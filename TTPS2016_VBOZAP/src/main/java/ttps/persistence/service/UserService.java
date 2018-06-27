package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.user.Usuario;

public interface UserService {

	public void create(Usuario entity);
	public void modify (Usuario entity);
	public void delete (Usuario entity);
	public List<Usuario> find();
	public Usuario find(Serializable id);
	void create(List<Usuario> entity);
	void delete(Serializable id);
	void deleteAll();
	public boolean isUserExist(Usuario user);
	public Usuario findByUserName(Serializable username);
}
