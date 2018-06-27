package ttps.persistence.model.user.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ttps.persistence.model.access.Visualizacion;
import ttps.persistence.model.notification.Medio;
import ttps.persistence.model.notification.Mensaje;

@Entity
@Table
//@PrimaryKeyJoinColumn(name="USUARIO_ID")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Alumno extends UsuarioImpl {

	/**
	 *
	 */
	private static final long serialVersionUID = -2957814681735707322L;

	@OneToMany(cascade = {CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER, mappedBy = "alumno")
	private List<Medio> medios;

	public List<Medio> getMedios() {
		return medios;
	}

	public void setMedios(List<Medio> medios) {
		this.medios = medios;
	}

	public void addMedio(Medio medios) {
		this.medios.add(medios);
	}

	public Alumno() {
		super();
	}

	public Alumno(String nickname, String password, List<Visualizacion> accesos) {
		super(nickname, password, accesos);
	}

	/*
	 * Envia notificaciones al cliente
	 * @param Mensaje para enviar al cliente
	 */
	public void enviarAviso(Mensaje msj){
		for(Medio medio: medios){
			medio.enviarAviso(msj,this);
		}
	}

	@Override
	public Boolean accesoEscritura(long pizarra) {
		return false;
	}

	@Override
	public String toString() {
		return "Usuario [Id =" + super.getId() + " nickname=" + super.getNickname() + " carteleras por pagina=" + super.getCartelerasXpag() + " publicaciones por pagina=" +
				super.getPublicacionesXpag() + "Accesos=" + super.getAccesos() + " medios=" + medios + "]";
	}

	@Override
	public String getTipo() {
		return "ALUMNO";
	}
}
