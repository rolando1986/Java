package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.user.impl.Publicador;

public interface PublicadorService {

	public void create(Publicador entity);
	public void modify (Publicador entity);
	public void delete (Publicador entity);
	public List<Publicador> find();
	public Publicador find(Serializable id);
	void create(List<Publicador> entity);
	void delete(Serializable id);
	void deleteAll();
	public boolean isUserExist(Publicador user);
}
