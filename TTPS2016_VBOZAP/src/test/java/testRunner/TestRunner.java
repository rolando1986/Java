package testRunner;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import testCase.TestArchivo;
import testCase.TestComentario;
import testCase.TestPizarra;
import testCase.TestPublicacion;
import testCase.TestUsuario;
import testCase.TestVisualizacion;

public class TestRunner {
	public static void main(String[] args) {

		Result result = JUnitCore.runClasses(TestArchivo.class, TestComentario.class, TestPublicacion.class,
				TestPizarra.class, TestUsuario.class, TestVisualizacion.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}