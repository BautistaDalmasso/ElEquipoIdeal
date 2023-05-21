package negocio;

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Empleado {
	private String nombre;
	private Empresa.Rol rol;
	private int calificacionHistorica;

	public Empleado(String nombre, Empresa.Rol rol, int calificacionHistorica) {
		verificarCalificacion(calificacionHistorica);
		verificarNombre(nombre);
		this.nombre = nombre;
		this.rol = rol;
		this.calificacionHistorica = calificacionHistorica;
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

	public Empresa.Rol getRol() {
		return rol;
	}

	public int getCalificacion() {
		return calificacionHistorica;
	}
	
	@Override
	public String toString() {
		return "Empleado [nombre=" + nombre + ", rol=" + rol + ", calificacionHistorica=" + calificacionHistorica + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(calificacionHistorica, nombre, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Empleado))
			return false;
		Empleado other = (Empleado) obj;
		return calificacionHistorica == other.calificacionHistorica && Objects.equals(nombre, other.nombre)
				&& rol == other.rol;
	}
	public static Comparator<Empleado> sortPorMayorCalificacion() {
		return new Comparator<Empleado>() {
			@Override
			public int compare(Empleado uno, Empleado otro) {
				return -uno.getCalificacion() + otro.getCalificacion();
				}
			};
		}
	
	public static Comparator<Empleado> sortPorMenorIncompatibilidad(Empresa e) {
		return new Comparator<Empleado>() {
			@Override
			public int compare(Empleado uno, Empleado otro) {	
				return uno.getCantIncompatibles(e) - otro.getCantIncompatibles(e);	
				}
			};
		}
	
	public int getCantIncompatibles(Empresa e) {
		Map<Empleado, Set<Empleado>> incompatibles = 
				e.getRelaciones().getEmpleadosConIncompatibilidades();
		return incompatibles.get(this) == null ? 0 : incompatibles.get(this).size();
	}
}
