package negocio;

import java.util.List;

import negocio.Empresa.Rol;

public class Solver {
	private Empresa empresa;
	private Requerimientos requerimientos;
	private Equipo mejorEquipo;
	private Equipo actual;
	
	public Solver(Empresa empresa, Requerimientos requerimientos) {
		this.empresa = empresa;
		this.requerimientos = requerimientos;
	}
	
	public Equipo resolver() throws EquipoImposibleException {
		this.actual = new Equipo(empresa);
		this.mejorEquipo = this.actual.copiar();
		
		agregarEmpleadosPorRolDesde(Rol.values()[0], 0);
		
		if (!requerimientos.equipoCumpleConLosRequerimientos(mejorEquipo)) {
			throw new EquipoImposibleException();
		}
		
		return this.mejorEquipo;
	}

	private void agregarEmpleadosPorRolDesde(Rol rol, int desde) {		
		if (rol == null) {
			if (actual.getCalificacionTotal() > mejorEquipo.getCalificacionTotal()) {
				mejorEquipo = actual.copiar();
			}
			return;
		}

		List<Empleado> empleadosDelRol = empresa.getEmpleadosDeRol(rol);
		if (desde == empleadosDelRol.size()) {
			return;
		}
		
		Empleado empleadoSiendoEvaluado = obtenerEmpleadoDelRol(rol, desde);
		
		if (actual.tieneIncompatibilidadesConElEquipo(empleadoSiendoEvaluado)) {
			agregarEmpleadosPorRolDesde(rol, desde+1);
			return;
		}
		actual.agregarEmpleado(empleadoSiendoEvaluado);
		if (actual.getEmpleadosPorRol(rol) == requerimientos.getRequerimientosParaRol(rol)) {
			agregarEmpleadosPorRolDesde(obtenerSiguienteRol(rol), 0);
		} else {
			agregarEmpleadosPorRolDesde(rol, desde+1);
		}
		
		actual.removerEmpleado(empleadoSiendoEvaluado);
		agregarEmpleadosPorRolDesde(rol, desde+1);
	}

	private Empleado obtenerEmpleadoDelRol(Rol rol, int indice) {
		return empresa.getEmpleadosDeRol(rol).get(indice);
	}

	private Rol obtenerSiguienteRol(Rol rol) {
		Rol[] roles = Rol.values();
		int siguiente = rol.ordinal() + 1;
		
		return siguiente < roles.length ? roles[siguiente] : null;
	}
}
