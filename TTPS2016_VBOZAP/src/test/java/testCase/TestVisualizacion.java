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
import ttps.persistence.model.access.Escritura;
import ttps.persistence.model.access.Visualizacion;
import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.service.AccesoService;
import ttps.persistence.service.PizarraService;

/**
 * Alta y baja de Accesos para una Pizarra
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class TestVisualizacion extends TestCase {

	List<Visualizacion> arch;
	List<PizarraImpl> arch1;
	@Autowired
	AccesoService dao;
	@Autowired
	PizarraService dao1;

	public TestVisualizacion() {
		super();
		arch = new ArrayList<Visualizacion>();
		arch1 = new ArrayList<PizarraImpl>();
	}

	// assigning the values
	protected void setUp() {
		if (dao1.find().isEmpty()) {
			arch1.add(new PizarraImpl("Tercer anio", null, "publique materias de tercer anio"));
			arch1.add(new PizarraImpl("Segundo anio", null, "publique materias de primer anio"));
			dao1.create(arch1);
		}
	}

	@Test
	public void testAgregarAcceso() {
		setUp();
		System.out.println("--TEST AGREGAR ACCESO--");
		arch1 = dao1.find();
		arch.add(new Visualizacion(2,arch1.get(0)));
		arch.add(new Escritura(1,arch1.get(1)));
		int size = dao.find().size();
		System.out.println("Cantidad de Accesos: " + size);
		System.out.println("Agregando Accesos: " + arch);
		dao.create(arch);
		size += 2;
		assertEquals(size, dao.find().size());
		System.out.println("Cantidad de Accesos: " + size);
		arch = new ArrayList<Visualizacion>();
		arch.addAll(dao.find());
		System.out.println(arch);
	}

	@Test
	public void testRecuperarAccesos() {
		System.out.println("--TEST RECUPERAR ACCESOS--");
		List<Visualizacion> accesos = dao.find();
		int size = accesos.size();
		System.out.println("Cantidad de accesos: " + size);
		System.out.println(accesos);
	}

	@Test
	public void testEliminarAcceso() {
		System.out.println("--TEST ELIMINAR ACCESO--");
		dao.create(arch);
		arch = new ArrayList<Visualizacion>();
		arch.addAll(dao.find());
		int size = arch.size();
		System.out.println("Cantidad de accesos: " + size);
		System.out.println("Eliminando accesos: " + arch.get(1));
		dao.delete(arch.get(1).getId());
		size--;
		assertEquals(size, dao.find().size());
		System.out.println("Cantidad de Accesos: " + size);
		System.out.println(dao.find());
	}
}