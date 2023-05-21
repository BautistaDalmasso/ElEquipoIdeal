package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import negocio.Empleado;
import negocio.Empresa;
import negocio.Rol;

public class CargadoDeArchivosTest {
	private static final Empresa EMPRESA_SIMPLE = crearEmpresaSimple();
	private static final String NOMBRE_ARCHIVO = "./src/test/EmpresaSimple";
	
	@Test( expected = FileNotFoundException.class )
	public void cargarArchivoInexistente() throws IOException {
		Empresa.cargarEmpresaDesdeArchivo("./src/test/INEXISTENTE");
	}

	@Test
	public void cargarArchivoExistente() throws IOException {
		Empresa obtenida = Empresa.cargarEmpresaDesdeArchivo(NOMBRE_ARCHIVO);
		
		assertEquals(EMPRESA_SIMPLE, obtenida);
	}
	
	private static Empresa crearEmpresaSimple() {
		Empresa e = new Empresa();
		
		Empleado e1 = new Empleado("Raul", Rol.LIDERDEPROYECTO, 5);
		Empleado e2 = new Empleado("Marcos", Rol.ARQUITECTO, 5);
		e.agregarEmpleado(e1);
		e.agregarEmpleado(e2);
		e.agregarEmpleado(new Empleado("Miguel", Rol.PROGRAMADOR, 5));
		e.agregarEmpleado(new Empleado("Manuel", Rol.TESTER, 5));
		
		e.agregarIncompatibilidad(e1, e2);
		
		return e;
	}

}
