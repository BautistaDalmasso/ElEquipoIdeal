package test;

import static org.junit.Assert.*;
import org.junit.Test;

import negocio.Empleado;
import negocio.Empresa;


public class PersonaTest {
	@Test(expected = IllegalArgumentException.class)
	public void calificacionMenorAUnoTest() {
		new Empleado("Raúl", Empresa.Rol.LIDERDEPROYECTO, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calificacionMayorACincoTest() {
		new Empleado("Raúl", Empresa.Rol.ARQUITECTO, 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nombreNullTest() {
		new Empleado(null, Empresa.Rol.PROGRAMADOR, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nombreVacioTest() {
		new Empleado("", Empresa.Rol.TESTER, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nombreMuyLargoTest() {
		new Empleado("a".repeat(31), Empresa.Rol.ARQUITECTO, 1);
	}
	
	@Test
	public void crearPersonaTest() {
		Empleado p = new Empleado("Raúl", Empresa.Rol.ARQUITECTO, 5);
		
		assertEquals("Raúl", p.getNombre());
	}
	
	@Test
	public void chequearCalificacionTest() {
		Empleado p = new Empleado("Raúl", Empresa.Rol.ARQUITECTO, 5);
		assertEquals(5, p.getCalificacion());
	}
	
	@Test
	public void chequearRolTest() {
		Empleado p = new Empleado("Raúl", Empresa.Rol.TESTER, 5);
		assertEquals(Empresa.Rol.TESTER, p.getRol());
	}
}
