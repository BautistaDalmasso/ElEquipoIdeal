package negocio;

import java.util.List;

public class SolverAlternativo {
	private Empresa empresa;
	private Requerimientos requerimientos;
	private Equipo mejorEquipo;
	private Equipo actual;

	private int casosBaseConsiderados;
	private int casosIncompatiblesDescartados;

	public SolverAlternativo(Empresa empresa, Requerimientos requerimientos) {
		this.empresa = empresa;
		this.requerimientos = requerimientos;
	}

	public Equipo resolver() throws EquipoImposibleException {
		this.actual = new Equipo(empresa);
		this.mejorEquipo = this.actual.copiar();

		this.casosBaseConsiderados = 0;
		this.casosIncompatiblesDescartados = 0;

		agregarEmpleadosDesde(0);

		if (!requerimientos.equipoCumpleConLosRequerimientos(mejorEquipo)) {
			throw new EquipoImposibleException();
		}

		return this.mejorEquipo;
	}

	private void agregarEmpleadosDesde(int desde) {
		List<Empleado> empleados = empresa.getEmpleados();
		if (requerimientos.equipoCumpleConLosRequerimientos(actual)) {
			manejarEvaluacionCasoBase();
			return;
		}
	}

	private void manejarEvaluacionCasoBase() {
		if (actual.getCalificacionTotal() > mejorEquipo.getCalificacionTotal()) {
			mejorEquipo = actual.copiar();
		}
		this.casosBaseConsiderados++;
	}

	private void backtrack(int desde) {
		this.casosIncompatiblesDescartados++;
		agregarEmpleadosDesde(desde + 1);
	}

	public String estadisticas() {
		return "Casos bases considerados: " + casosBaseConsiderados + ". Casos descartados: "
				+ casosIncompatiblesDescartados + ". Incompatibilidades totales: "
				+ this.empresa.getIncompatibilidades();
	}
}
