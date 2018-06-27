package ttps.persistence.model.board;

import java.util.List;

import ttps.persistence.model.user.impl.Alumno;

public interface Pizarra{

	public long getId();
	public void setId(long id);
	public String getNombre();
	public void setNombre(String nombre);
	public List<Publicacion> getPublicaciones();
	public void setPublicaciones(List<Publicacion> publicaciones);
	public Boolean add(Publicacion publicacion);
	public Boolean add(Alumno alu);
	public Boolean remove(Publicacion publicacion);
	public void setDescripcion(String desc);
	public String getDescripcion();
	public List<Alumno> getObservers();
	public void setObservers(List<Alumno> alumnos);
	public void actualizarAtributos(Pizarra pizarra);
}