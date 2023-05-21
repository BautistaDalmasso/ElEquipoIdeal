package test;

import negocio.Solver;
import negocio.SolverHeuristicoGoloso;

public class SolverHeuristicoGolosoTest extends SolverGenericoTest {

	@Override
	Solver obtenerSolver() {
		return new SolverHeuristicoGoloso(empresaActual, requerimientosActuales);
	}

}
