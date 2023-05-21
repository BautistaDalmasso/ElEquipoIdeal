package test;

import static org.junit.Assert.*;

import org.junit.Test;

import negocio.Empleado;
import negocio.Empresa;
import negocio.EquipoImposibleException;
import negocio.Rol;
import negocio.Solver;
import negocio.SolverHeuristicoGoloso;

public class SolverHeuristicoGolosoTest extends SolverGenericoTest {

	@Test
	public void empleadoConMenosIncompatibilidadesTest() {
		crearEmpresaParaTestearOrdenamiento();
		
		Solver solver = obtenerSolver();
		
		try {
			assertEquals(empleadosEsperados, solver.resolver().getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}

	private void crearEmpresaParaTestearOrdenamiento() {
		empresaActual = new Empresa();
		Empleado lider1 = new Empleado("lider1", Rol.LIDERDEPROYECTO, 5);
		Empleado lider2 = new Empleado("lider2", Rol.LIDERDEPROYECTO, 5);
		Empleado lider3 = new Empleado("lider3", Rol.LIDERDEPROYECTO, 5);
		
		agregarEmpleados(new Empleado[] {lider1, lider2, lider3});
		empresaActual.agregarIncompatibilidad(lider1, lider3);
		
		crearRequerimientosActuales(new int[] {1, 0, 0, 0});
		
		crearEmpleadosEsperados(new String[] {"lider2"});
	}
	
	
	@Override
	Solver obtenerSolver() {
		return new SolverHeuristicoGoloso(empresaActual, requerimientosActuales);
	}

}
