package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import negocio.Empresa;
import negocio.Persona;

public class EmpresaTest {

	@Test(expected = IllegalArgumentException.class)
	public void agregarPersonaNulaTest() {
		Empresa e = new Empresa();

		e.agregarEmpleado(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarObjetoPersonaDosVecesTest() {
		Empresa e = new Empresa();
		Persona p = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);

		e.agregarEmpleado(p);
		e.agregarEmpleado(p);
	}

	@Test(expected = IllegalArgumentException.class)
	public void agregarPersonaDatosRepetidosTest() {
		Empresa e = new Empresa();
		Persona p1 = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);
		Persona p2 = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);

		e.agregarEmpleado(p1);
		e.agregarEmpleado(p2);
	}

	@Test
	public void isEmpleadoDaFalseParaEmpleadosNoAgregadosTest() {
		Empresa e = new Empresa();
		Persona p = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);

		assertFalse(e.esEmpleado(p));
	}

	@Test
	public void isEmpleadoDaTrueLuegoDeAgregarEmpleadoTest() {
		Empresa e = new Empresa();
		Persona p = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);

		e.agregarEmpleado(p);

		assertTrue(e.esEmpleado(p));
	}

	@Test
	public void empleadosPorTestContieneSoloEmpleadosAgregadoTest() {
		Empresa e = new Empresa();
		Persona p1 = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);
		Persona p2 = new Persona("Marcos", Persona.Rol.ARQUITECTO, 5);

		e.agregarEmpleado(p1);
		e.agregarEmpleado(p2);

		ArrayList<Persona> expected = new ArrayList<Persona>();
		expected.add(p1);
		expected.add(p2);

		assertEquals(expected, e.getEmpleadosDeRol(Persona.Rol.ARQUITECTO));
	}
}
