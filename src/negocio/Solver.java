package negocio;

public abstract class Solver {
	protected boolean equipoEsImposible;
	
	public abstract Equipo resolver() throws EquipoImposibleException;
	
	public String estadisticas() {
		return equipoEsImposible ? " EQUIPO IMPOSIBLE." : "";
	}
}
