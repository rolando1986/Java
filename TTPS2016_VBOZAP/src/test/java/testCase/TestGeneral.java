package testCase;

import java.util.ArrayList;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;
import ttps.persistence.DAO.AccesoDAO;
import ttps.persistence.DAO.PizarraDAO;
import ttps.persistence.DAO.PublicacionDAO;
import ttps.persistence.DAO.impl.UsuarioImplDAOjpa;
import ttps.persistence.model.access.Visualizacion;
import ttps.persistence.model.board.PizarraImpl;
import ttps.persistence.model.board.Publicacion;
import ttps.persistence.model.user.impl.Docente;

/**
 * Alta, baja y modificacion de Archivos
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-config.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class TestGeneral extends TestCase {

	@Autowired
	private UsuarioImplDAOjpa daoUsuario;;
	Docente docente;
	@Autowired
	PizarraDAO pizarraDao;
	@Autowired
	AccesoDAO accesoDao;
	@Autowired
	PublicacionDAO pubDao;

	public TestGeneral() {
		super();
	}

	// assigning the values
	protected void setUp() {
		ArrayList<String> ciclos = new ArrayList<String>();
		ciclos.add("primer ciclo");
		ciclos.add("segundo ciclo");
		pizarraDao.create(new PizarraImpl("primer ciclo", null, "publique materias de primer ciclo"));
		Visualizacion acceso = new Visualizacion(2,pizarraDao.find().get(0));
		accesoDao.create(acceso);
		acceso = accesoDao.find().get(0);
		Publicacion pub = new Publicacion("Cognizant", "Se necesita desarrollador JAVA", acceso.getPizarra());
		pubDao.create(pub);
		pub = pubDao.find().get(0);
		ArrayList<Visualizacion> accesosList = new ArrayList<Visualizacion>();
		acceso.getPizarra().add(pub);
		accesosList.add(acceso);
		docente = new Docente("Axel", "zi", accesosList, ciclos);
	}

	@Test
	public void testAgregarArchivo() {
		System.out.println("--TEST AGREGAR--");

		daoUsuario.create(docente);
		assertEquals(1, daoUsuario.find().size());
		docente = (Docente) daoUsuario.find().get(0);
		System.out.println(docente);
	}
}