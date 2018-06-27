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
import ttps.persistence.service.PizarraService;

/**
 * Alta, baja y modificacion de Pizarras
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class TestPizarra extends TestCase {

	List<PizarraImpl> arch;
	@Autowired
	PizarraService dao;

	public TestPizarra() {
		super();
		arch = new ArrayList<PizarraImpl>();
	}

	// execute for each test, before executing test
//	@Before
//	public void before() {
//		dao.deleteAll();
//	}

	// assigning the values
	protected void setUp() {
		arch.add(new PizarraImpl("Ofertas laboares", null, "publique las ofertas laboares de su empresa"));
		arch.add(new PizarraImpl("Eventos", null, "publique eventos"));
		arch.add(new PizarraImpl("Servico", null, "publique servicio ofrecidos"));
		// dao.create(arch);
		// arch.addAll(dao.find());
	}

	@Test
	public void testAAgregarPizarra() {
		setUp();
		System.out.println("--TEST AGREGAR PIZARRA--");
		int size = dao.find().size();
		System.out.println("Cantidad de Pizarras: " + size);
		System.out.println("Agregando Pizarras...");
		dao.create(new PizarraImpl("Primer anio", null, "publique materias de primer anio"));
		size++;
		assertEquals(size, dao.find().size());
		System.out.println("Cantidad de Pizarras: " + size);
		arch = new ArrayList<PizarraImpl>();
		arch.addAll(dao.find());
		System.out.println(arch);
	}

	@Test
	public void testBModificarPizarra() {
		System.out.println("--TEST MODIFICAR PIZARRA--");
//		dao.create(arch);
		arch = dao.find();
		int size = arch.size();
		System.out.println("Cantidad de Pizarras: " + size);
		System.out.println("Modificando Pizarras...");
		arch.get(0).setDescripcion("Nueva descripcion");
		dao.modify(arch.get(0));
		assertEquals("Nueva descripcion", dao.find().get(0).getDescripcion());
		System.out.println(dao.find());
	}

	@Test
	public void testRecuperarPizarras() {
		System.out.println("--TEST RECUPERAR PIZARRAS--");
		List<PizarraImpl> pizarras = dao.find();
		int size = pizarras.size();
		System.out.println("Cantidad de Pizarras: " + size);
		System.out.println(pizarras);
	}

	@Test
	public void testCEliminarPizarra() {
		System.out.println("--TEST ELIMINAR PIZARRA--");
		if (dao.find().isEmpty())
			dao.create(arch);
		arch = new ArrayList<PizarraImpl>();
		arch.addAll(dao.find());
		int size = arch.size();
		System.out.println("Cantidad de Pizarras: " + size);
		System.out.println("Eliminando Pizarra: " + arch.get(0));
		dao.delete(arch.get(0).getId());
		size--;
		assertEquals(size, dao.find().size());
		System.out.println("Cantidad de Pizarras: " + size);
		System.out.println(dao.find());
	}
}