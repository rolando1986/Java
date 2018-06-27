package ttps.persistence.DAO;

import java.io.Serializable;

import ttps.persistence.model.user.Usuario;
import ttps.persistence.model.user.impl.*;
public interface UsuarioImplDAO extends DAO<UsuarioImpl>{

	public Usuario findByUserName(Serializable username);

}
