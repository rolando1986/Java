package ttps.guarani.persistence.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ttps.guarani.persistence.model.Alumno;
import ttps.guarani.persistence.model.Credencial;
import ttps.guarani.persistence.model.Usuario;


@Service("guaraniAlumnoService")
public class AlumnoServiceImpl extends UsuarioServiceImpl implements AlumnoService{

	@Override
	public List<Alumno> find() {
		ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
		for(Usuario user: getDAO().find()){
			if(user.getTipo().equals("ALUMNO")){
				alumnos.add( (Alumno)user);
			}
		}
		return alumnos;
	}

	@Override
	public Alumno find(long id) {
		Usuario user = getDAO().find(id);
		return (user != null && user.getTipo().equals("ALUMNO")) ? (Alumno) user : null;
	}

	@Override
	public boolean chequearlogin(Credencial credencial) {
		Usuario user = getDAO().find(credencial.getUsuario(),credencial.getClave());
		return (user != null && user.getTipo().equals("ALUMNO"));
	}
}