package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.user.impl.Administrador;

public interface AdministradorService {

	public void create(Administrador entity);
	public void modify (Administrador entity);
	public void delete (Administrador entity);
	public List<Administrador> find();
	public Administrador find(Serializable id);
	void create(List<Administrador> entity);
	void delete(Serializable id);
	void deleteAll();
	public boolean isUserExist(Administrador user);
}
