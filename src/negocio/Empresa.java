package negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Empresa {
	public enum Rol {
		LIDERDEPROYECTO, ARQUITECTO, PROGRAMADOR, TESTER
	}

	private List<Empleado> empleados;
	private Map<Empresa.Rol, List<Empleado>> empleadosPorRol;
	private Relaciones relaciones;
	private int incompatibilidades;
	
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
	
	public static Empresa cargarEmpresaDesdeArchivo(String nombreArchivo) throws IOException {
		Empresa ret = new Empresa();
		ArchivoEmpresa archivoEmpresa = ArchivoEmpresa.obtenerArchivoEmpresa(nombreArchivo);
		
		ret.cargarEmpleados(archivoEmpresa.getEmpleados());
		ret.cargarIncompatibilidades(archivoEmpresa.getParesIncompatibles());
		
		return ret;
	}

	private void cargarEmpleados(List<Empleado> empleados) {
		for (Empleado e : empleados) {
			this.agregarEmpleado(e);
		}
	}

	private void cargarIncompatibilidades(Set<ParIncompatible> paresIncompatibles) {
		Empleado e1;
		Empleado e2;
		for (ParIncompatible par : paresIncompatibles) {
			e1 = this.buscarEmpleadoPorNombre(par.getX());
			e2 = this.buscarEmpleadoPorNombre(par.getY());
			
			this.agregarIncompatibilidad(e1, e2);
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
		
		this.incompatibilidades++;
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
	
	public Set<ParIncompatible> paresIncompatibles() {
		return relaciones.toSetParesIncompatibles();
	}

	public Empleado buscarEmpleadoPorNombre(String nombreEmpleado) {
		for (Empleado e : empleados) {
			if (e.getNombre().equals(nombreEmpleado)) {
				return e;
			}
		}
		
		throw new IllegalArgumentException("El empleado: " + nombreEmpleado + " no existe.");
	}
	
	public List<Empleado> getEmpleadosDeRol(Empresa.Rol rol) {
		List<Empleado> empleadosDelRol = empleadosPorRol.get(rol);
		List<Empleado> ret = new ArrayList<Empleado>();
		
		ret.addAll(empleadosDelRol);

		return ret;
	}

	public boolean esEmpleado(Empleado p) {
		return this.empleados.contains(p);
	}
	
	public List<Empleado> getEmpleados() {
		return this.empleados;
	}
	
	public int getIncompatibilidades() {
		return incompatibilidades;
	}

	@Override
	public int hashCode() {
		return Objects.hash(empleados, relaciones);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Empresa))
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(empleados, other.empleados) && Objects.equals(relaciones, other.relaciones);
	}
}
