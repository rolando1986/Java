package ttps.persistence.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.AccesoDAO;
import ttps.persistence.model.access.Visualizacion;


@Service("accesoService")
@Transactional
public class AccesoServiceImpl implements AccesoService{

	@Autowired
	AccesoDAO accesoDAO;

	@Override
	public void create(Visualizacion entity) {
		accesoDAO.create(entity);
	}

	@Override
	public void modify(Visualizacion entity) {
		accesoDAO.modify(entity);
	}

	@Override
	public void delete(Visualizacion entity) {
		accesoDAO.delete(entity);

	}

	@Override
	public List<Visualizacion> find() {
		List<Visualizacion> aux = new ArrayList<Visualizacion>();
		aux.addAll(accesoDAO.find());
		return aux;
	}

	@Override
	public Visualizacion find(Serializable id) {
		return accesoDAO.find(id);
	}

	@Override
	public void create(List<Visualizacion> entity) {
		accesoDAO.create(entity);
	}

	@Override
	public void delete(Serializable id) {
		accesoDAO.delete(id);
	}

	@Override
	public void deleteAll() {
		accesoDAO.deleteAll();
	}

	@Override
	public boolean isExist(Visualizacion entity) {
		return find(entity.getId()) != null;
	}

	@Override
	public List<Visualizacion> findByUserId(Serializable id) {
		return accesoDAO.findByUserId(id);
	}
}
