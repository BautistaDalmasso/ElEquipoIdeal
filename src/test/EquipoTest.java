package test;

import static org.junit.Assert.*;

import org.junit.Test;

import negocio.Empleado;
import negocio.Empresa;
import negocio.Empresa.Rol;
import negocio.Equipo;

public class EquipoTest {

	@Test
	public void agregarEmpleadosTest() {
		Empresa empresa = new Empresa();
		Empleado empleado1 = new Empleado("Raul", Rol.ARQUITECTO, 5);
		Empleado empleado2 = new Empleado("Roberto", Rol.ARQUITECTO, 4);
		Equipo equipo = new Equipo(empresa);
		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);
		
		equipo.agregarEmpleado(empleado1);
		equipo.agregarEmpleado(empleado2);
		
		assertEquals(9, equipo.getCalificacionTotal());
	}

	@Test
	public void removerEmpleadoTest() {
		Empresa empresa = new Empresa();
		Empleado empleado1 = new Empleado("Raul", Rol.ARQUITECTO, 5);
		Empleado empleado2 = new Empleado("Roberto", Rol.ARQUITECTO, 4);
		Equipo equipo = new Equipo(empresa);
		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);
		equipo.agregarEmpleado(empleado1);
		equipo.agregarEmpleado(empleado2);
		
		equipo.removerEmpleado(empleado1);
		
		assertEquals(4, equipo.getCalificacionTotal());
	}
	
	@Test
	public void empleadosPorRolSeActualizaLuegoDeAgregarTest() {
		Empresa empresa = new Empresa();
		Empleado empleado1 = new Empleado("Raul", Rol.ARQUITECTO, 5);
		Empleado empleado2 = new Empleado("Roberto", Rol.ARQUITECTO, 4);
		Equipo equipo = new Equipo(empresa);
		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);
		equipo.agregarEmpleado(empleado1);
		equipo.agregarEmpleado(empleado2);
		
		assertEquals(2, equipo.getEmpleadosPorRol(Rol.ARQUITECTO));
	}
	
	@Test
	public void empleadosPorRolSeActualizaLuegoDeEliminarTest() {
		Empresa empresa = new Empresa();
		Empleado empleado1 = new Empleado("Raul", Rol.ARQUITECTO, 5);
		Empleado empleado2 = new Empleado("Roberto", Rol.ARQUITECTO, 4);
		Equipo equipo = new Equipo(empresa);
		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);
		equipo.agregarEmpleado(empleado1);
		equipo.agregarEmpleado(empleado2);
		
		equipo.removerEmpleado(empleado2);
		
		assertEquals(1, equipo.getEmpleadosPorRol(Rol.ARQUITECTO));
	}
	
	@Test
	public void noHayIncompatibilidadesTest() {
		Empresa empresa = new Empresa();
		Empleado empleado1 = new Empleado("Raul", Rol.ARQUITECTO, 5);
		Empleado empleado2 = new Empleado("Roberto", Rol.ARQUITECTO, 4);
		Empleado empleado3 = new Empleado("Juan", Rol.ARQUITECTO, 5);
		Equipo equipo = new Equipo(empresa);
		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);
		empresa.agregarEmpleado(empleado3);
		equipo.agregarEmpleado(empleado1);
		equipo.agregarEmpleado(empleado2);
				
		assertFalse(equipo.tieneIncompatibilidadesConElEquipo(empleado3));
	}
	
	@Test
	public void hayIncompatibilidadesTest() {
		Empresa empresa = new Empresa();
		Empleado empleado1 = new Empleado("Raul", Rol.ARQUITECTO, 5);
		Empleado empleado2 = new Empleado("Roberto", Rol.ARQUITECTO, 4);
		Empleado empleado3 = new Empleado("Juan", Rol.ARQUITECTO, 5);
		Equipo equipo = new Equipo(empresa);
		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);
		empresa.agregarEmpleado(empleado3);
		empresa.agregarIncompatibilidad(empleado1, empleado3);
		equipo.agregarEmpleado(empleado1);
		equipo.agregarEmpleado(empleado2);
				
		assertTrue(equipo.tieneIncompatibilidadesConElEquipo(empleado3));
	}
	
	@Test
	public void empleadosPorRolSeMantieneLuegoDeCopiarTest() {
		Empresa empresa = new Empresa();
		Empleado empleado1 = new Empleado("Raul", Rol.ARQUITECTO, 5);
		Empleado empleado2 = new Empleado("Roberto", Rol.ARQUITECTO, 4);
		Equipo equipo = new Equipo(empresa);
		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);
		equipo.agregarEmpleado(empleado1);
		equipo.agregarEmpleado(empleado2);
		
		Equipo copia = equipo.copiar();
		
		assertEquals(2, copia.getEmpleadosPorRol(Rol.ARQUITECTO));
	}
	
	@Test
	public void copiarNoGeneraAliasingDeEmpleadosTest() {
		Empresa empresa = new Empresa();
		Empleado empleado1 = new Empleado("Raul", Rol.ARQUITECTO, 5);
		Empleado empleado2 = new Empleado("Roberto", Rol.ARQUITECTO, 4);
		Equipo equipo = new Equipo(empresa);
		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);
		equipo.agregarEmpleado(empleado1);
		equipo.agregarEmpleado(empleado2);
				
		Equipo copia = equipo.copiar();
		copia.removerEmpleado(empleado1);

		assertTrue(equipo.contieneEmpleado(empleado1));
	}
	
	@Test
	public void copiarNoGeneraAliasingDeEmpleadosPorRolTest() {
		Empresa empresa = new Empresa();
		Empleado empleado1 = new Empleado("Raul", Rol.ARQUITECTO, 5);
		Empleado empleado2 = new Empleado("Roberto", Rol.ARQUITECTO, 4);
		Equipo equipo = new Equipo(empresa);
		empresa.agregarEmpleado(empleado1);
		empresa.agregarEmpleado(empleado2);
		equipo.agregarEmpleado(empleado1);
		equipo.agregarEmpleado(empleado2);
				
		Equipo copia = equipo.copiar();
		copia.removerEmpleado(empleado1);

		assertEquals(2, equipo.getEmpleadosPorRol(Rol.ARQUITECTO));
	}
}
