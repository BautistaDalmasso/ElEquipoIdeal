package negocio;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


public class SolverHeuristicoGoloso implements ISolver {
	
	private Empresa empresa;
	private Requerimientos requerimientos;
	private Equipo mejorEquipo;

	// para mejorar la heurística, se hace un Map con listas de <Empleado> ordenada según:
	// mejor calificados con menor cantidad de incompatibilidades primero
	private HashMap<Rol, List<Empleado>> empleadosOrdenados;

	private int empleadosConsiderados;
	
	public SolverHeuristicoGoloso(Empresa e, Requerimientos r) {
		this.empresa = e;
		this.requerimientos = r;
		this.mejorEquipo = new Equipo(empresa);
		this.empleadosOrdenados = new HashMap<Rol, List<Empleado>>();
	}
	
	public Equipo resolver() throws EquipoImposibleException {
		inicializarEstadisticas();
		sortEmpleadosTodosLosRoles();
		armarEquipo();
		
		return this.mejorEquipo;
	}

	private void inicializarEstadisticas() {
		this.empleadosConsiderados = 0;
	}

	private void armarEquipo() throws EquipoImposibleException {		
		Set <Rol> roles = empleadosOrdenados.keySet();
		for (Rol rol: roles) {
			int cant = requerimientos.getRequerimientosParaRol(rol);
			List <Empleado> empleadoRol = empleadosOrdenados.get(rol);
			buscarEmpleadoNuevo(cant, empleadoRol);
		}
	}

	private void buscarEmpleadoNuevo(int cant, List<Empleado> empleadoRol) throws EquipoImposibleException {
		Empleado nuevo;
		try {
			for (int i = 0; i < cant; i++) {
				nuevo = empleadoRol.get(i);
				if (!mejorEquipo.tieneIncompatibilidadesConElEquipo(nuevo)) {
					mejorEquipo.agregarEmpleado(nuevo);
				}
				this.empleadosConsiderados++;
			}
		} catch (IndexOutOfBoundsException e) {
			throw new EquipoImposibleException();
		}
	}	

	private void sortEmpleadosTodosLosRoles() {
		for (Rol rol: Rol.values())
			empleadosOrdenados.put(rol, sortEmpleados(rol));
	}

	private List <Empleado> sortEmpleados(Rol rol) {
		List <Empleado> empleados = empresa.getEmpleadosDeRol(rol);	
		ordenarPorIncompatibilidad(empleados);
		ordenarPorCalificacion(empleados);
		return empleados;
	}

	private void ordenarPorIncompatibilidad(List<Empleado> empleados) {
		Collections.sort(empleados, new Comparator<Empleado>() {
			@Override
			public int compare(Empleado o1, Empleado o2) {
				return empresa.getCantidadIncompatibles(o1) - empresa.getCantidadIncompatibles(o2);
			}
		});
	}

	private void ordenarPorCalificacion(List<Empleado> empleados) {
		Collections.sort(empleados, new Comparator<Empleado>() {
			@Override
			public int compare(Empleado o1, Empleado o2) {
				return -o1.getCalificacion() + o2.getCalificacion();
			}
		});
	}

	public Equipo getMejorEquipo() {
		return mejorEquipo;
	}

	@Override
	public String estadisticas() {
		return "Empleados considerados: " + this.empleadosConsiderados;
	}
}
