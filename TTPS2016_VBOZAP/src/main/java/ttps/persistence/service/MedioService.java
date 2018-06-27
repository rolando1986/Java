package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.notification.Medio;

public interface MedioService {

	public void create(Medio entity);
	public void modify (Medio entity);
	public void delete (Medio entity);
	public List<Medio> find();
	public Medio find(Serializable id);
	void create(List<Medio> entity);
	void delete(Serializable id);
	void deleteAll();
}
