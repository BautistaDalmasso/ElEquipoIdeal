package negocio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Relaciones {
	private Map<Persona, Set<Persona>> empleadosConIncompatibilidades;
	
	public Relaciones() {
		this.empleadosConIncompatibilidades = new HashMap<Persona, Set<Persona>>();
	}
	
	public void agregarIncompatibilidad(Persona empleado1, Persona empleado2) {
		Empresa.prevenirEmpleadoNulo(empleado1); Empresa.prevenirEmpleadoNulo(empleado2);
		prevenirLoops(empleado1, empleado2);
		
		incompatibilizar(empleado1, empleado2);
		incompatibilizar(empleado2, empleado1);
	}
	
	private void prevenirLoops(Persona empleado1, Persona empleado2) {
		if (empleado1.equals(empleado2)) {
			throw new IllegalArgumentException("Una persona no puede ser incompatible con sigo misma: " + empleado1);
		}
	}

	private void incompatibilizar(Persona empleado, Persona incompatible) {
		inicializar(empleado);
		Set<Persona> empleadosIncompatibles = this.empleadosConIncompatibilidades.get(empleado);
		
		empleadosIncompatibles.add(incompatible);
	}

	private void inicializar(Persona empleado) {		
		if (! this.empleadosConIncompatibilidades.containsKey(empleado)) {
			this.empleadosConIncompatibilidades.put(empleado, new HashSet<Persona>());
		}
	}
	
	public boolean sonIncompatibles(Persona empleado1, Persona empleado2) {
		Empresa.prevenirEmpleadoNulo(empleado1); Empresa.prevenirEmpleadoNulo(empleado2);
		inicializar(empleado1); inicializar(empleado2);

		return empleadosConIncompatibilidades.get(empleado1).contains(empleado2);
	}
}
