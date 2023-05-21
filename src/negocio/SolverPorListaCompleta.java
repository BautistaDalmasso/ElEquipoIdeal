package negocio;

public class SolverPorListaCompleta implements ISolver {
	private Empresa empresa;
	private Requerimientos requerimientos;
	private Equipo mejorEquipo;
	private Equipo actual;

	private int casosBaseConsiderados;
	private int casosIncompatiblesDescartados;

	public SolverPorListaCompleta(Empresa empresa, Requerimientos requerimientos) {
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
		if (requerimientos.equipoCumpleConLosRequerimientos(actual)) {
			manejarEvaluacionCasoBase();
			return;
		}
		if (empleadosAgotados(desde)) {
			return;
		}
		
		Empleado empleadoSiendoEvaluado = empresa.getEmpleados().get(desde);
		
		if (rolLleno(empleadoSiendoEvaluado) ||
				actual.tieneIncompatibilidadesConElEquipo(empleadoSiendoEvaluado)) {
			backtrack(desde);
			return;
		}
		
		actual.agregarEmpleado(empleadoSiendoEvaluado);
		agregarEmpleadosDesde(desde + 1);

		actual.removerEmpleado(empleadoSiendoEvaluado);
		agregarEmpleadosDesde(desde + 1);
	}

	private void manejarEvaluacionCasoBase() {
		if (actual.getCalificacionTotal() > mejorEquipo.getCalificacionTotal()) {
			mejorEquipo = actual.copiar();
		}
		this.casosBaseConsiderados++;
	}

	private boolean rolLleno(Empleado empleadoSiendoEvaluado) {
		Rol rol = empleadoSiendoEvaluado.getRol();
		return requerimientos.getRequerimientosParaRol(rol) < actual.getEmpleadosPorRol(rol);
	}
	
	private boolean empleadosAgotados(int evaluados) {
		return evaluados >= empresa.getEmpleados().size();
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
