package interfaz;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import negocio.Empresa;
import negocio.Equipo;
import negocio.ObserverResultadosParciales;
import negocio.Requerimientos;
import negocio.Solver;
import negocio.SolverMultiple;

public class ObserverInterfaz extends SwingWorker<Equipo, Equipo> implements ObserverResultadosParciales {
	private Solver solver;
	private ElEquipoIdeal interfaz;
	
	public ObserverInterfaz(ElEquipoIdeal interfaz, Empresa empresa, Requerimientos requerimientos) {
		this.solver = new SolverMultiple(empresa, requerimientos);
		this.interfaz = interfaz;
		
		solver.registrarObserver(this);
	}
	
	@Override
	public void notificar(Equipo equipoParcial) {
		System.out.println(equipoParcial.getCalificacionTotal());
		
		this.publish(equipoParcial);
	}

	@Override
	protected Equipo doInBackground() throws Exception {
		return solver.resolver();
	}
	
	@Override
	protected void process(List<Equipo> equipos) {
		interfaz.equipoEncontrado(equipos.get(equipos.size()-1));
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
