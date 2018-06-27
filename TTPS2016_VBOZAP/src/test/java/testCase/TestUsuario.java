package testCase;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ttps.persistence.model.user.impl.Administrador;
import ttps.persistence.model.user.impl.Alumno;
import ttps.persistence.model.user.impl.Docente;
import ttps.persistence.model.user.impl.Publicador;
import ttps.persistence.service.AdministradorService;
import ttps.persistence.service.AlumnoService;
import ttps.persistence.service.DocenteService;
import ttps.persistence.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-config.xml")
@Ignore
public class TestUsuario {
	@Autowired
	private UserService daoUsuario;
	@Autowired
	private AdministradorService daoAdministrador;
	@Autowired
	private DocenteService daoDocente;
	@Autowired
	private AlumnoService daoAlumno;
	// private PublicadorDAOjpa daoPublicador;

	public TestUsuario() {
		super();
	}

	// INSERCION DE USUARIOS E INICIO DE SESION DEL ADMIN
	@Test
	public void testInsercionUsuarios1() {
		Administrador admin = new Administrador("Admin", "Admin", null);
		Alumno Alumno = new Alumno("Roberto", "Gomez", null);
		ArrayList<String> ciclosA = new ArrayList<String>();
		ciclosA.add("primer ciclo");
		ciclosA.add("segundo ciclo");
		Docente docente = new Docente("Diego", "Vilches", null, ciclosA);
		Publicador Publicador = new Publicador("AMSAO company S.R.L",null,null);
		assertEquals(0, daoUsuario.find().size());
		daoUsuario.create(admin);
		System.out.println("Cantidad de usuarios: 0");
		System.out.println("Agregando admin...");
		assertEquals(1, daoUsuario.find().size());
		System.out.println("Cantidad de usuarios: 1");

		System.out.println("Agregando Alumno");
		daoUsuario.create(Alumno);
		assertEquals(2, daoUsuario.find().size());
		System.out.println("Cantidad de usuarios: 2");

		System.out.println("Agregando Docente");
		daoUsuario.create(docente);
		assertEquals(3, daoUsuario.find().size());
		System.out.println("Cantidad de usuarios: 3");

		System.out.println("Agregando Publicador");
		daoUsuario.create(Publicador);
		assertEquals(4, daoUsuario.find().size());
		System.out.println("Cantidad de usuarios: 4");
		// logueo
		System.out.println("Ingresando Usuario:Admin - Contraseña:Admin ");
		Administrador aux = new Administrador();
		List<Administrador> ListaAdmin= daoAdministrador.find();
		long id = 0;
		for(int i=0;i<ListaAdmin.size();i++){
			Administrador elemento=ListaAdmin.get(i);
			id= elemento.getId();
		}
		aux = daoAdministrador.find(id);
		String usuario = aux.getNickname();
		String contraseña = aux.getPassword();

		assertEquals("Admin", usuario);
		assertEquals("Admin", contraseña);
		System.out.println("Logueado correctamente");
		System.out.println("Bienvenido: " + usuario);

	}

	// PRUEBA MODIFICACION cambio de password ADMINISTRADOR
	@Test
	public void testModificacion1() {
		System.out.println("MODIFICACION DE UN USUARIO Admin");
		System.out.println("Obteniendo usuario Administrador...");
		List<Administrador> ListaAdmin= daoAdministrador.find();
		long id = 0;
		for(int i=0;i<ListaAdmin.size();i++){
			Administrador elemento=ListaAdmin.get(i);
			id= elemento.getId();
		}
		// Class<Administrador> clase = (Class<Administrador>)
		// DaoAdmin.getClass();

		Administrador aux = (Administrador) daoAdministrador.find(id);
		assertEquals(id, aux.getId());
		System.out.println("usuario obtenido");
		System.out.println("cambiando contraseña");
		String pass = "1234";
		aux.setPassword(pass);
		daoUsuario.modify(aux);
		System.out.println("cambiando contraseña de 'Admin' a '1234' ");
	}

	// PRUEBA MODIFICACION cambio de nickname ALUMNO
	@Test
	public void testModificacion2() {
		System.out.println("MODIFICACION DE UN USUARIO Alumno");
		System.out.println("Obteniendo usuario Alumno...");
		// Class<Administrador> clase = (Class<Administrador>)
		// DaoAdmin.getClass();
		// Object clase = Admi.getClass();
		List<Alumno> ListaAlumno= daoAlumno.find();
		long id = 0;
		for(int i=0;i<ListaAlumno.size();i++){
			Alumno elemento=ListaAlumno.get(i);
			id=elemento.getId();

		}
		Alumno aux = (Alumno) daoAlumno.find(id);
		assertEquals(id, aux.getId());
		System.out.println("Actualizando nombre a Rolando");
		aux.setNickname("Rolando");
		daoUsuario.modify(aux);
		System.out.println("cambiando el nickname de 'Roberto' a 'Rolando' ");
	}

