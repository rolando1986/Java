package ttps.persistence.model.user.impl;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import ttps.persistence.model.access.Visualizacion;

@Entity
@Table
//@PrimaryKeyJoinColumn(name="USUARIO_ID")
public class Administrador extends Publicador {

	/**
	 *
	 */
	private static final long serialVersionUID = 8480552320040105051L;

	public Administrador() {
		super();
	}

	public Administrador(String nickname, String password, List<Visualizacion> acceso) {
		super(nickname, password, acceso);
	}

	@Override
	public String getTipo() {
		return "ADMINISTRADOR";
	}
}
