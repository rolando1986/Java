package ttps.persistence.DAO.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ttps.persistence.DAO.DAO;



public class GenericDAO<T> implements DAO<T> {

	protected EntityManager em;
	private final String columnOrder;
	protected Class<T> persistentClass;

	public GenericDAO(String columnOrder, Class<T> class1) {
		this.columnOrder = columnOrder;
		this.persistentClass = class1;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.em = entityManager;
	}

	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public void delete(Serializable id) {
		getEntityManager().remove(em.getReference(persistentClass, (long) id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find() {
		return getEntityManager().createQuery(("from " + getEntityName() + " e order by e." + getColumnOrder())).getResultList();
	}

	@Override
	public T find(Serializable id) {
		return getEntityManager().find(persistentClass, id);
	}

	@Override
	public void modify(T entity) {
		// getEntityManager().persist(entity);
		getEntityManager().merge(entity);
	}

	@Override
	public void create(List<T> entity) {
		for (T arch : entity)
			getEntityManager().persist(arch);
		// getEntityManager().flush();
	}

	@Override
	public void deleteAll() {
		getEntityManager().createQuery("DELETE from " + getEntityName()).executeUpdate();
	}

	@Override
	public String getEntityName() {
		// subclassResponsability
		return null;
	}

	@Override
	public String getColumnOrder() {
		return columnOrder;
	}
}