package test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import negocio.Empleado;
import negocio.Empresa;
import negocio.Rol;
import negocio.SolverHeuristicoGoloso;
import negocio.EquipoImposibleException;
import negocio.Solver;
import negocio.Requerimientos;
import negocio.SolverPorRoles;
import negocio.SolverPorListaCompleta;

public class StressTestSolver {
	static final Random r = new Random(0);
	static Empresa empresaGenerada;
	static Requerimientos requerimientosGenerados;
	static CreadorSolver creadorSolver;
	static int iteraciones;
	
	public static void main(String[] args) {
		inicializarStressTest();
		
		Solver solver;
		for (int i = 6; i < iteraciones; i++) {
			long inicio = System.currentTimeMillis();
			generarProblema(i);
			
			solver = creadorSolver.crearSolver(empresaGenerada, requerimientosGenerados);
			try {
				solver.resolver();
			} catch (EquipoImposibleException e) {
				System.out.println("EQUIPO IMPOSIBLE:");
			}
			
			long fin = System.currentTimeMillis();
			double tiempo = (fin - inicio) / 1000.0;
			
			System.out.println("n = " + i + ": " + tiempo + " seg. \n" + solver.estadisticas());
		}
	}

	private static void inicializarStressTest() {
		Scanner in = new Scanner(System.in);
		System.out.println("Cuantas iteraciones generar: ");
		iteraciones = in.nextInt();
		System.out.println("1. Solver Principal. 2. Solver Alternativo. 3. Solver Heuristico Goloso.");
		int solverParaUsar = in.nextInt();
		System.out.println(solverParaUsar);
		in.close();
		switch (solverParaUsar) {
		case 1:
			creadorSolver = new CreadorSolver() {
				@Override
				public Solver crearSolver(Empresa empresa, Requerimientos requerimientos) {
					return new SolverPorRoles(empresa, requerimientos);
				}
			};
			break;
		case 2:
			creadorSolver = new CreadorSolver() {
				@Override
				public Solver crearSolver(Empresa empresa, Requerimientos requerimientos) {
					return new SolverPorListaCompleta(empresa, requerimientos);
				}
			};
			break;
		default:
			creadorSolver = new CreadorSolver() {
				@Override
				public Solver crearSolver(Empresa empresa, Requerimientos requerimientos) {
					return new SolverHeuristicoGoloso(empresa, requerimientos);
				}
 			};
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

interface CreadorSolver {
	Solver crearSolver(Empresa empresa, Requerimientos requerimientos);
}
