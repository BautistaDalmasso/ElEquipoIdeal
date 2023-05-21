package negocio;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import negocio.Empresa.Rol;

public class SolverHeuristico {
	
	private Empresa empresa;
	private Requerimientos requerimientos;
	private Equipo mejorEquipo;

	// para mejorar la heurística, se hace un Map con listas de <Empleado> ordenada según:
	// mejor calificados con menor cantidad de incompatibilidades primero
	private HashMap<String, List<Empleado>> empleadosOrdenados;

	public SolverHeuristico(Empresa e, Requerimientos r) {
		this.empresa = e;
		this.requerimientos = r;
		this.mejorEquipo = new Equipo(empresa);
		this.empleadosOrdenados = new HashMap<String, List<Empleado>>();
	}
	
	public void resolver() throws EquipoImposibleException {	
		sortEmpleadosTodosLosRoles();
		armarEquipo();
	}

	private void armarEquipo() throws EquipoImposibleException {		
		Set <String> roles = empleadosOrdenados.keySet();
		for (String rol: roles) {
			int cant = requerimientos.getRequerimientosParaRol(Rol.valueOf(rol));
			System.out.println("Cargo "+Rol.valueOf(rol).name() + 
					" requiere cantidad: " + cant);
			List <Empleado> empleadoRol = empleadosOrdenados.get(rol);
			buscarEmpleadoNuevo(cant, empleadoRol);	
		}
	}

	private void buscarEmpleadoNuevo(int cant, List<Empleado> empleadoRol) throws EquipoImposibleException {
		int indice = 0;
		while (cant > 0) {
			try {
				Empleado nuevo = empleadoRol.get(indice);
				if (!(mejorEquipo.tieneIncompatibilidadesConElEquipo(nuevo))) {
					mejorEquipo.agregarEmpleado(nuevo);
					empleadoRol.remove(indice);
					cant --;
				} else {
					indice ++;		
				}
			} catch (Exception e) {
				throw new EquipoImposibleException();
			}
		}
	}	

	private void sortEmpleadosTodosLosRoles() {
		Rol [] roles = Rol.values();
		for (Rol rol: roles)
			empleadosOrdenados.put(rol.toString(), sortEmpleados(rol));
	}

	private List <Empleado> sortEmpleados(Rol rol) {
		List <Empleado> empleados = empresa.getEmpleadosDeRol(rol);	
		reordenarPorIncompatibilidad(empleados);
		reordenarPorCalificacion(empleados);
		return empleados;
	}

	private void reordenarPorIncompatibilidad(List<Empleado> empleados) {
		Collections.sort(empleados, Empleado.sortPorMenorIncompatibilidad(empresa));
	}

	private void reordenarPorCalificacion(List<Empleado> empleados) {
		Collections.sort(empleados, Empleado.sortPorMayorCalificacion());
		
		imprimirEmpleadosOrdenados(empleados);
	}

	
	// Método de impresión de prueba,
	private void imprimirEmpleadosOrdenados(List<Empleado> empleados) {
		Map<Empleado, Set<Empleado>> incompatibles = 
			empresa.getRelaciones().getEmpleadosConIncompatibilidades();
		int cant;	
		System.out.println("Rol:" + empleados.get(0).getRol());
		for (Empleado e: empleados) {
			if (incompatibles.get(e) != null) {
				cant = incompatibles.get(e).size();
			} else {
				cant = 0;
			}
			System.out.println("calificación " + e.getCalificacion() + " - incomp: " + cant);	
		}
	}	
	
	public Equipo getMejorEquipo() {
		return mejorEquipo;
	}
}