	// PRUEBA MODIFICACION cambio de cicloAsignado DOCENTE
	@Test
	public void testModificacion3() {
		System.out.println("MODIFICACION DE UN USUARIO Docente");
		System.out.println("Obteniendo usuario Docente...");
		// Class<Administrador> clase = (Class<Administrador>)
		// DaoAdmin.getClass();
		// Object clase = Admi.getClass();
		List<Docente> ListaDocente= daoDocente.find();
		long id = 0;
		for(int i=0;i<ListaDocente.size();i++){
			Docente elemento=ListaDocente.get(i);
			if(i==0){
				id=elemento.getId();
			}


		}
		Docente aux = (Docente) daoDocente.find(id);
		assertEquals(id, aux.getId());
		System.out.println("Actualizando ciclosAsignados");
		List<String> lista = aux.getCiclosAsignados();
		assertEquals("primer ciclo", lista.get(0));
		assertEquals("segundo ciclo", lista.get(1));
		System.out.println("ciclos actuales= " + lista.get(0) + " - " + lista.get(1));
		lista.add("tercer ciclo");
		aux.setCiclosAsignados((ArrayList<String>) lista);
		daoDocente.modify(aux);
		System.out.println("se agrego un ciclo mas con exito ");

		Docente aux2 = (Docente) daoDocente.find(id);
		List<String> listaF = aux2.getCiclosAsignados();
		assertEquals("primer ciclo", listaF.get(0));
		assertEquals("segundo ciclo", listaF.get(1));
		assertEquals("tercer ciclo", listaF.get(2));
		System.out.println("ciclos finales= " + listaF.get(0) + " - " + listaF.get(1) + " - " + listaF.get(2));
	}

	// PRUEBA ELIMINACION ADMINISTRADOR
	@Test
	public void testEliminacion1() {
		System.out.println("ELIMINACION DE UN USUARIO Admin");
		System.out.println("Agregando admin NUEVO... usuario=prueba");
		Administrador adminPrueba = new Administrador("prueba", "prueba", null);
		daoAdministrador.create(adminPrueba);
		assertEquals(2, daoAdministrador.find().size());
		System.out.println("Cantidad de admin: 2");
		System.out.println("Empieza la eliminacion");
		Long id = adminPrueba.getId();
		daoAdministrador.delete(id);
		System.out.println("Eliminando ");
		assertEquals(1, daoAdministrador.find().size());
		System.out.println("Cantidad de admin: 1 - eliminacion exitosa");
	}

	// PRUEBA ELIMINACION ALUMNO
	@Test
	public void testEliminacion2() {
		System.out.println("ELIMINACION DE UN USUARIO Alumno");
		System.out.println("Agregando alumno NUEVO... usuario=alumnoPrueba");
		Alumno aluPrueba = new Alumno("alumnoPrueba", "alumnoPrueba", null);
		daoAlumno.create(aluPrueba);
		assertEquals(2, daoAlumno.find().size());
		System.out.println("Cantidad de alumno: 2");
		System.out.println("Empieza la eliminacion");
		Long id = aluPrueba.getId();
		daoAlumno.delete(id);
		System.out.println("Eliminando ");
		assertEquals(1, daoAlumno.find().size());
		System.out.println("Cantidad de alumnos: 1 - eliminacion exitosa");
	}

	// PRUEBA ELIMINACION DOCENTE
	@Test
	public void testEliminacion3() {
		System.out.println("ELIMINACION DE UN USUARIO Docente");
		System.out.println("Agregando docente NUEVO... usuario=teacherPrueba");
		ArrayList<String> ciclosA = new ArrayList<String>();
		ciclosA.add("primer ciclo");
		Docente docePrueba = new Docente("teacherPrueba", "teacherPrueba", null, ciclosA);
		daoDocente.create(docePrueba);
		assertEquals(2, daoDocente.find().size());
		System.out.println("Cantidad de Docente: 2");
		System.out.println("Empieza la eliminacion");
		Long id = docePrueba.getId();
		daoDocente.delete(id);
		System.out.println("Eliminando ");
		assertEquals(1, daoDocente.find().size());
		System.out.println("Cantidad de Docentes: 1 - eliminacion exitosa");
	}
}