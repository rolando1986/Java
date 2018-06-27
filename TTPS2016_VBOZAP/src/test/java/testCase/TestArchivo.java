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
import ttps.persistence.model.media.Archivo;
import ttps.persistence.service.ArchivoService;

/**
 * Alta, baja y modificacion de Archivos
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class TestArchivo extends TestCase {

	List<Archivo> arch;
	@Autowired
	ArchivoService dao;

	public TestArchivo() {
		super();
	}

	// assigning the values
	protected void setUp() {
		arch = dao.find();
	}

	@Test
	public void testAgregarArchivo() {
		arch = new ArrayList<Archivo>();
		arch.add(new Archivo("Prueba1", "prueba|prueba1.jpg"));
		arch.add(new Archivo("Prueba2", "prueba|prueba2.jpg"));
		arch.add(new Archivo("Prueba3", "prueba|prueba3.jpg"));
		System.out.println("--TEST AGREGAR ARCHIVOS--");
		int size = arch.size() + dao.find().size();
		System.out.println("Cantidad de archivos antes de agregar: " + size);
		System.out.println("Agregando archivos: " + arch);
		dao.create(arch);
		assertEquals(size, dao.find().size());
		System.out.println("Cantidad de archivos: despues de agregar" + size);
		arch = new ArrayList<Archivo>();
		arch = dao.find();
		System.out.println(arch);
	}

	@Test
	public void testModificarArchivo() {
		System.out.println("--TEST Modificar ARCHIVO--");
		arch = new ArrayList<Archivo>();
		arch = dao.find();
		int size = dao.find().size();
		System.out.println("Cantidad de archivos antes de modificar: " + size);
		System.out.println("Modificando archivos " + arch.get(0));
		arch.get(0).setNombre("Nuevo nombre");
		dao.modify(arch.get(0));
		arch = dao.find();
		assertEquals("Nuevo nombre", arch.get(0).getNombre());
		assertEquals(size, arch.size());
		System.out.println("Cantidad de archivos despues de modificar: " + size);
		System.out.println(arch);
	}

	@Test
	public void testEliminarArchivo() {
		System.out.println("--TEST ELIMINAR ARCHIVOS--");
		arch = new ArrayList<Archivo>();
		arch.addAll(dao.find());
		int size = dao.find().size();
		System.out.println("Cantidad de archivos antes de eliminar: " + size);
		System.out.println("Eliminando archivo: " + arch.get(1));
		dao.delete(arch.get(1).getId());
		size--;
		arch = dao.find();
		assertEquals(size, arch.size());
		System.out.println("Cantidad de archivos despues de eliminar: " + size);
		System.out.println(arch);
	}


	@Test
	public void testRecuperarUnArchivo() {
		System.out.println("--TEST RECUPERAR UN ARCHIVO--");
		System.out.println("Buscando archivo con nombre: Prueba3");
		Archivo archivos = dao.findByName("Prueba3");
		assertEquals("Prueba3", archivos.getNombre());
		System.out.println(archivos);
	}

	@Test
	public void testRecuperarArchivos() {
		System.out.println("--TEST RECUPERAR ARCHIVOS--");
		arch = dao.find();
		assertEquals(arch.size(), dao.find().size());
		System.out.println("Cantidad de archivos: " + dao.find().size());
		System.out.println(arch);
	}

	// execute for each test, before executing test
//	@After
//	public void after() {
//		dao.deleteAll();
//	}
}