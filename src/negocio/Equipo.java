package negocio;

import java.util.HashSet;
import java.util.Set;

import negocio.Empresa.Rol;

public class Equipo {
	private Empresa empresa;
	private Set<Empleado> empleados;
	private int calificacionTotal;
	private int[][] empleadosPorRol;
	
	public Equipo(Empresa empresa) {
		this.empresa = empresa;
		this.empleados = new HashSet<Empleado>();
		this.calificacionTotal = 0;
		inicializarEmpleadosPorRol();
	}
	
	private Equipo(Empresa empresa, Set<Empleado> empleados, int calificacionTotal, int[][] empleadosPorRol) {
		this.empresa = empresa;
		this.empleados = empleados;
		this.calificacionTotal = calificacionTotal;
		this.empleadosPorRol = empleadosPorRol;
	}

	private void inicializarEmpleadosPorRol() {
		Rol[] roles = Rol.values();
		this.empleadosPorRol = new int[roles.length][1];
	}
	
	public void agregarEmpleado(Empleado empleado) {
		this.empleados.add(empleado);
		this.calificacionTotal += empleado.getCalificacion();
		this.empleadosPorRol[empleado.getRol().ordinal()][0] += 1;
	}
	
	public void removerEmpleado(Empleado empleado) {
		this.empleados.remove(empleado);
		this.calificacionTotal -= empleado.getCalificacion();
		this.empleadosPorRol[empleado.getRol().ordinal()][0] -= 1;
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
		
		return new Equipo(this.empresa, copiaEmpleados, this.calificacionTotal, copiarEmpleadosPorRol());
	}
	
	private int[][] copiarEmpleadosPorRol() {
		Rol[] roles = Rol.values();
		int[][] ret = new int[roles.length][1];
		
		for (int i = 0; i < ret.length; i++) {
			ret[i][0] = this.empleadosPorRol[i][0];
		}
		
		return ret;
	}

	public boolean contieneEmpleado(Empleado empleado) {
		return this.empleados.contains(empleado);
	}
	
	public int getEmpleadosPorRol(Rol rol) {
		return this.empleadosPorRol[rol.ordinal()][0];
	}
	
	public int getCalificacionTotal() {
		return this.calificacionTotal;
	}

	public Set<Empleado> getEmpleados() {
		return empleados;
	}
}
