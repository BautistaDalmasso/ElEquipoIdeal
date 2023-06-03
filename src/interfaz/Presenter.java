package interfaz;

import java.io.IOException;
import java.util.concurrent.CancellationException;

import negocio.Empleado;
import negocio.Empresa;
import negocio.EmpresasGuardadas;
import negocio.Requerimientos;
import negocio.Rol;

public class Presenter {
	private Empresa empresa;
	private Requerimientos requerimientos;
	private ElEquipoIdeal view;
	private ObserverInterfaz observer;
	
	public Presenter(ElEquipoIdeal view) {
		this.view = view;
		
		this.empresa = new Empresa();
	}
	
	public void agregarEmpleado(String nombre, String rol, int calificacion) {
		Empleado nuevoEmpleado = new Empleado(nombre, Rol.fromString(rol), calificacion);

		this.empresa.agregarEmpleado(nuevoEmpleado);

		realizarActualizacionesEmpleadoAgregado();
	}
	
	public String[] crearArregloConNombres() {
		return this.empresa.crearArregloConNombres();
	}

	public void incompatibilizar(String empleado1, String empleado2) {
		empresa.agregarIncompatibilidad(empresa.buscarEmpleadoPorNombre(empleado1), empresa.buscarEmpleadoPorNombre(empleado2));
	}

	public void crearRequerimientos(int[] valoresDeRequerimientos) {
		this.requerimientos = new Requerimientos(empresa);
		
		int i = 0;
		for (Rol rol : Rol.values()) {
			this.requerimientos.setRequerimientosParaRol(rol, valoresDeRequerimientos[i++]);
		}
	}
	
	public void resolverInstancia() {
		observer = new ObserverInterfaz(view, empresa, requerimientos);
		observer.execute();
	}

	public void detenerBusqueda() {
		try {
			observer.cancel(true);
		} catch (CancellationException e) {
		}
	}
	
	public String[] getNombresEmpresasGuardadas() {
		return EmpresasGuardadas.cargarNombresEmpresas();
	}

	public void cargarEmpresa(String nombreEmpresa) {
		try {
			this.empresa = EmpresasGuardadas.cargarEmpresa(nombreEmpresa);
			this.requerimientos = null;
			realizarActualizacionesEmpleadoAgregado();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void guardarEmpresa(String nombreEmpresa) {
		EmpresasGuardadas.guardarEmpresa(empresa, nombreEmpresa);
	}
	
	private void realizarActualizacionesEmpleadoAgregado() {
		this.view.getTarjetaAgregarIncompatibilidades().agregarComboBoxModels();
		this.view.getTarjetaVisualizarEmpresa().refrescarCarrouselEmpleados(empresa.getArregloEmpleados());
	}
}
