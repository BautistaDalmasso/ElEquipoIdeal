package negocio;

public class EquipoImposibleException extends Exception {
	private static final long serialVersionUID = 5134785055485169569L;
	
	public EquipoImposibleException() {
		super("No es posible crear un equipo sin incompatibilidades que cumpla con los requerimientos dados.");
	}
}
