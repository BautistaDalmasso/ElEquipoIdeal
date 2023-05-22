package interfaz;

import negocio.Empleado;
import negocio.Empresa;
import negocio.Rol;

public class Presenter {
	private Empresa empresa;
	private ElEquipoIdeal view;
	
	public Presenter(ElEquipoIdeal view) {
		this.view = view;
		
		this.empresa = new Empresa();
	}
	
	public void agregarEmpleado(String nombre, String rol, int calificacion) {
		Empleado nuevoEmpleado = new Empleado(nombre, Rol.fromString(rol), calificacion);

		this.empresa.agregarEmpleado(nuevoEmpleado);

		realizarActualizacionesEmpleadoAgregado();
	}
	
	private void realizarActualizacionesEmpleadoAgregado() {
		this.view.getTarjetaAgregarIncompatibilidades().agregarComboBoxModels();
	}
	
	public String[] crearArregloConNombres() {
		return this.empresa.crearArregloConNombres();
	}

	public void incompatibilizar(String empleado1, String empleado2) {
		empresa.agregarIncompatibilidad(empresa.buscarEmpleadoPorNombre(empleado1), empresa.buscarEmpleadoPorNombre(empleado2));
	}
}
