package negocio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Relaciones {
	private Map<Empleado, Set<Empleado>> empleadosConIncompatibilidades;
	
	public Relaciones() {
		this.empleadosConIncompatibilidades = new HashMap<Empleado, Set<Empleado>>();
	}
	
	public void agregarIncompatibilidad(Empleado empleado1, Empleado empleado2) {
		Empresa.prevenirEmpleadoNulo(empleado1); Empresa.prevenirEmpleadoNulo(empleado2);
		prevenirLoops(empleado1, empleado2);
		
		incompatibilizar(empleado1, empleado2);
		incompatibilizar(empleado2, empleado1);
	}
	
	private void prevenirLoops(Empleado empleado1, Empleado empleado2) {
		if (empleado1.equals(empleado2)) {
			throw new IllegalArgumentException("Una persona no puede ser incompatible con sigo misma: " + empleado1);
		}
	}

	private void incompatibilizar(Empleado empleado, Empleado incompatible) {
		inicializar(empleado);
		Set<Empleado> empleadosIncompatibles = this.empleadosConIncompatibilidades.get(empleado);
		
		empleadosIncompatibles.add(incompatible);
	}

	private void inicializar(Empleado empleado) {		
		if (! this.empleadosConIncompatibilidades.containsKey(empleado)) {
			this.empleadosConIncompatibilidades.put(empleado, new HashSet<Empleado>());
		}
	}
	
	public boolean sonIncompatibles(Empleado empleado1, Empleado empleado2) {
		Empresa.prevenirEmpleadoNulo(empleado1); Empresa.prevenirEmpleadoNulo(empleado2);
		inicializar(empleado1); inicializar(empleado2);

		return empleadosConIncompatibilidades.get(empleado1).contains(empleado2);
	}
	
	public int getCantidadDeIncompatibilidades(Empleado empleado) {
		Set<Empleado> incompatibles = this.empleadosConIncompatibilidades.get(empleado);
		
		return incompatibles == null ? 0 : incompatibles.size();
	}
}
