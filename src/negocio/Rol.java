package negocio;

public enum Rol {
	LIDERDEPROYECTO, 
	ARQUITECTO, 
	PROGRAMADOR, 
	TESTER;
	
	public static final String[] nombresRoles = {"Lider de Proyecto", "Arquitecto", "Programador", "Tester"};
	public static final String[] nombresPluralesRoles = {"Lideres de Proyecto", "Arquitectos", "Programadores", "Testers"};
	
	public static Rol fromString(String nombreRol) {
		switch (nombreRol) {
		case "Lider de Proyecto":
			return LIDERDEPROYECTO;
		case "Arquitecto":
			return ARQUITECTO;
		case "Programador":
			return PROGRAMADOR;
		case "Tester":
			return TESTER;
		default:
			throw new IllegalArgumentException("Rol no reconocido: " + nombreRol);
		}
	}
	
	public String toString() {
		return nombresRoles[this.ordinal()];
	}
}
