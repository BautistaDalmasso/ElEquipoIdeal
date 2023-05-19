package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import negocio.Empresa;
import negocio.Empleado;

public class EmpresaTest {

	@Test(expected = IllegalArgumentException.class)
	public void agregarPersonaNulaTest() {
		Empresa e = new Empresa();

		e.agregarEmpleado(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarObjetoPersonaDosVecesTest() {
		Empresa e = new Empresa();
		Empleado p = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);

		e.agregarEmpleado(p);
		e.agregarEmpleado(p);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarPersonaDatosRepetidosTest() {
		Empresa e = new Empresa();
		Empleado p1 = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);
		Empleado p2 = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);

		e.agregarEmpleado(p1);
		e.agregarEmpleado(p2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarIncompatibilidad_PrimerEmpleadoNoEsDeLaEmpresaTest() {
		Empresa e = new Empresa();
		Empleado p1 = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);
		Empleado p2 = new Empleado("Marcos", Empresa.Rol.ARQUITECTO, 5);
		e.agregarEmpleado(p2);
		
		e.agregarIncompatibilidad(p1, p2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarIncompatibilidad_SegundoEmpleadoNoEsDeLaEmpresaTest() {
		Empresa e = new Empresa();
		Empleado p1 = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);
		Empleado p2 = new Empleado("Marcos", Empresa.Rol.ARQUITECTO, 5);
		e.agregarEmpleado(p1);
		
		e.agregarIncompatibilidad(p1, p2);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void buscarEmpleadoInexistenteTest() {
		Empresa e = new Empresa();
		Empleado p1 = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);
		e.agregarEmpleado(p1);
		
		e.buscarEmpleadoPorNombre("Marcos");
	}
	
	@Test
	public void isEmpleadoDaFalseParaEmpleadosNoAgregadosTest() {
		Empresa e = new Empresa();
		Empleado p = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);

		assertFalse(e.esEmpleado(p));
	}

	@Test
	public void isEmpleadoDaTrueLuegoDeAgregarEmpleadoTest() {
		Empresa e = new Empresa();
		Empleado p = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);

		e.agregarEmpleado(p);

		assertTrue(e.esEmpleado(p));
	}

	@Test
	public void empleadosPorRolContieneSoloEmpleadosAgregadoTest() {
		Empresa e = new Empresa();
		Empleado p1 = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);
		Empleado p2 = new Empleado("Marcos", Empresa.Rol.ARQUITECTO, 5);

		e.agregarEmpleado(p1);
		e.agregarEmpleado(p2);

		ArrayList<Empleado> expected = new ArrayList<Empleado>();
		expected.add(p1);
		expected.add(p2);

		assertEquals(expected, e.getEmpleadosDeRol(Empresa.Rol.ARQUITECTO));
	}
	
	@Test
	public void incompatibilidadSeAgregaSatisfactoriamenteTest() {
		Empresa e = new Empresa();
		Empleado p1 = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);
		Empleado p2 = new Empleado("Marcos", Empresa.Rol.ARQUITECTO, 5);
		e.agregarEmpleado(p1);
		e.agregarEmpleado(p2);
		
		e.agregarIncompatibilidad(p1, p2);
		
		assertTrue(e.sonIncompatibles(p1, p2));
	}
	
	@Test
	public void buscarEmpleadoExistenteTest() {
		Empresa e = new Empresa();
		Empleado p1 = new Empleado("Raul", Empresa.Rol.ARQUITECTO, 5);
		Empleado p2 = new Empleado("Marcos", Empresa.Rol.ARQUITECTO, 5);
		e.agregarEmpleado(p1);
		e.agregarEmpleado(p2);
		
		assertEquals(p2, e.buscarEmpleadoPorNombre("Marcos"));
	}
}
