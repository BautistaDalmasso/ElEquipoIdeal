package interfaz;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import negocio.Empresa;
import negocio.Equipo;
import negocio.EquipoImposibleException;
import negocio.ObserverResultadosParciales;
import negocio.Requerimientos;
import negocio.ResultadoParcialEquipo;
import negocio.Solver;
import negocio.SolverMultiple;

public class ObserverInterfaz extends SwingWorker<Equipo, ResultadoParcialEquipo> implements ObserverResultadosParciales {
	private static final String[] ESTADOS_POSIBLES = {
			"Ningun equipo encontrado todavia...",
			"El equipo encontrado podria no ser optimo...",
			"Resultado optimo obtenido.",
			"Equipo obtenido podria no ser optimo.",
			"Imposible satisfacer los requerimientos."
	};
	
	private static enum EstadoDeBusqueda {
		NINGUNO_ENCONTRADO,
		RESULTADO_PARCIAL_OBTENIDO,
		BUSQUEDA_FINALIZADA,
		BUSQUEDA_DETENIDA,
		EQUIPO_IMPOSIBLE,
	}
	
	private Solver solver;
	private ElEquipoIdeal interfaz;

	private EstadoDeBusqueda estado;
	
	public ObserverInterfaz(ElEquipoIdeal interfaz, Empresa empresa, Requerimientos requerimientos) {
		this.solver = new SolverMultiple(empresa, requerimientos);
		this.interfaz = interfaz;
		
		solver.registrarObserver(this);

		estado = EstadoDeBusqueda.NINGUNO_ENCONTRADO;
		interfaz.actualizarEstado(ESTADOS_POSIBLES[estado.ordinal()]);
		
		iniciarBusqueda();
	}
	
	private void iniciarBusqueda() {
		interfaz.iniciarBusqueda();
	}

	@Override
	public void notificar(ResultadoParcialEquipo resultadoParcial) {
		estado = EstadoDeBusqueda.RESULTADO_PARCIAL_OBTENIDO;
		this.publish(resultadoParcial);
	}

	@Override
	protected Equipo doInBackground() throws Exception {
		try {			
			return solver.resolver();
		}
		catch (EquipoImposibleException e) {
			estado = EstadoDeBusqueda.EQUIPO_IMPOSIBLE;
			throw e;
		}
	}
	
	@Override
	protected void process(List<ResultadoParcialEquipo> resultadosParciales) {
		ResultadoParcialEquipo ultimoResultado = resultadosParciales.get(resultadosParciales.size()-1);
		
		interfaz.equipoEncontrado(ultimoResultado.getEquipoEncontrado());
		interfaz.actualizarEstadisticas(ultimoResultado.getEstadisticas());
		interfaz.actualizarEstado(ESTADOS_POSIBLES[estado.ordinal()]);
	}

	@Override
	protected void done() {
		terminarBusqueda();
		
		try {
			manejarBusquedaFinalizada();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		interfaz.actualizarEstado(ESTADOS_POSIBLES[estado.ordinal()]);
	}

	private void manejarBusquedaFinalizada() throws InterruptedException, ExecutionException {
		if (!isCancelled()) {				
			estado = EstadoDeBusqueda.BUSQUEDA_FINALIZADA;
			interfaz.equipoEncontrado(get());
		}
		else {				
			estado = EstadoDeBusqueda.BUSQUEDA_DETENIDA;
		}
	}

	private void terminarBusqueda() {
		this.interfaz.busquedaTerminada();
	}
}
