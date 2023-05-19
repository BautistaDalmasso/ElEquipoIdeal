package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import negocio.Empleado;
import negocio.Empresa;
import negocio.Requerimientos;
import negocio.SolverPorRoles;
import negocio.Empresa.Rol;
import negocio.Equipo;
import negocio.EquipoImposibleException;

public class SolverTest {
	private static Empresa empresaActual;
	private static Requerimientos requerimientosActuales;
	private static Set<Empleado> empleadosEsperados;
	
	@Test( expected = EquipoImposibleException.class )
	public void equipoImposibleTest() throws EquipoImposibleException {
		setEmpresaConIncompatibilidades();
		setRequerimientosImposibles();
		
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		solver.resolver();
	}

	@Test
	public void mejorEquipoSinIncompatibilidadesTest() {
		setEmpresaSimple();
		setRequerimientosSimples();
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		try {
			assertEquals(empleadosEsperados, solver.resolver().getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void mejorEquipoConAlgunasIncompatibilidadesTest() {
		setEmpresaConIncompatibilidades();
		setRequerimientosSimples();
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		try {
			assertEquals(empleadosEsperados, solver.resolver().getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mejorEquipoEmpresaComplejaTest() {
		setEmpresaCompleja();
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		try {
			Equipo resultado = solver.resolver();
			assertEquals(empleadosEsperados, resultado.getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mejorEquipoConRequerimientoEn0Test() {
		setEmpresaCompleja();
		requerimientosActuales.setRequerimientosParaRol(Rol.ARQUITECTO, 0);
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		try {
			Equipo resultado = solver.resolver();
			assertEquals(20, resultado.getCalificacionTotal());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}
	
	private void setEmpresaConIncompatibilidades() {
		empresaActual = new Empresa();
		empleadosEsperados = new HashSet<Empleado>();
		Empleado lider = new Empleado("lider", Rol.LIDERDEPROYECTO, 5);
		Empleado arquitectoIncompatible = new Empleado("arquitecto i", Rol.ARQUITECTO, 5);
		Empleado arquitectoCompatible = new Empleado("arquitecto c", Rol.ARQUITECTO, 4);
		Empleado programador = new Empleado("programador", Rol.PROGRAMADOR, 5);
		Empleado tester = new Empleado("tester", Rol.TESTER, 5);
		
		agregarEmpleados(empresaActual, new Empleado[] {lider, arquitectoIncompatible, arquitectoCompatible, programador, tester});
		
		agregarEmpleadosEsperados(new Empleado[] {lider, arquitectoCompatible, programador, tester});
		
		empresaActual.agregarIncompatibilidad(lider, arquitectoIncompatible);
	}
	
	private static void setEmpresaSimple() {
		empresaActual = new Empresa();
		empleadosEsperados = new HashSet<Empleado>();
		
		Empleado nuevoEmpleado;
		for (Rol rol : Rol.values()) {
			nuevoEmpleado = new Empleado("Raul", rol, 5);
			empresaActual.agregarEmpleado(new Empleado("Raul", rol, 5));
			empleadosEsperados.add(nuevoEmpleado);
		}
	}
	
	private static void setRequerimientosSimples() {
		requerimientosActuales = new Requerimientos(empresaActual);
		
		for (Rol rol : Rol.values()) {
			requerimientosActuales.setRequerimientosParaRol(rol, 1);
		}
	}

	private void setRequerimientosImposibles() {
		requerimientosActuales = new Requerimientos(empresaActual);
		requerimientosActuales.setRequerimientosParaRol(Rol.LIDERDEPROYECTO, 1);
		requerimientosActuales.setRequerimientosParaRol(Rol.ARQUITECTO, 2);
		requerimientosActuales.setRequerimientosParaRol(Rol.PROGRAMADOR, 1);
		requerimientosActuales.setRequerimientosParaRol(Rol.TESTER, 1);
	}
	
	private void setEmpresaCompleja() {
		empresaActual = new Empresa();
		empleadosEsperados = new HashSet<Empleado>();
		requerimientosActuales = new Requerimientos(empresaActual);
		
		Empleado lider1 = new Empleado("lid1", Rol.LIDERDEPROYECTO, 5);
		Empleado lider2 = new Empleado("lid2", Rol.LIDERDEPROYECTO, 3);
		
		Empleado arqu1 = new Empleado("arq1", Rol.ARQUITECTO, 5);
		Empleado arqu2 = new Empleado("arq2", Rol.ARQUITECTO, 5);
		Empleado arqu3 = new Empleado("arq3", Rol.ARQUITECTO, 2);
		Empleado arqu4 = new Empleado("arq4", Rol.ARQUITECTO, 3);
		
		Empleado prog1 = new Empleado("prog1", Rol.PROGRAMADOR, 5);
		Empleado prog2 = new Empleado("prog2", Rol.PROGRAMADOR, 5);
		Empleado prog3 = new Empleado("prog3", Rol.PROGRAMADOR, 2);
		Empleado prog4 = new Empleado("prog4", Rol.PROGRAMADOR, 1);
		
		Empleado tester1 = new Empleado("tester1", Rol.TESTER, 5);
		Empleado tester2 = new Empleado("tester2", Rol.TESTER, 1);
		
		agregarEmpleados(empresaActual, new Empleado[] {lider1, lider2, arqu1, arqu2, arqu3, arqu4, prog1, prog2, prog3, prog4, tester1, tester2});

		empresaActual.agregarIncompatibilidad(lider1, arqu2);
		empresaActual.agregarIncompatibilidad(lider1, arqu4);
		
		empresaActual.agregarIncompatibilidad(arqu1, prog1);
		empresaActual.agregarIncompatibilidad(arqu1, prog2);
		
		agregarEmpleadosEsperados(new Empleado[] {lider2, arqu2, arqu3, arqu4, prog1, prog2, tester1});
		
		requerimientosActuales.setRequerimientosParaRol(Rol.LIDERDEPROYECTO, 1);
		requerimientosActuales.setRequerimientosParaRol(Rol.ARQUITECTO, 3);
		requerimientosActuales.setRequerimientosParaRol(Rol.PROGRAMADOR, 2);
		requerimientosActuales.setRequerimientosParaRol(Rol.TESTER, 1);
	}
	
	private static void agregarEmpleados(Empresa empresa, Empleado[] empleados) {
		for (Empleado e : empleados) {
			empresa.agregarEmpleado(e);
		}
	}
	
	private static void agregarEmpleadosEsperados(Empleado[] empleados) {
		for (Empleado e : empleados) {
			empleadosEsperados.add(e);
		}
	}
}
