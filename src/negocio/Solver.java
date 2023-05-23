package negocio;

import java.util.ArrayList;
import java.util.List;

public abstract class Solver {
	protected boolean equipoEsImposible;
	private List<ObserverResultadosParciales> observers;
	
	public abstract Equipo resolver() throws EquipoImposibleException;
	
	public void registrarObserver(ObserverResultadosParciales observer) {
		if (observers == null) {
			observers = new ArrayList<ObserverResultadosParciales>();
		}
		observers.add(observer);
	}
	
	protected void notificarObservers(Equipo equipo) {
		for (ObserverResultadosParciales observer : observers) {
			observer.notificar(equipo);
		}
	}
	
	public String estadisticas() {
		return equipoEsImposible ? " EQUIPO IMPOSIBLE." : "";
	}
}
