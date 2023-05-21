package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import negocio.Rol;
import negocio.Solver;
import negocio.Equipo;
import negocio.EquipoImposibleException;

public abstract class SolverExactoTest extends SolverGenericoTest {
	/*
	 * Contiene tests que deben cumplirse para cualquier Solver exacto (de fuerza bruta o backtracking).
	 * Estos tests son muy reutilizables ya que siempre podemos predecir la solución exacta para un input dado.
	 */

	
	@Test( expected = EquipoImposibleException.class )
	public void equipoImposibleTest() throws EquipoImposibleException, IOException {
		setEmpresaConIncompatibilidades();
		setRequerimientosImposibles();
		
		Solver solver = obtenerSolver();
		
		solver.resolver();
	}

	@Test
	public void mejorEquipoSinIncompatibilidadesTest() {
		setEmpresaSimple();
		setRequerimientosSimples();
		Solver solver = obtenerSolver();
		
		try {
			assertEquals(empleadosEsperados, solver.resolver().getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void mejorEquipoConAlgunasIncompatibilidadesTest() throws IOException {
		setEmpresaConIncompatibilidades();
		setRequerimientosSimples();
		Solver solver = obtenerSolver();
		
		try {
			assertEquals(empleadosEsperados, solver.resolver().getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mejorEquipoEmpresaComplejaTest() throws IOException {
		setEmpresaCompleja();
		Solver solver = obtenerSolver();
		
		try {
			Equipo resultado = solver.resolver();
			assertEquals(empleadosEsperados, resultado.getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mejorEquipoConRequerimientoEn0Test() throws IOException {
		setEmpresaCompleja();
		requerimientosActuales.setRequerimientosParaRol(Rol.ARQUITECTO, 0);
		Solver solver = obtenerSolver();
		
		try {
			Equipo resultado = solver.resolver();
			assertEquals(20, resultado.getCalificacionTotal());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}
}
