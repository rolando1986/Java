package ttps.persistence.DAO.impl;

import org.springframework.stereotype.Repository;

import ttps.persistence.DAO.MedioDAO;
import ttps.persistence.model.notification.Medio;

@Repository
public class MedioDAOjpa extends GenericDAO<Medio> implements MedioDAO{

	public MedioDAOjpa() {
		super("id", Medio.class);
	}

	@Override
	public String getEntityName() {
		return "Medio";
	}
}
