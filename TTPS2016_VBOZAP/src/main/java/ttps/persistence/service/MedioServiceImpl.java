package ttps.persistence.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.MedioDAO;
import ttps.persistence.model.notification.Medio;


@Service("MedioService")
@Transactional
public class MedioServiceImpl implements MedioService{

	@Autowired
	MedioDAO MedioDAO;

	@Override
	public void create(Medio entity) {
		MedioDAO.create(entity);
	}

	@Override
	public void modify(Medio entity) {
		MedioDAO.modify(entity);
	}

	@Override
	public void delete(Medio entity) {
		MedioDAO.delete(entity);

	}

	@Override
	public List<Medio> find() {
		List<Medio> aux = new ArrayList<Medio>();
		aux.addAll(MedioDAO.find());
		return aux;
	}

	@Override
	public Medio find(Serializable id) {
		return MedioDAO.find(id);
	}

	@Override
	public void create(List<Medio> entity) {
		for(Medio user: entity){
			create(user);
		}
	}

	@Override
	public void delete(Serializable id) {
		MedioDAO.delete(id);
	}

	@Override
	public void deleteAll() {
		MedioDAO.deleteAll();
	}
}
