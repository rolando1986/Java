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
import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.model.board.Publicacion;
import ttps.persistence.service.PizarraService;
import ttps.persistence.service.PublicacionService;

/**
* Alta, baja y modificacion de Publicaciones sobre una Pizarra
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class TestPublicacion extends TestCase {

	List<Publicacion> arch;
	@Autowired
	PublicacionService dao;
	@Autowired
	PizarraService dao1;
	PizarraImpl pizarra = new PizarraImpl("Ofertas laboares", null, "publique las ofertas laboares de su empresa");

	public TestPublicacion() {
		super();
		arch = new ArrayList<Publicacion>();
	}

	// assigning the values
	protected void setUp() {
		arch.add(new Publicacion("Cognizant", "Se necesita desarrollador JAVA", pizarra));
		arch.add(new Publicacion("Accenture", "Se necesita desarrollador PHP", pizarra));
		arch.add(new Publicacion("Accenture", "Se necesita desarrollador Android", pizarra));
		if (dao1.find().isEmpty()) {
			dao1.create(pizarra);
		}
		pizarra = (PizarraImpl) dao1.find().get(0);
	}

	@Test
	public void testAAgregarPublicacion() {
		setUp();
		System.out.println("--TEST AGREGAR PUBLICACION--");
		int size = dao.find().size();
		System.out.println("Cantidad de Publicacions: " + size);
		System.out.println("Agregando Publicacione...");
		dao.create(new Publicacion("Flux", "Se necesita desarrollador Symfony", pizarra));
		size++;
		assertEquals(size, dao.find().size());
		System.out.println("Cantidad de Publicacions: " + size);
		arch = new ArrayList<Publicacion>();
		arch.addAll(dao.find());
		System.out.println(arch);
	}

	@Test
	public void testBModificarPublicacion() {
		System.out.println("--TEST MODIFICAR PUBLICACION--");
		if (dao1.find().isEmpty()) {
			dao1.create(pizarra);
		}
		pizarra = (PizarraImpl) dao1.find().get(0);
		if (dao.find().isEmpty())
			dao.create(arch);
		arch = new ArrayList<Publicacion>();
		arch.addAll(dao.find());
		int size = arch.size();
		System.out.println("Cantidad de Publicacions: " + size);
		System.out.println("Modificando Publicacion...");
		arch.get(0).setTexto("Nueva oferta labolar");
		dao.modify(arch.get(0));
		assertEquals("Nueva oferta labolar", dao.find().get(0).getTexto());
		System.out.println(dao.find());
	}

	@Test
	public void testRecuperarPublicaciones() {
		System.out.println("--TEST RECUPERAR PUBLICACIONES--");
		List<Publicacion> publicaciones = dao.find();
		int size = publicaciones.size();
		System.out.println("Cantidad de Publicaciones: " + size);
		System.out.println(publicaciones);
	}

	@Test
	public void testEliminarPublicacion() {
		System.out.println("--TEST ELIMINAR PUBLICACION--");
		if (dao.find().isEmpty())
			dao.create(arch);
		arch = new ArrayList<Publicacion>();
		arch.addAll(dao.find());
		int size = arch.size();
		System.out.println("Cantidad de Publicaciones: " + size);
		arch.get(0).setPizarra(pizarra);
		System.out.println("Eliminando Publicacion: " + arch.get(0));
		dao.delete(arch.get(0).getId());
		size--;
		assertEquals(size, dao.find().size());
		System.out.println("Cantidad de Publicacions: " + size);
		System.out.println(dao.find());
	}
}