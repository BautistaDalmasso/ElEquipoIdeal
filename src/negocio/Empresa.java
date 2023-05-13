package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Empresa {
	public enum Rol {
		LIDERDEPROYECTO, ARQUITECTO, PROGRAMADOR, TESTER
	}

	private List<Empleado> empleados;
	private Map<Empresa.Rol, List<Empleado>> empleadosPorRol;
	private Relaciones relaciones;

	public Empresa() {
		this.empleados = new ArrayList<Empleado>();
		this.relaciones = new Relaciones();
		inicializarEmpleadosPorRol();
	}

	private void inicializarEmpleadosPorRol() {
		empleadosPorRol = new HashMap<Empresa.Rol, List<Empleado>>();

		for (Empresa.Rol rol : Empresa.Rol.values()) {
			empleadosPorRol.put(rol, new ArrayList<Empleado>());
		}
	}

	public void agregarEmpleado(Empleado empleado) {
		prevenirEmpleadoInvalidoORepetido(empleado);

		this.empleados.add(empleado);
		empleadosPorRol.get(empleado.getRol()).add(empleado);
	}

	private void prevenirEmpleadoInvalidoORepetido(Empleado empleado) {
		prevenirEmpleadoNulo(empleado);

		if (empleados.contains(empleado)) {
			throw new IllegalArgumentException("El empleado: " + empleado + " ya se encuentra en la empresa.");
		}
	}

	static void prevenirEmpleadoNulo(Empleado empleado) {
		if (empleado == null) {
			throw new IllegalArgumentException("El empleado no puede ser null.");
		}
	}

	public void agregarIncompatibilidad(Empleado empleado1, Empleado empleado2) {
		prevenirEmpleadoNuloONoAgregado(empleado1); prevenirEmpleadoNuloONoAgregado(empleado2);
		
		this.relaciones.agregarIncompatibilidad(empleado1, empleado2);
	}
	
	public boolean sonIncompatibles(Empleado empleado1, Empleado empleado2) {
		prevenirEmpleadoNuloONoAgregado(empleado1); prevenirEmpleadoNuloONoAgregado(empleado2);
		
		return this.relaciones.sonIncompatibles(empleado1, empleado2);
	}
	
	private void prevenirEmpleadoNuloONoAgregado(Empleado empleado) {
		prevenirEmpleadoNulo(empleado);
		
		if (!esEmpleado(empleado)) {
			throw new IllegalArgumentException("El empleado: " + empleado + " no es parte de la empresa.");
		}
	}
	
	public List<Empleado> getEmpleadosDeRol(Empresa.Rol rol) {
		List<Empleado> empleadosDelRol = empleadosPorRol.get(rol);

		return new ArrayList<Empleado>(empleadosDelRol);
	}

	public boolean esEmpleado(Empleado p) {
		return this.empleados.contains(p);
	}
}
