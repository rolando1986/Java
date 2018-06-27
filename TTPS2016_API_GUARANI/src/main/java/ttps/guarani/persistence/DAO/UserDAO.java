package ttps.guarani.persistence.DAO;

import java.util.List;

import ttps.guarani.persistence.model.Usuario;
public interface UserDAO{

	public List<Usuario> find();
	public Usuario find(long id);
	Usuario find(String nombre, String clave);
}
