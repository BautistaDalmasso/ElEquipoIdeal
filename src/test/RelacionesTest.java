package test;

import static org.junit.Assert.*;

import org.junit.Test;

import negocio.Persona;
import negocio.Relaciones;

public class RelacionesTest {

	@Test(expected = IllegalArgumentException.class)
	public void agregarEmpleado1NuloTest() {
		Relaciones r = new Relaciones();
		Persona p = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);
		
		r.agregarIncompatibilidad(null, p);
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void agregarEmpleado2NuloTest() {
		Relaciones r = new Relaciones();
		Persona p = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);
		
		r.agregarIncompatibilidad(p, null);
	}
	
	@Test(expected = IllegalArgumentException.class) 
	public void agregarCicloDeIncompatibilidadTest() {
		Relaciones r = new Relaciones();
		Persona p = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);
		
		r.agregarIncompatibilidad(p, p);
	}
	
	@Test
	public void empleadosNoSonIncompatiblesTest() {
		Relaciones r = new Relaciones();
		Persona p1 = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);
		Persona p2 = new Persona("Sergio", Persona.Rol.ARQUITECTO, 5);
		Persona p3 = new Persona("Marcos", Persona.Rol.LIDERDEPROYECTO, 5);
		
		r.agregarIncompatibilidad(p1, p2);
		
		assertFalse(r.sonIncompatibles(p1, p3));
	}
	
	@Test
	public void empleadosSonIncompatiblesTest() {
		Relaciones r = new Relaciones();
		Persona p1 = new Persona("Raul", Persona.Rol.ARQUITECTO, 5);
		Persona p2 = new Persona("Sergio", Persona.Rol.ARQUITECTO, 5);
		
		r.agregarIncompatibilidad(p1, p2);
		
		assertTrue(r.sonIncompatibles(p1, p2));
	}
}
