package test;

import static org.junit.Assert.*;

import org.junit.Test;

import negocio.Empleado;
import negocio.Empresa;
import negocio.Rol;
import negocio.Requerimientos;

public class RequerimientosTest {

	@Test (expected = IllegalArgumentException.class)
	public void inicializarConEmpresaNullTest() {
		new Requerimientos(null);
	}

	@Test (expected = IllegalArgumentException.class)
	public void setRequerimientosConNumeroNegativoTest() {
		Empresa e = new Empresa();
		Requerimientos r = new Requerimientos(e);
		
		r.setRequerimientosParaRol(Rol.ARQUITECTO, -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setRequerimientosSinEmpleadosTest() {
		Empresa e = new Empresa();
		Requerimientos r = new Requerimientos(e);
		
		r.setRequerimientosParaRol(Rol.ARQUITECTO, 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setRequerimientosConEmpleadosInsuficientesTest() {
		Empresa e = new Empresa();
		Requerimientos r = new Requerimientos(e);
		e.agregarEmpleado(new Empleado("Raul", Rol.ARQUITECTO, 5));
		
		r.setRequerimientosParaRol(Rol.ARQUITECTO, 2);
	}
	
	@Test
	public void setRequerimientosConEmpleadosSuficientesTest() {
		Empresa e = new Empresa();
		Requerimientos r = new Requerimientos(e);
		e.agregarEmpleado(new Empleado("Raul", Rol.ARQUITECTO, 5));
		e.agregarEmpleado(new Empleado("Marcos", Rol.ARQUITECTO, 5));
		
		r.setRequerimientosParaRol(Rol.ARQUITECTO, 2);
		
		assertEquals(2, r.getRequerimientosParaRol(Rol.ARQUITECTO));
	}
}
