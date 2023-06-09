package interfaz;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CancellationException;

import negocio.ArchivosGuardados;
import negocio.Empleado;
import negocio.Empresa;
import negocio.EmpresasGuardadas;
import negocio.Requerimientos;
import negocio.Rol;

public class Presenter {
	private static final String DIRECTORIO_FOTOS = "./FotosEmpleados";
	
	private Empresa empresa;
	private Requerimientos requerimientos;
	private ElEquipoIdeal view;
	private ObserverInterfaz observer;
	
	public Presenter(ElEquipoIdeal view) {
		this.view = view;
		
		this.empresa = new Empresa();
		this.requerimientos = new Requerimientos(empresa);
	}
	
	public void agregarEmpleado(String nombre, String rol, int calificacion, String nombreFoto) {
		Empleado nuevoEmpleado = new Empleado(nombre, Rol.fromString(rol), calificacion, nombreFoto);

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
			this.requerimientos = new Requerimientos(empresa);
			realizarActualizacionesEmpleadoAgregado();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void guardarEmpresa(String nombreEmpresa) {
		EmpresasGuardadas.guardarEmpresa(empresa, nombreEmpresa);
	}
	
	private void realizarActualizacionesEmpleadoAgregado() {
		this.view.getCartaAgregarIncompatibilidades().agregarComboBoxModels();
		this.view.getCartaVisualizarEmpresa().refrescarCarrouselEmpleados(empresa.getArregloEmpleados());
	}

	public String[] cargarFotosPosibles() {
		return ArchivosGuardados.cargarNombres(DIRECTORIO_FOTOS);
	}
	
	public void mostrarIncompatibilidades(Empleado empleado) {
		Set<Empleado> empleadosIncompatibles = empresa.getIncompatibles(empleado);
		
		VentanaIncompatibilidades.mostrarIncompatibilidades(empleado.getNombre(), empleadosIncompatibles);
	}
}
