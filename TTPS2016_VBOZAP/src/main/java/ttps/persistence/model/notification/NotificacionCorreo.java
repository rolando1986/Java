package ttps.persistence.model.notification;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ttps.persistence.model.user.Usuario;

@Entity
@DiscriminatorValue("CORREO")
public class NotificacionCorreo extends Medio{

	/**
	 *
	 */
	private static final long serialVersionUID = 1314283514739039366L;



	public NotificacionCorreo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificacionCorreo(String credencial) {
		super(credencial);
	}

	public Boolean enviarAviso(Mensaje msj, Usuario user){
		//TODO Accion especifica de la notificacion para construir y enviar un msj
		return true;
	}

	@Override
	public String toString() {
		return "Notificacion [id=" + super.getId() + " tipo=Correo]";
	}

	@Override
	public String getTipo() {
		return "CORREO";
	}
}
