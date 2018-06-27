package ttps.guarani.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ttps.guarani.persistence.model.Credencial;
import ttps.guarani.persistence.model.Profesor;
import ttps.guarani.persistence.model.Usuario;


@Service("guaraniProfesorService")
public class ProfesorServiceImpl extends UsuarioServiceImpl implements ProfesorService{

	@Override
	public List<Profesor> find() {
		ArrayList<Profesor> profesores = new ArrayList<Profesor>();
		for(Usuario user: getDAO().find()){
			if(user.getTipo().equals("PROFESOR")){
				profesores.add( (Profesor)user);
			}
		}
		return profesores;
	}

	@Override
	public Profesor find(long id) {
		Usuario user = getDAO().find(id);
		return (user != null && user.getTipo().equals("PROFESOR")) ? (Profesor) user : null;
	}

	@Override
	public boolean chequearlogin(Credencial credencial) {
		Usuario user = getDAO().find(credencial.getUsuario(),credencial.getClave());
		return (user != null && user.getTipo().equals("PROFESOR"));
	}
}