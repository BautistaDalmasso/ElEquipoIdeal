package test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

import negocio.Empleado;
import negocio.Empresa;
import negocio.Empresa.Rol;
import negocio.EquipoImposibleException;
import negocio.Requerimientos;
import negocio.Solver;

public class StressTestSolver {
	static final Random r = new Random(0);
	static Empresa empresaGenerada;
	static Requerimientos requerimientosGenerados;
	
	public static void main(String[] args) {		
		for (int i = 6; i < 100; i++) {			
			long inicio = System.currentTimeMillis();
			generarProblema(i);
			
			if (i == 51) {
				System.out.println();
			}
			Solver solver = new Solver(empresaGenerada, requerimientosGenerados);
			try {
				solver.resolver();
			} catch (EquipoImposibleException e) {
			}
			
			long fin = System.currentTimeMillis();
			double tiempo = (fin - inicio) / 1000.0;
			
			System.out.println("n = " + i + ": " + tiempo + " seg. \n" + solver.estadisticas());
		}
	}

	private static void generarProblema(int tamanio) {
		generarEmpresa(tamanio);
		generarRequerimientos(tamanio);
	}

	private static void generarEmpresa(int tamanio) {
		empresaGenerada = new Empresa();
		
		agregarEmpleados(tamanio);
		generarIncompatibilidades();
	}

	private static void agregarEmpleados(int tamanio) {
		Rol[] roles = Rol.values();
		String nombre;
		Rol rolElegido;
		int calificacion;
		
		Empleado nuevoEmpleado;
		for (int i = 0; i < tamanio; i++) {
			nombre = stringAleatorio();
			rolElegido = roles[r.nextInt(roles.length)];
			calificacion = r.nextInt(1, 6);
			nuevoEmpleado = new Empleado(nombre, rolElegido, calificacion);
			
			try {
				empresaGenerada.agregarEmpleado(nuevoEmpleado);
			}
			catch (IllegalArgumentException e) {}
			}
	}

	private static String stringAleatorio() {
		byte[] arregloCaracteres = new byte[8]; 
	    r.nextBytes(arregloCaracteres);
	    return new String(arregloCaracteres, Charset.forName("UTF-8"));
	}
	
	private static void generarIncompatibilidades() {
		List<Empleado> empleados = empresaGenerada.getEmpleados();
		Empleado a;
		Empleado b;
		for (int i = 0; i < empleados.size(); i++) {
			for (int j = i+1; i < empleados.size(); i++) {
				if (r.nextDouble() < 0.25) {
					a = empleados.get(i); b = empleados.get(j);
					if (a != b) {						
						empresaGenerada.agregarIncompatibilidad(a, b);
					}
				}
			}
		}
	}

	private static void generarRequerimientos(int tamanio) {
		requerimientosGenerados = new Requerimientos(empresaGenerada);
		
		for (Rol rol : Rol.values()) {
			try {				
				requerimientosGenerados.setRequerimientosParaRol(rol, r.nextInt(0, tamanio/2));
			}
			catch (IllegalArgumentException e) {}
		}
	}
}
