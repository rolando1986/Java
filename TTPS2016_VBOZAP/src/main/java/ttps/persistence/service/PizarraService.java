package ttps.persistence.service;

import java.io.Serializable;
import java.util.List;

import ttps.persistence.model.board.PizarraImpl;


public interface PizarraService {

	public void create(PizarraImpl entity);
	public void modify (PizarraImpl entity);
	public void delete (PizarraImpl entity);
	public List<PizarraImpl> find();
	public PizarraImpl find(Serializable id);
	void create(List<PizarraImpl> entity);
	void delete(Serializable id);
	void deleteAll();
	public boolean isExist(PizarraImpl entity);
	public PizarraImpl findByName(String nombre);
}
