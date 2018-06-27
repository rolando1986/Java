package ttps.persistence.model.notification;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ttps.persistence.model.user.Usuario;

@Entity
@DiscriminatorValue("FACEBOOK")
public class NotificacionFacebook extends Medio{
	/**
	 *
	 */
	private static final long serialVersionUID = -4805742914363102884L;

	public NotificacionFacebook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Boolean enviarAviso(Mensaje msj, Usuario user){
		//TODO Accion especifica de la notificacion para construir y enviar un msj
		return true;
	}

	@Override
	public String toString() {
		return "Notificacion [id=" + super.getId() + " tipo=Facebook]";
	}

	@Override
	public String getTipo() {
		return "FACEBOOK";
	}
}
