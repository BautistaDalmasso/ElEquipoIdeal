package test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import negocio.Empleado;
import negocio.Empresa;
import negocio.EquipoImposibleException;
import negocio.Requerimientos;
import negocio.Rol;
import negocio.Solver;

public abstract class SolverGenericoTest {
	/*
	 * Contiene tests que deben cumplirse para cualquier Solver de nuestro problema
	 * (Tanto Solvers exactos de fuerza bruta como solvers heuristicos)
	 * Tambien contiene metodos de creacion de problemas genericos.
	 */
	static final String ARCHIVO_EMPRESA_COMPLEJA = "./src/test/EmpresaCompleja";
	static final String ARCHIVO_EMPRESA_CON_INCOMPATIBILIDADES = "./src/test/EmpresaConIncompatibilidades";
	Empresa empresaActual;
	Requerimientos requerimientosActuales;
	Set<Empleado> empleadosEsperados;
	
	@Test( expected = EquipoImposibleException.class )
	public void equipoImposibleTest() throws EquipoImposibleException, IOException {
		// Un equipo imposible sera imposible sin importar que Solver se este usando.
		setEmpresaConIncompatibilidades();
		setRequerimientosImposibles();
		
		Solver solver = obtenerSolver();
		
		solver.resolver();
	}
	
	void setEmpresaConIncompatibilidades() throws IOException {
		empresaActual = Empresa.cargarEmpresaDesdeArchivo(ARCHIVO_EMPRESA_CON_INCOMPATIBILIDADES);

		crearEmpleadosEsperados(new String[] {"lider", "arquitecto c", "programador", "tester"});
	}
	
	void setRequerimientosImposibles() {
		crearRequerimientosActuales(new int[] {1, 2, 1, 1});
	}
	
	void crearEmpleadosEsperados(String[] empleados) {
		empleadosEsperados = new HashSet<Empleado>();
		
		for (String nombreEmpleado : empleados) {
			empleadosEsperados.add(empresaActual.buscarEmpleadoPorNombre(nombreEmpleado));
		}
	}
	
	void crearRequerimientosActuales(int[] requerimientos) {
		requerimientosActuales = new Requerimientos(empresaActual);
		
		int i = 0;
		for (Rol rol : Rol.values()) {
			requerimientosActuales.setRequerimientosParaRol(rol, requerimientos[i++]);
		}
	}
	
	void setEmpresaSimple() {
		empresaActual = new Empresa();
		empleadosEsperados = new HashSet<Empleado>();
		
		Empleado nuevoEmpleado;
		int i = 0;
		for (Rol rol : Rol.values()) {
			nuevoEmpleado = new Empleado("Empleado " + i++, rol, 5);
			empresaActual.agregarEmpleado(nuevoEmpleado);
			empleadosEsperados.add(nuevoEmpleado);
		}
	}
	
	void setRequerimientosSimples() {
		crearRequerimientosActuales(new int[] {1, 1, 1, 1});
	}
	
	void setEmpresaCompleja() throws IOException {
		empresaActual = Empresa.cargarEmpresaDesdeArchivo(ARCHIVO_EMPRESA_COMPLEJA);
		empresaActual.getEmpleados();
		
		crearEmpleadosEsperados(new String[] {"lid2", "arq2", "arq3", "arq4", "prog1", "prog2", "tester1"});
		
		crearRequerimientosActuales(new int[] {1, 3, 2, 1});
	}
	
	abstract Solver obtenerSolver();
}
