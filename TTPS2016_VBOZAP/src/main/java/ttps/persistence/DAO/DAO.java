package ttps.persistence.DAO;

import java.io.Serializable;
import java.util.List;

public interface DAO<T> {

	public void create(T entity);
	public void modify (T entity);
	public void delete (T entity);
	public List<T> find();
	public T find(Serializable id);
	public String getEntityName();
	public String getColumnOrder();
	void create(List<T> entity);
	void delete(Serializable id);
	void deleteAll();
}
