package negocio;

import java.util.HashSet;
import java.util.Set;

public class Equipo {
	private Empresa empresa;
	private Set<Empleado> empleados;
	private int calificacionTotal;
	
	public Equipo(Empresa empresa) {
		this.empresa = empresa;
		this.empleados = new HashSet<Empleado>();
		this.calificacionTotal = 0;
	}
	
	private Equipo(Empresa empresa, Set<Empleado> empleados, int calificacionTotal) {
		this.empresa = empresa;
		this.empleados = empleados;
		this.calificacionTotal = calificacionTotal;
	}
	
	public void agregarEmpleado(Empleado empleado) {
		this.empleados.add(empleado);
		this.calificacionTotal += empleado.getCalificacion();
	}
	
	public void removerEmpleado(Empleado empleado) {
		this.empleados.remove(empleado);
		this.calificacionTotal -= empleado.getCalificacion();
	}
	
	public boolean tieneIncompatibilidadesConElEquipo(Empleado empleado) {
		for (Empleado miembroDelEquipo : this.empleados) {
			if (empresa.sonIncompatibles(empleado, miembroDelEquipo)) {
				return true;
			}
		}
		return false;
	}
	
	public Equipo copiar() {
		Set<Empleado> copiaEmpleados = new HashSet<Empleado>(this.empleados);
		
		return new Equipo(this.empresa, copiaEmpleados, this.calificacionTotal);
	}
	
	public boolean contieneEmpleado(Empleado empleado) {
		return this.empleados.contains(empleado);
	}
	
	public int getCalificacionTotal() {
		return this.calificacionTotal;
	}
}
