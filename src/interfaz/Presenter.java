package interfaz;

import negocio.Empleado;
import negocio.Empresa;
import negocio.Rol;

public class Presenter {
	private Empresa empresa;
	@SuppressWarnings("unused")
	private ElEquipoIdeal view;
	
	public Presenter(ElEquipoIdeal view) {
		this.view = view;
		
		this.empresa = new Empresa();
	}
	
	public void agregarEmpleado(String nombre, String rol, int calificacion) {
		Empleado nuevoEmpleado = new Empleado(nombre, Rol.fromString(rol), calificacion);
		
		this.empresa.agregarEmpleado(nuevoEmpleado);
	}
}
