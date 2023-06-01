package negocio;

public class SolverMultiple extends Solver implements ObserverResultadosParciales {
	private Equipo mejorEquipo;
	private Empresa empresa;
	private Requerimientos requerimientos;
	
	private Solver[] solvers;
	
	public SolverMultiple(Empresa empresa, Requerimientos requerimientos) {
		super();
		this.empresa = empresa;
		this.requerimientos = requerimientos;
		
		mejorEquipo = new Equipo(empresa);
		
		inicializarSolvers();
	}
	
	private void inicializarSolvers() {
		solvers = new Solver[2];
		
		solvers[0] = new SolverHeuristicoGoloso(empresa, requerimientos);
		solvers[1] = new SolverPorRoles(empresa, requerimientos);
	}

	@Override
	public void notificar(ResultadoParcialEquipo resultadoParcial) {
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

}
