package negocio;

public class SolverMultiple extends Solver implements ObserverResultadosParciales {
	private Equipo mejorEquipo;
	private Empresa empresa;
	private Requerimientos requerimientos;
	
	private Solver[] solvers;
	
	private EstadisticasDeBusqueda[] estadisticasObtenidas;
	private int estadisticasActuales;
	
	public SolverMultiple(Empresa empresa, Requerimientos requerimientos) {
		super();
		this.empresa = empresa;
		this.requerimientos = requerimientos;
		
		mejorEquipo = new Equipo(empresa);
		
		inicializarSolvers();
		inicializarEstadisticas();
	}

	private void inicializarSolvers() {
		solvers = new Solver[2];
		
		solvers[0] = new SolverHeuristicoGoloso(empresa, requerimientos);
		solvers[1] = new SolverPorRoles(empresa, requerimientos);
	}
	
	private void inicializarEstadisticas() {
		this.estadisticasActuales = -1;
		this.estadisticasObtenidas = new EstadisticasDeBusqueda[solvers.length];
		
		for (int i = 0; i < estadisticasObtenidas.length; i++) {
			estadisticasObtenidas[i] = new EstadisticasDeBusqueda();
		}
	}

	@Override
	public void notificar(ResultadoParcialEquipo resultadoParcial) {
		this.estadisticasObtenidas[estadisticasActuales] = resultadoParcial.getEstadisticas();
		
		if (resultadoParcial.getCalificacionTotal() > mejorEquipo.getCalificacionTotal()) {
			mejorEquipo = resultadoParcial.getEquipoEncontrado();
			notificarObservers(resultadoParcial);
		}
	}

	@Override
	public Equipo resolver() throws EquipoImposibleException {
		int soluciones = 0;
		for (Solver solver : solvers) {
			solver.registrarObserver(this);
			estadisticasActuales++;
			
			try {				
				notificarObservers(solver.resolver());
			}
			catch (EquipoImposibleException e) {
				continue;
			}
			soluciones++;
		}
		
		if (soluciones == 0) {
			throw new EquipoImposibleException();
		}
		
		return mejorEquipo;
	}

	@Override
	public EstadisticasDeBusqueda getEstadisticas() {
		EstadisticasDeBusqueda ret = new EstadisticasDeBusqueda();
		
		for (EstadisticasDeBusqueda estadisticas : this.estadisticasObtenidas) {
			ret = ret.obtenerSuma(estadisticas);
		}
		
		return ret;
	}
}
