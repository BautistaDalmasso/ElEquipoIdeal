package test;

import negocio.Solver;
import negocio.SolverPorRoles;

public class SolverPorRolesTest extends SolverExactoTest {

	@Override
	Solver obtenerSolver() {
		return new SolverPorRoles(empresaActual, requerimientosActuales);
	}
}
