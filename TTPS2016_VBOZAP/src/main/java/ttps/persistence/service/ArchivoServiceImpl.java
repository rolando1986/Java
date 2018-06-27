package ttps.persistence.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ttps.persistence.DAO.ArchivoDAO;
import ttps.persistence.model.media.Archivo;


@Service("archivoService")
@Transactional
public class ArchivoServiceImpl implements ArchivoService{

	@Autowired
	ArchivoDAO archivoDAO;

	@Override
	public void create(Archivo entity) {
		archivoDAO.create(entity);
	}

	@Override
	public void modify(Archivo entity) {
		archivoDAO.modify(entity);
	}

	@Override
	public void delete(Archivo entity) {
		archivoDAO.delete(entity);

	}

	@Override
	public List<Archivo> find() {
		List<Archivo> aux = new ArrayList<Archivo>();
		aux.addAll(archivoDAO.find());
		return aux;
	}

	@Override
	public Archivo find(Serializable id) {
		return archivoDAO.find(id);
	}

	@Override
	public Archivo findByName(Serializable idPub, Serializable name) {
		return archivoDAO.findByName(idPub,name);
	}

	@Override
	public void create(List<Archivo> entity) {
		for(Archivo user: entity){
			create(user);
		}
	}

	@Override
	public void delete(Serializable id) {
		archivoDAO.delete(id);
	}

	@Override
	public void deleteAll() {
		archivoDAO.deleteAll();
	}

	@Override
	public boolean isExist(Archivo arch) {
		return find(arch.getId()) != null;
	}

	@Override
	public List<Archivo> findByPublicacionId(Serializable idPub) {
		return archivoDAO.findByPublicacionId(idPub);
	}

	@Override
	public Archivo findByPublicacionId(Serializable idPub, Serializable id) {
		return archivoDAO.findByPublicacionId(idPub, id);
	}

	@Override
	public Archivo findByName(String name) {
		return archivoDAO.findByName(name);
	}
}
