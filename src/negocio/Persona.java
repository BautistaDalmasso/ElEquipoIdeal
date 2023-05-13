package negocio;

import java.util.Objects;

public class Persona
{
	public enum Rol { LIDERDEPROYECTO,
		  ARQUITECTO,
		  PROGRAMADOR,
		  TESTER
	};
		
	private String nombre;
	private Rol rol;
	private int calificacionHistorica;

	public Persona(String nombre, Rol rol, int calificacionHistorica)
	{	
		verificarCalificacion(calificacionHistorica);
		verificarNombre(nombre);
		this.nombre = nombre;
		this.rol = rol;
		this.calificacionHistorica = calificacionHistorica;
	}
					
	private static void verificarCalificacion(int calificacionHistorica)
	{
		if (calificacionHistorica < 1 || calificacionHistorica > 5)
			throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
	}
	
	private static void verificarNombre(String nombre)
	{
		if (nombre == null)
			throw new IllegalArgumentException("El nombre de la persona no puede ser null.");
		if (nombre.length() == 0)
			throw new IllegalArgumentException("El nombre de la persona no puede ser una cadena vacía.");
		if (nombre.length() < 2)
			throw new IllegalArgumentException("El nombre de la persona no puede tener menos de 2 caracteres.");
		if (nombre.length() > 30)
			throw new IllegalArgumentException("El nombre de la persona no puede tener más de 30 caracteres.");
	}
	
	public String getNombre()
	{
		return nombre;
	}

	public Rol getRol()
	{
		return rol;
	}
	
	public int getCalificacion()
	{
		return calificacionHistorica;
	}

	@Override
	public int hashCode() {
		return Objects.hash(calificacionHistorica, nombre, rol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Persona))
			return false;
		Persona other = (Persona) obj;
		return calificacionHistorica == other.calificacionHistorica && Objects.equals(nombre, other.nombre)
				&& rol == other.rol;
	}
}
