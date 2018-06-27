package testCase;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;
import ttps.persistence.model.board.Comentario;
import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.model.board.Publicacion;
import ttps.persistence.service.ComentarioService;
import ttps.persistence.service.PizarraService;
import ttps.persistence.service.PublicacionService;

/**
* Alta, baja y modificacion de Comentarios para una Publicacion de una Pizarra
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class TestComentario extends TestCase {

	String message = "prueba|prueba.jpg";
	Comentario comentario = new Comentario("Prueba", "prueba|prueba.jpg");
	Publicacion publicacion;
	PizarraImpl pizarra;
	@Autowired
	ComentarioService dao;
	@Autowired
	PizarraService dao1;
	@Autowired
	PublicacionService daoPublicacion;
	List<Comentario> com;

	public TestComentario() {
		super();
	}

	// assigning the values
	protected void setUp() {
		com = new ArrayList<Comentario>();
		if(daoPublicacion != null && daoPublicacion.find().isEmpty()){
			if (dao1.find().isEmpty()) {
				PizarraImpl pizarra = new PizarraImpl("Quinto anio", null, "publique las notas");
				dao1.create(pizarra);
			}
			pizarra = (PizarraImpl) dao1.find().get(0);
			publicacion = new Publicacion("Tesis", "Se abre la inscripcion a la tesis", pizarra);
			daoPublicacion.create(publicacion);
		}
		com.add(new Comentario("Juan", "Prueba1", publicacion));
		com.add(new Comentario("Axel", "Prueba2", publicacion));
		com.add(new Comentario("Pedro", "Prueba3", publicacion));
		PizarraImpl pizarra = new PizarraImpl("Segundo anio", null, "publique las notas");
		publicacion = new Publicacion("Algoritmo y estructura de datos", "Se abre la inscripcion a la materia", pizarra);
		pizarra.add(publicacion);
		dao1.create(pizarra);
	}

	@Test
	public void testAgregarComentario() {
		setUp();
		System.out.println("--TEST AGREGAR COMENTARIO--");
		System.out.println("Pizarra: " + pizarra.toString());
		int size = dao.find().size();
		System.out.println("Cantidad de comentarios: " + size);
		System.out.println("Agregando comentarios: " + com);
		dao.create(com);
		size += com.size();
		assertEquals(size, dao.find().size());
		System.out.println("Cantidad de comentarios: " + size);
		com = new ArrayList<Comentario>();
		com.addAll(dao.find());
		System.out.println(dao.find());
	}

	@Test
	public void testModificarComentario() {
		System.out.println("--TEST MODIFICAR COMENTARIO--");
		com = new ArrayList<Comentario>();
		com = dao.find();
		int size = dao.find().size();
		System.out.println("Cantidad de comentarios: " + size);
		System.out.println("Modificando comentario: " + com.get(0).toString());
		com.get(0).setTexto("Nuevo texto");
		dao.modify(com.get(0));
		assertEquals("Nuevo texto", dao.find().get(0).getTexto());
		System.out.println(dao.find());
	}

	@Test
	public void testRecuperarComentarios() {
		System.out.println("--TEST RECUPERAR COMENTARIOS--");
		List<Comentario> archivos = dao.find();
		int size = archivos.size();
		System.out.println("Cantidad de coemntarios: " + size);
		System.out.println(archivos);
	}

	@Test
	public void testEliminarComentario() {
		System.out.println("--TEST ELIMINAR COMENTARIO--");
		com = new ArrayList<Comentario>();
		com = dao.find();
		int size = com.size();
		System.out.println("Cantidad de Comentarios: " + size);
		System.out.println("Eliminando Comentario: " + com.get(1));
		dao.delete(com.get(1).getId());
		size--;
		assertEquals(size, dao.find().size());
		System.out.println("Cantidad de comentarios: " + size);
		System.out.println(dao.find());
	}

	// execute for each test, before executing test
//	@After
//	public void after() {
//		dao1.deleteAll();
//		dao.deleteAll();
//	}
}