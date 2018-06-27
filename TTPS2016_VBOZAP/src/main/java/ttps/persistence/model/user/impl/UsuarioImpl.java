package ttps.persistence.model.user.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ttps.persistence.model.access.Visualizacion;
import ttps.persistence.model.board.Pizarra;
import ttps.persistence.model.board.Publicacion;
import ttps.persistence.model.user.Usuario;

@Entity
@Table
@Inheritance(strategy=InheritanceType.JOINED)
@NamedQuery(query = "Select e from UsuarioImpl e where e.nickname = :username", name = "find by username")
@OnDelete(action = OnDeleteAction.CASCADE)
public abstract class UsuarioImpl implements Usuario, Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5408483809928314518L;

	@Id @GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="USUARIO_ID")
	private long id;

	private String nickname;

	private String password;

	private int cartelerasXpag;

	private int publicacionesXpag;

	@OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
	private List<Visualizacion> accesos;

	public UsuarioImpl(String nickname) {
		super();
		this.nickname = nickname;
	}

	public UsuarioImpl(String nickname, String password,List<Visualizacion> accesos) {
		this(nickname);
		this.password=password;
		this.accesos = accesos;
	}

	public UsuarioImpl() {
		// TODO Auto-generated constructor stub
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public List<Publicacion> leer(Pizarra pizarra) throws Exception {
		return pizarra.getPublicaciones();
	}

	@Override
	public Boolean escribir(long pizarra, Publicacion publicacion) {
		return false;
	}

	@Override
	public Boolean accesoEscritura(long pizarra) {
		return false;
	}

	public List<Visualizacion> getAccesos() {
		return accesos;
	}

	public void setAccesos(List<Visualizacion> accesos) {
		this.accesos = accesos;
	}

	@Override
	public boolean add(Visualizacion acceso) {
		if(!acceso.hasEscritura() && !hasAcceso(acceso)){
			return accesos.add(acceso);
		}
		return false;
	}

	protected boolean hasAcceso(Visualizacion acceso) {
		acceso.setUsuario(this);
		for(Visualizacion unAcceso: getAccesos()){
			if(unAcceso.getPizarra().equals(acceso.getPizarra()))
				return true;
		}

		return false;
	}

	public int getCartelerasXpag() {
		return cartelerasXpag;
	}

	public void setCartelerasXpag(int cartelerasXpag) {
		this.cartelerasXpag = cartelerasXpag;
	}

	public int getPublicacionesXpag() {
		return publicacionesXpag;
	}

	public void setPublicacionesXpag(int publicacionesXpag) {
		this.publicacionesXpag = publicacionesXpag;
	}

	public void actualizarAtributos(Usuario user){
		this.nickname = user.getNickname();
		this.password = user.getPassword();
//		this.accesos = user.getAccesos();
		this.cartelerasXpag = user.getCartelerasXpag();
		this.publicacionesXpag = user.getPublicacionesXpag();
	}

	@Override
	public String toString() {
		return "Usuario [Id =" + id + " nickname=" + nickname + " carteleras por pagina=" + cartelerasXpag + " publicaciones por pagina=" + publicacionesXpag + "Accesos=" + accesos + "]";
	}

	@Override
	public String getTipo(){
		return "USUARIO";
	}
}
