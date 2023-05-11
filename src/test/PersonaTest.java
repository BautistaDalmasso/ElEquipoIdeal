package test;

import static org.junit.Assert.*;
import org.junit.Test;

import negocio.Persona;
import negocio.Persona.*;


public class PersonaTest {
	@Test(expected = IllegalArgumentException.class)
	public void calificacionMenorAUnoTest() {
		new Persona("Raúl", Rol.ARQUITECTO, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void calificacionMayorACincoTest() {
		new Persona("Raúl", Rol.ARQUITECTO, 6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nombreNullTest() {
		new Persona(null, Rol.ARQUITECTO, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nombreVacioTest() {
		new Persona("", Rol.ARQUITECTO, 6);
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
	public void chequearRolesTest() {
		Persona p1 = new Persona("Raúl", Rol.LIDERDEPROYECTO, 5);
		Persona p2 = new Persona("Pedro", Rol.ARQUITECTO, 4);
		Persona p3 = new Persona("Juan", Rol.PROGRAMADOR, 3);
		Persona p4 = new Persona("José", Rol.TESTER, 2);	
	}
	
//	@Test
//	public void calificacionTest() {
//		for (int i = 1; )
//		new Persona("Raúl", Rol.ARQUITECTO, 5);
//	}

}
