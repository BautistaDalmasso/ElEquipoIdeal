package interfaz;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import negocio.Empresa;
import negocio.Equipo;
import negocio.ObserverResultadosParciales;
import negocio.Requerimientos;
import negocio.ResultadoParcialEquipo;
import negocio.Solver;
import negocio.SolverMultiple;

public class ObserverInterfaz extends SwingWorker<Equipo, ResultadoParcialEquipo> implements ObserverResultadosParciales {
	private Solver solver;
	private ElEquipoIdeal interfaz;
	
	public ObserverInterfaz(ElEquipoIdeal interfaz, Empresa empresa, Requerimientos requerimientos) {
		this.solver = new SolverMultiple(empresa, requerimientos);
		this.interfaz = interfaz;
		
		solver.registrarObserver(this);
	}
	
	@Override
	public void notificar(ResultadoParcialEquipo resultadoParcial) {
		this.publish(resultadoParcial);
	}

	@Override
	protected Equipo doInBackground() throws Exception {
		return solver.resolver();
	}
	
	@Override
	protected void process(List<ResultadoParcialEquipo> resultadosParciales) {
		ResultadoParcialEquipo ultimoResultado = resultadosParciales.get(resultadosParciales.size()-1);
		
		interfaz.equipoEncontrado(ultimoResultado.getEquipoEncontrado());
	}

	@Override
	protected void done() {
		try {
			interfaz.equipoEncontrado(get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
