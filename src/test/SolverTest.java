package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import negocio.Empleado;
import negocio.Empresa;
import negocio.Requerimientos;
import negocio.SolverPorRoles;
import negocio.Empresa.Rol;
import negocio.Equipo;
import negocio.EquipoImposibleException;

public class SolverTest {
	private static final String ARCHIVO_EMPRESA_COMPLEJA = "./src/test/EmpresaCompleja";
	private static final String ARCHIVO_EMPRESA_CON_INCOMPATIBILIDADES = "./src/test/EmpresaConIncompatibilidades";
	private static Empresa empresaActual;
	private static Requerimientos requerimientosActuales;
	private static Set<Empleado> empleadosEsperados;
	
	@Test( expected = EquipoImposibleException.class )
	public void equipoImposibleTest() throws EquipoImposibleException, IOException {
		setEmpresaConIncompatibilidades();
		setRequerimientosImposibles();
		
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		solver.resolver();
	}

	@Test
	public void mejorEquipoSinIncompatibilidadesTest() {
		setEmpresaSimple();
		setRequerimientosSimples();
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		try {
			assertEquals(empleadosEsperados, solver.resolver().getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void mejorEquipoConAlgunasIncompatibilidadesTest() throws IOException {
		setEmpresaConIncompatibilidades();
		setRequerimientosSimples();
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		try {
			assertEquals(empleadosEsperados, solver.resolver().getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mejorEquipoEmpresaComplejaTest() throws IOException {
		setEmpresaCompleja();
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		try {
			Equipo resultado = solver.resolver();
			assertEquals(empleadosEsperados, resultado.getEmpleados());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void mejorEquipoConRequerimientoEn0Test() throws IOException {
		setEmpresaCompleja();
		requerimientosActuales.setRequerimientosParaRol(Rol.ARQUITECTO, 0);
		SolverPorRoles solver = new SolverPorRoles(empresaActual, requerimientosActuales);
		
		try {
			Equipo resultado = solver.resolver();
			assertEquals(20, resultado.getCalificacionTotal());
		} catch (EquipoImposibleException e) {
			fail(e.getMessage());
		}
	}
	
	private void setEmpresaConIncompatibilidades() throws IOException {
		empresaActual = Empresa.cargarEmpresaDesdeArchivo(ARCHIVO_EMPRESA_CON_INCOMPATIBILIDADES);

		crearEmpleadosEsperados(new String[] {"lider", "arquitecto c", "programador", "tester"});
	}
	
	private static void setEmpresaSimple() {
		empresaActual = new Empresa();
		empleadosEsperados = new HashSet<Empleado>();
		
		Empleado nuevoEmpleado;
		for (Rol rol : Rol.values()) {
			nuevoEmpleado = new Empleado("Raul", rol, 5);
			empresaActual.agregarEmpleado(new Empleado("Raul", rol, 5));
			empleadosEsperados.add(nuevoEmpleado);
		}
	}
	
	private static void setRequerimientosSimples() {
		crearRequerimientosActuales(new int[] {1, 1, 1, 1});
	}

	private void setRequerimientosImposibles() {
		crearRequerimientosActuales(new int[] {1, 2, 1, 1});
	}
	
	private void setEmpresaCompleja() throws IOException {
		empresaActual = Empresa.cargarEmpresaDesdeArchivo(ARCHIVO_EMPRESA_COMPLEJA);
		empresaActual.getEmpleados();
		
		crearEmpleadosEsperados(new String[] {"lid2", "arq2", "arq3", "arq4", "prog1", "prog2", "tester1"});
		
		crearRequerimientosActuales(new int[] {1, 3, 2, 1});
	}
	
	private static void crearEmpleadosEsperados(String[] empleados) {
		empleadosEsperados = new HashSet<Empleado>();
		
		for (String nombreEmpleado : empleados) {
			empleadosEsperados.add(empresaActual.buscarEmpleadoPorNombre(nombreEmpleado));
		}
	}
	
	private static void crearRequerimientosActuales(int[] requerimientos) {
		requerimientosActuales = new Requerimientos(empresaActual);
		
		int i = 0;
		for (Rol rol : Rol.values()) {
			requerimientosActuales.setRequerimientosParaRol(rol, requerimientos[i++]);
		}
	}
}
