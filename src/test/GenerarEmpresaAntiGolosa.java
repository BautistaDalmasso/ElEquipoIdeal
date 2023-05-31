package test;

import java.util.Random;

import negocio.ArchivoEmpresa;
import negocio.Empleado;
import negocio.Empresa;
import negocio.Rol;

public class GenerarEmpresaAntiGolosa {
	/*
	 * Clase de utilidad. 
	 * Genera una empresa grande donde el mejor resultado no es el obtenido por nuestro solver heuristico goloso.
	 * (Para requerimientos que necesiten mas de un empleado por rol)
	 */
	
	private static final int cantidadEmpleadosBuenaCalidadPorRol = 20;
	private static final int cantidadEmpleadosMalaCalidadPorRol = 30;
	
	private static Empleado[][] empleadosDeBuenaCalidadPorRol = new Empleado[Rol.values().length][cantidadEmpleadosBuenaCalidadPorRol];
	private static Empleado[][] empleadosDeMalaCalidadPorRol = new Empleado[Rol.values().length][cantidadEmpleadosMalaCalidadPorRol];

	// Los empleados elegidos son empleados de buena calidad que se llevan bien entre ellos y con todos los de mala calidad.
	// Sin embargo se llevan mal con el resto de empleados de buena calidad.
	private static Empleado[] empleadosElegidos = new Empleado[Rol.values().length];
	
	private static Empleado[] todosLosEmpleados = 
			new Empleado[(cantidadEmpleadosBuenaCalidadPorRol + cantidadEmpleadosMalaCalidadPorRol) * Rol.values().length];
	
	private static Empresa empresaGenerada = new Empresa();
	
	private static int agregados = 0;
	
	private static Random random = new Random();

	public static void main(String[] args) {
		poblarArreglosDeEmpleados();
	}

	private static void poblarArreglosDeEmpleados() {
		System.out.println("agregar");
		agregarEmpleadosDeBuenaCalidad();
		agregarEmpleadosDeMalaCalidad();
		
		seleccionarElegidos();
		System.out.println("Incompatibilizar");
		incompatibilizar();
		
		System.out.println("Guardar");
		ArchivoEmpresa a = new ArchivoEmpresa(empresaGenerada);
		a.guardarEmpresaConCuidado("Empresa AntiGolosa");
	}

	private static void agregarEmpleadosDeBuenaCalidad() {
		
		for (Rol rol : Rol.values() ) {
			agregarEmpleadosDeBuenaCalidadDeRol(rol);
		}
	}

	private static void agregarEmpleadosDeBuenaCalidadDeRol(Rol rol) {
		for (int i = 0; i < cantidadEmpleadosBuenaCalidadPorRol; i++) {
			Empleado nuevoEmpleado = new Empleado("C5_" + rol + "_" + i, rol, 5);
			empleadosDeBuenaCalidadPorRol[rol.ordinal()][i] = nuevoEmpleado;
			todosLosEmpleados[agregados++] = nuevoEmpleado;
			empresaGenerada.agregarEmpleado(nuevoEmpleado);
		}
	}
	
	private static void agregarEmpleadosDeMalaCalidad() {
		for (Rol rol : Rol.values()) {
			agregarEmpleadosDeMalaCalidadDeRol(rol);
		}
	}

	private static void agregarEmpleadosDeMalaCalidadDeRol(Rol rol) {
		for (int i = 0; i < cantidadEmpleadosMalaCalidadPorRol; i++) {
			Empleado nuevoEmpleado = new Empleado("C1_" + rol + "_" + i, rol, 1);
			empleadosDeMalaCalidadPorRol[rol.ordinal()][i] = nuevoEmpleado;
			todosLosEmpleados[agregados++] = nuevoEmpleado; 
			empresaGenerada.agregarEmpleado(nuevoEmpleado);
		}
	}
	
	private static void seleccionarElegidos() {
		for (Rol rol : Rol.values()) {
			empleadosElegidos[rol.ordinal()] = empleadosDeBuenaCalidadPorRol[rol.ordinal()][0];
		}
	}
	
	private static void incompatibilizar() {
		System.out.println("-> Elegidos");
		incompatibilizarElegidos();
		System.out.println("-> Buena calidad");
		incompatibilizarEmpleadosDeBuenaCalidad();
		//System.out.println("-> Random");
		//agregarIncompatibilidadesRandom();
	}

	private static void incompatibilizarElegidos() {
		for (Rol rol : Rol.values()) {
			Empleado elegido = empleadosElegidos[rol.ordinal()];
			for (Empleado empleado : empleadosDeBuenaCalidadPorRol[rol.ordinal()]) {
				if (empleado != elegido) {
					empresaGenerada.agregarIncompatibilidad(elegido, empleado);
				}
			}
		}
	}
	
	private static void incompatibilizarEmpleadosDeBuenaCalidad() {
		for (Rol rol : Rol.values()) {
			Empleado elegido = empleadosElegidos[rol.ordinal()];
			for (Empleado empleadoBueno : empleadosDeBuenaCalidadPorRol[rol.ordinal()]) {
				for (Empleado empleadoMalo : empleadosDeMalaCalidadPorRol[rol.ordinal()]) {
					if (empleadoBueno != elegido) {
						empresaGenerada.agregarIncompatibilidad(empleadoMalo, empleadoBueno);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static void agregarIncompatibilidadesRandom() {
		// Agrega algunas incompatibilidades aleatorias para que resolver el problema sea más interesante.
		Empleado empleadoI; Empleado empleadoJ;
		for (int i = 0; i < todosLosEmpleados.length; i++) {
			empleadoI = todosLosEmpleados[i];
			for (int j = i+1; j < todosLosEmpleados.length; j++) {
				empleadoJ = todosLosEmpleados[j];
				if (esElegido(empleadoI) || esElegido(empleadoJ)) {
					continue;
				}
				
				if (random.nextDouble() < 0.15) {
					empresaGenerada.agregarIncompatibilidad(empleadoI, empleadoJ);
				}
			}
		}
	}

	private static boolean esElegido(Empleado empleado) {
		return empleado == empleadosElegidos[empleado.getRol().ordinal()];
	}
}
