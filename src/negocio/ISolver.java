package negocio;

public interface ISolver {
	public Equipo resolver() throws EquipoImposibleException;
	
	public String estadisticas();
}
