package ttps.guarani.persistence.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import ttps.guarani.persistence.model.Alumno;
import ttps.guarani.persistence.model.Profesor;
import ttps.guarani.persistence.model.Usuario;

@Repository
public class UserDAOMockImpl implements UserDAO{

	private static List<Usuario> users;


	public UserDAOMockImpl() {
		super();
		users = new ArrayList<Usuario>();
		users.add(new Alumno(1, "Israel", "Zeromski", "zeroaxel88@gmail.com", "Pass1234"));
		ArrayList<Integer> ciclos = new ArrayList<Integer>();
		ciclos.add(1);
		ciclos.add(3);
		users.add(new Profesor(2, "Laura", "Alonso", "AlonsoLaura@gmail.com", "Pass1234",ciclos));
		ciclos.remove(1);
		ciclos.add(2);
		users.add(new Profesor(3, "Juan", "Rissoto", "rosiJuan@hotmail.com", "Pass1234", ciclos));
		users.add(new Alumno(4, "Veronica", "Casoli", "veroLi@gmail.com", "Pass1234"));
	}

	@Override
	public List<Usuario> find() {
		return users;
	}

	@Override
	public Usuario find(long id) {
		for(Usuario user: users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}

	@Override
	public Usuario find(String nombre, String clave) {
		for(Usuario user: users){
			if(user.getNombres().equals(nombre) && user.getClave().equals(clave)){
				return user;
			}
		}
		return null;
	}
}