package ttps.persistence.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.PublicadorDAO;
import ttps.persistence.model.user.impl.Publicador;


@Service("publicadorService")
@Transactional
public class PublicadorServiceImpl implements PublicadorService{

	@Autowired
	PublicadorDAO userDAO;

	@Override
	public void create(Publicador entity) {
		userDAO.create(entity);
	}

	@Override
	public void modify(Publicador entity) {
		userDAO.modify(entity);
	}

	@Override
	public void delete(Publicador entity) {
		userDAO.delete(entity);
	}

	@Override
	public List<Publicador> find() {
		ArrayList<Publicador> list = new ArrayList<Publicador>();
		for(Publicador p: userDAO.find()){
			if(p.getTipo() == "PUBLICADOR")
				list.add(p);
		}
		return list;
	}

	@Override
	public Publicador find(Serializable id) {

		return userDAO.find(id);
	}

	@Override
	public void create(List<Publicador> entity) {
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
	public boolean isUserExist(Publicador user) {
		// TODO Auto-generated method stub
		return false;
	}
}
