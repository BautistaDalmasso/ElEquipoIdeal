package negocio;

import java.util.Objects;

public class Empleado {
	private static final String NOMBRE_FOTO_DEFAULT = "default.png";
	
	private String nombre;
	private Rol rol;
	private int calificacionHistorica;
	private String nombreFoto;

	public Empleado(String nombre, Rol rol, int calificacionHistorica) {
		verificarCalificacion(calificacionHistorica);
		verificarNombre(nombre);
		this.nombre = nombre;
		this.rol = rol;
		this.calificacionHistorica = calificacionHistorica;
		this.nombreFoto = NOMBRE_FOTO_DEFAULT;
	}
	
	public Empleado(String nombre, Rol rol, int calificacionHistorica, String nombreFoto) {
		verificarCalificacion(calificacionHistorica);
		verificarNombre(nombre);
		this.nombre = nombre;
		this.rol = rol;
		this.calificacionHistorica = calificacionHistorica;
		this.nombreFoto = nombreFoto;
	}

	private static void verificarCalificacion(int calificacionHistorica) {
		if (calificacionHistorica < 1 || calificacionHistorica > 5)
			throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
	}

	private static void verificarNombre(String nombre) {
		if (nombre == null)
			throw new IllegalArgumentException("El nombre de la persona no puede ser null.");
		if (nombre.length() == 0)
			throw new IllegalArgumentException("El nombre de la persona no puede ser una cadena vacía.");
		if (nombre.length() < 2)
			throw new IllegalArgumentException("El nombre de la persona no puede tener menos de 2 caracteres.");
		if (nombre.length() > 30)
			throw new IllegalArgumentException("El nombre de la persona no puede tener más de 30 caracteres.");
	}

	public String getNombre() {
		return nombre;
	}

	public Rol getRol() {
		return rol;
	}

	public int getCalificacion() {
		return calificacionHistorica;
	}
	
	public String getNombreFoto() {
		return nombreFoto == null ? NOMBRE_FOTO_DEFAULT : nombreFoto;
	}
	
	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", rol=" + rol + ", calificacionHistorica=" + calificacionHistorica + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Empleado))
			return false;
		Empleado other = (Empleado) obj;
		return Objects.equals(nombre, other.nombre);
	}
}
