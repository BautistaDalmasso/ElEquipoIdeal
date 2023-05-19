package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import negocio.ArchivoEmpresa;
import negocio.Empleado;
import negocio.Empresa;
import negocio.Empresa.Rol;

public class GuardadoDeArchivosTest {
	private static final String NOMBRE_ARCHIVO = "ArchivoDePrueba";
	private static File archivo;
	private static Empresa empresa;
	
	
	@Before
	public void crearArchivo() {
		archivo = new File(NOMBRE_ARCHIVO);
	}
	
	@Before
	public void crearEmpresaSimple() {
		empresa = new Empresa();
		
		Empleado e1 = new Empleado("Raul", Rol.LIDERDEPROYECTO, 5);
		Empleado e2 = new Empleado("Marcos", Rol.ARQUITECTO, 5);
		empresa.agregarEmpleado(e1);
		empresa.agregarEmpleado(e2);
		empresa.agregarEmpleado(new Empleado("Miguel", Rol.PROGRAMADOR, 5));
		empresa.agregarEmpleado(new Empleado("Manuel", Rol.TESTER, 5));
		
		empresa.agregarIncompatibilidad(e1, e2);
	}
	
	@Test( expected = IllegalArgumentException.class)
	public void archivoExistenteTest() throws IOException {
		archivo.createNewFile();
		
		ArchivoEmpresa a = new ArchivoEmpresa(empresa);
		a.guardarEmpresaConCuidado(NOMBRE_ARCHIVO);
	}

	@Test
	public void archivoSeSobreescribeTest() throws IOException {
		archivo.createNewFile();
		
		ArchivoEmpresa a = new ArchivoEmpresa(empresa);
		a.guardarEmpresaConSobreescritura(NOMBRE_ARCHIVO);
		
		assertEquals(empresa, Empresa.cargarEmpresaDesdeArchivo(NOMBRE_ARCHIVO));
	}
	
	@Test
	public void archivoSinSobreescrituraTest() throws IOException {
		ArchivoEmpresa a = new ArchivoEmpresa(empresa);
		a.guardarEmpresaConCuidado(NOMBRE_ARCHIVO);
		
		assertEquals(empresa, Empresa.cargarEmpresaDesdeArchivo(NOMBRE_ARCHIVO));
	}
	
	@After
	public void borrarArchivo() {
		if (archivo.exists()) {
			if (!archivo.delete())
				throw new RuntimeException("No se borro.");
		}
	}
}
