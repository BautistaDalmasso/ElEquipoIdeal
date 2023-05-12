package negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static negocio.Persona.Rol;

public class Empresa {
	private List<Persona> empleados;
	private Map<Rol, List<Persona>> empleadosPorRol;

	public Empresa() {
		this.empleados = new ArrayList<Persona>();
		inicializarEmpleadosPorRol();
	}

	private void inicializarEmpleadosPorRol() {
		empleadosPorRol = new HashMap<Rol, List<Persona>>();

		for (Rol rol : Rol.values()) {
			empleadosPorRol.put(rol, new ArrayList<Persona>());
		}
	}

	public void agregarEmpleado(Persona empleado) {
		forzarEmpleadoValidoNoRepetido(empleado);

		this.empleados.add(empleado);
		empleadosPorRol.get(empleado.getRol()).add(empleado);
	}

	private void forzarEmpleadoValidoNoRepetido(Persona empleado) {
		forzarEmpleadoValido(empleado);

		if (empleados.contains(empleado)) {
			throw new IllegalArgumentException("El empleado: " + empleado + " ya se encuentra en la empresa.");
		}
	}

	private void forzarEmpleadoValido(Persona empleado) {
		if (empleado == null) {
			throw new IllegalArgumentException("El empleado agregado no puede ser null.");
		}
	}

	public List<Persona> getEmpleadosDeRol(Rol rol) {
		List<Persona> empleadosDelRol = empleadosPorRol.get(rol);

		return new ArrayList<Persona>(empleadosDelRol);
	}

	public boolean esEmpleado(Persona p) {
		return this.empleados.contains(p);
	}
}
