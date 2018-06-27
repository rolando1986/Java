package ttps.persistence.model.user.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import ttps.persistence.model.access.Visualizacion;
import ttps.persistence.model.user.Usuario;

@Entity
@Table
//@PrimaryKeyJoinColumn(name="PUBLICADOR_ID")
public class Docente extends Publicador {

	/**
	 *
	 */
	private static final long serialVersionUID = -5762526756281967166L;

	private  ArrayList<String> ciclosAsignados;

	public Docente() {
	}

	public Docente(String nickname, String password,List<Visualizacion> accesos, ArrayList<String> ciclos) {
		super(nickname, password, accesos);
		ciclosAsignados = ciclos;
	}

	public List<String> getCiclosAsignados() {
		return ciclosAsignados;
	}

	public void setCiclosAsignados(ArrayList<String> ciclosAsignados) {
		this.ciclosAsignados = ciclosAsignados;
	}

	public List<Alumno> getInteresados(long pizarraId) {
		List<Alumno> seguidores = new ArrayList<Alumno>();
		try {
			Visualizacion acceso = buscarAcceso(pizarraId);
			if(acceso != null){
				seguidores.addAll(acceso.getPizarra().getObservers());
			}
		} catch (Exception e) {
			//TODO loguear error;
		}
		return seguidores;
	}

	public List<Alumno> getInteresados() {
		List<Alumno> seguidores = new ArrayList<Alumno>();
		try {
			for(Visualizacion p: getAccesos())
				seguidores.addAll(p.getPizarra().getObservers());
		} catch (Exception e) {
			//TODO loguear error;
		}
		return seguidores;
	}

	@Override
	public void actualizarAtributos(Usuario user){
		super.actualizarAtributos(user);
		this.ciclosAsignados = (ArrayList<String>) ((Docente) user).getCiclosAsignados();
	}

	@Override
	public String toString() {
		return "Usuario [Id =" + super.getId() + " nickname=" + super.getNickname() + " carteleras por pagina=" + super.getCartelerasXpag() + " publicaciones por pagina=" +
				super.getPublicacionesXpag() + "Accesos=" + super.getAccesos() + " ciclos asignados=" + ciclosAsignados + "]";
	}

	@Override
	public String getTipo() {
		return "DOCENTE";
	}
}
