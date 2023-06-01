package negocio;

import java.util.ArrayList;
import java.util.List;

public abstract class Solver {
	private EstadisticasDeBusqueda estadisticas;
	private List<ObserverResultadosParciales> observers;
	
	public abstract Equipo resolver() throws EquipoImposibleException;
	
	public Solver() {
		observers = new ArrayList<ObserverResultadosParciales>();
		estadisticas = new EstadisticasDeBusqueda();
	}
	
	public void registrarObserver(ObserverResultadosParciales observer) {
		observers.add(observer);
	}
	
	protected void notificarObservers(Equipo equipo) {
		for (ObserverResultadosParciales observer : observers) {
			observer.notificar(equipo);
		}
	}
	
	public void casoConsiderado() {
		estadisticas.casoConsiderado();
	}

	public void casoDescartado() {
		estadisticas.casoDescartado();
	}
	
	public String stringEstadisticas() {
		return "Casos Considerados: " + estadisticas.getCasosConsiderados() + ". Casos Descartados: " + estadisticas.getCasosDescartados();
	}
}
