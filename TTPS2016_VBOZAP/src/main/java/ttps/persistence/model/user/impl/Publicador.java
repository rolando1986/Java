package ttps.persistence.model.user.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ttps.persistence.model.access.Visualizacion;
import ttps.persistence.model.board.Publicacion;
import ttps.persistence.model.user.Usuario;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
// @PrimaryKeyJoinColumn(name="USUARIO_ID")
@OnDelete(action = OnDeleteAction.CASCADE)
public class Publicador extends UsuarioImpl {

	/**
	 *
	 */
	private static final long serialVersionUID = 6573369658182038396L;

	@OneToMany(mappedBy = "publicador", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
//	@JsonManagedReference(value="user_publicaciones")
	private List<Publicacion> publicaciones;

	public Publicador() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Publicador(String nickname, String password, List<Visualizacion> accesos) {
		super(nickname, password, accesos);
	}

	public Publicador(String nickname, String password, List<Visualizacion> accesos, List<Publicacion> publicaciones) {
		super(nickname, password, accesos);
		this.publicaciones = publicaciones;
	}

	public Publicador(String nickname) {
		super(nickname);
	}

	public List<Publicacion> getPublicaciones() {
		return publicaciones;
	}

	public void setPublicaciones(List<Publicacion> publicaciones) {
		this.publicaciones = publicaciones;
	}

	@Override
	public Boolean escribir(long pizarra, Publicacion publicacion) {
		publicacion.setPublicador(this);
		Visualizacion acceso = buscarAcceso(pizarra);
		if(acceso != null)
			return acceso.escribir(publicacion);

		return false;
	}

	protected Visualizacion buscarAcceso(long pizarraId) {
		Visualizacion acceso = null;
		for(Visualizacion unAcceso: getAccesos()){
			if (unAcceso.getPizarra().getId() == pizarraId){
				acceso = unAcceso;
			}
		}
		return acceso;
	}

	@Override
	public Boolean accesoEscritura(long pizarra){
		Visualizacion acceso = buscarAcceso(pizarra);
		return acceso == null ? false : acceso.hasEscritura();
	}

	@Override
	public void actualizarAtributos(Usuario user) {
		super.actualizarAtributos(user);
		this.publicaciones = ((Publicador) user).getPublicaciones();
	}

	@Override
	public String toString() {
		return "Usuario [Id =" + super.getId() + " nickname=" + super.getNickname() + " carteleras por pagina="
				+ super.getCartelerasXpag() + " publicaciones por pagina=" + super.getPublicacionesXpag() + " Accesos="
				+ super.getAccesos() + " publicaciones=" + publicaciones + "]";
	}

	@Override
	public String getTipo() {
		return "PUBLICADOR";
	}

	@Override
	public boolean add(Visualizacion acceso) {
		if(!hasAcceso(acceso))
			return  getAccesos().add(acceso);

		return false;
	}
}
