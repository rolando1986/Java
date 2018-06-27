package ttps.persistence.model.user;

import java.util.List;

import ttps.persistence.model.access.Visualizacion;
import ttps.persistence.model.board.Pizarra;
import ttps.persistence.model.board.Publicacion;

public interface Usuario {

	public long getId();
	public String getNickname();
	public List<Publicacion> leer(Pizarra pizarra) throws Exception;
	public Boolean escribir(long pizarra, Publicacion publicaciones);
	/**
	 * Devuelve si un usuario tiene acceso a la pizarra
	 * @param Pizarra para verificar el acceso
	 * @return Boolean indicando si tiene acceso el usuario a la pizarra
	 * @see Boolean
	 */
	public Boolean accesoEscritura(long pizarra);
	/**
	 * Devuelve los accesos asociados al usuario
	 * @return List<Visualizacion>
	 * @see List<Visualizacion>
	 */
	public List<Visualizacion> getAccesos();
	public void setAccesos(List<Visualizacion> accesos);
	public int getCartelerasXpag();
	public void setCartelerasXpag(int cartelerasXpag);
	public int getPublicacionesXpag();
	public String getTipo();
	public void setPublicacionesXpag(int publicacionesXpag);
	public void setNickname(String nickname);
	public String getPassword();
	public void setPassword(String password);
	public void actualizarAtributos(Usuario user);
	public boolean add(Visualizacion acceso);
}
