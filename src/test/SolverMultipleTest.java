package test;

import java.io.IOException;

import negocio.Solver;
import negocio.SolverMultiple;

public class SolverMultipleTest extends SolverExactoTest {

	@Override
	Solver obtenerSolver() {
		return new SolverMultiple(empresaActual, requerimientosActuales);
	}

	@Override
	public void mejorEquipoEmpresaComplejaTest() throws IOException {
		super.mejorEquipoEmpresaComplejaTest();
	}

	@Override
	public void mejorEquipoConAlgunasIncompatibilidadesTest() throws IOException {
		super.mejorEquipoConAlgunasIncompatibilidadesTest();
	}
}
