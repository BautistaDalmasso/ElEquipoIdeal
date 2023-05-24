package negocio;

public class SolverPorRoles extends Solver {
	private static final Rol TODOS_LOS_ROLES_LLENADOS = null;

	private Empresa empresa;
	private Requerimientos requerimientos;
	private Equipo mejorEquipo;
	private Equipo actual;

	private int casosBaseConsiderados;
	private int casosIncompatiblesDescartados;

	public SolverPorRoles(Empresa empresa, Requerimientos requerimientos) {
		super();
		this.empresa = empresa;
		this.requerimientos = requerimientos;
	}

	public Equipo resolver() throws EquipoImposibleException {
		this.actual = new Equipo(empresa);
		this.mejorEquipo = this.actual.copiar();

		this.casosBaseConsiderados = 0;
		this.casosIncompatiblesDescartados = 0;

		agregarEmpleadosPorRolDesde(Rol.values()[0], 0);

		if (!requerimientos.equipoCumpleConLosRequerimientos(mejorEquipo)) {
			this.equipoEsImposible = true;
			throw new EquipoImposibleException();
		}

		return this.mejorEquipo;
	}

	private void agregarEmpleadosPorRolDesde(Rol rol, int desde) {
		if (todosLosRolesLlenados(rol)) {
			this.manejarEvaluacionCasoBase();
			return;
		}
		if (requerimientosParaRolLlenados(rol)) {
			agregarEmpleadosPorRolDesde(obtenerSiguienteRol(rol), 0);
			return;
		}
		if (empleadosParaEvaluarAgotados(rol, desde)) {
			return;
		}

		Empleado empleadoSiendoEvaluado = obtenerEmpleadoDelRol(rol, desde);

		if (actual.tieneIncompatibilidadesConElEquipo(empleadoSiendoEvaluado)) {
			backtrack(rol, desde);
			return;
		}

		actual.agregarEmpleado(empleadoSiendoEvaluado);
		agregarEmpleadosPorRolDesde(rol, desde + 1);

		actual.removerEmpleado(empleadoSiendoEvaluado);
		agregarEmpleadosPorRolDesde(rol, desde + 1);
	}

	private boolean todosLosRolesLlenados(Rol rol) {
		return rol == TODOS_LOS_ROLES_LLENADOS;
	}

	private void manejarEvaluacionCasoBase() {
		if (actual.getCalificacionTotal() > mejorEquipo.getCalificacionTotal()) {
			Equipo nuevoMejor = actual.copiar();
			mejorEquipo = nuevoMejor;
			notificarObservers(nuevoMejor);
		}
		this.casosBaseConsiderados++;
	}

	private boolean empleadosParaEvaluarAgotados(Rol rol, int evaluados) {
		return empresa.getEmpleadosDeRol(rol).size() == evaluados;
	}

	private boolean requerimientosParaRolLlenados(Rol rol) {
		return requerimientos.getRequerimientosParaRol(rol) == actual.getEmpleadosPorRol(rol);
	}

	private Empleado obtenerEmpleadoDelRol(Rol rol, int indice) {
		return empresa.getEmpleadosDeRol(rol).get(indice);
	}

	private void backtrack(Rol rol, int desde) {
		this.casosIncompatiblesDescartados++;
		agregarEmpleadosPorRolDesde(rol, desde + 1);
	}

	private Rol obtenerSiguienteRol(Rol rol) {
		Rol[] roles = Rol.values();
		int siguiente = rol.ordinal() + 1;

		return siguiente < roles.length ? roles[siguiente] : TODOS_LOS_ROLES_LLENADOS;
	}

	public String estadisticas() {
		return "Casos bases considerados: " + casosBaseConsiderados + ". Casos descartados: "
				+ casosIncompatiblesDescartados + ". Incompatibilidades totales: "
				+ this.empresa.getIncompatibilidades() + super.estadisticas();
	}
}
