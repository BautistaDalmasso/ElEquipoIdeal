package test;

import static org.junit.Assert.*;
import org.junit.Test;

import negocio.Persona;
import negocio.Persona.*;


public class PersonaTest {
	@Test(expected = IllegalArgumentException.class)
	public void calificacionMenorAUnoTest() {
		new Persona("Raúl", Rol.LIDERDEPROYECTO, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calificacionMayorACincoTest() {
		new Persona("Raúl", Rol.ARQUITECTO, 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nombreNullTest() {
		new Persona(null, Rol.PROGRAMADOR, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nombreVacioTest() {
		new Persona("", Rol.TESTER, 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nombreMuyLargoTest() {
		new Persona("a".repeat(31), Rol.ARQUITECTO, 6);
	}
	
	@Test
	public void crearPersonaTest() {
		Persona p = new Persona("Raúl", Rol.ARQUITECTO, 5);
		
		assertEquals("Raúl", p.getNombre());
	}
	
	@Test
	public void chequearCalificacionTest() {
		Persona p = new Persona("Raúl", Rol.ARQUITECTO, 5);
		assertEquals(5, p.getCalificacion());
	}
	
	@Test
	public void chequearRolTest() {
		Persona p = new Persona("Raúl", Rol.TESTER, 5);
		assertEquals(Rol.TESTER, p.getRol());
	}
}
