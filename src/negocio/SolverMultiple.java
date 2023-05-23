package negocio;

public class SolverMultiple extends Solver implements ObserverResultadosParciales {
	private Equipo mejorEquipo;
	private Empresa empresa;
	private Requerimientos requerimientos;
	
	private Solver[] solvers;
	
	public SolverMultiple(Empresa empresa, Requerimientos requerimientos) {
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
	public void notificar(Equipo equipoParcial) {
		if (equipoParcial.getCalificacionTotal() > mejorEquipo.getCalificacionTotal()) {
			mejorEquipo = equipoParcial;
			notificarObservers(equipoParcial);
		}
	}

	@Override
	public Equipo resolver() throws EquipoImposibleException {

		for (Solver solver : solvers) {
			solver.registrarObserver(this);
			
			this.notificar(solver.resolver());
		}
		
		return mejorEquipo;
	}

}
