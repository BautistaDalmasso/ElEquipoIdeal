package test;

import negocio.Empresa;
import negocio.ISolver;
import negocio.Requerimientos;

public abstract class CreadorSolver {
	abstract ISolver crearSolver(Empresa empresa, Requerimientos requerimientos);
}
