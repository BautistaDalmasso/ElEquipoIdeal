package test;

import negocio.Solver;
import negocio.SolverPorListaCompleta;

public class SolverPorListaCompletaTest extends SolverExactoTest {

	@Override
	Solver obtenerSolver() {
		return new SolverPorListaCompleta(empresaActual, requerimientosActuales);
	}

}
