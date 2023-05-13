package negocio;

import negocio.Empresa.Rol;

public class Requerimientos {
	private int[][] empleadosPorRol;
	private Empresa empresa;

	public Requerimientos(Empresa empresa) {
		if (empresa == null) {
			throw new IllegalArgumentException("Empresa no puede ser null.");
		}
		
		this.empresa = empresa;
		inicializarEmpleadosPorRol();
	}

	private void inicializarEmpleadosPorRol() {
		Rol[] roles = Rol.values();
		this.empleadosPorRol = new int[roles.length][1];
	}

	public void setRequerimientosParaRol(Rol rol, int empleadosRequeridos) {
		prevenirEmpleadosRequeridosInvalidos(rol, empleadosRequeridos);
		this.empleadosPorRol[rol.ordinal()][0] = empleadosRequeridos;
	}

	private void prevenirEmpleadosRequeridosInvalidos(Rol rol, int empleadosRequeridos) {
		prevenirEmpleadosRequeridosNegativos(empleadosRequeridos);
		prevenirEmpleadosInsuficientesParaRol(rol, empleadosRequeridos);
	}

	private void prevenirEmpleadosRequeridosNegativos(int empleadosRequeridos) {
		if (empleadosRequeridos < 0) {
			throw new IllegalArgumentException("Los empleados requeridos deben ser un número positivo o cero, se recibió: " + empleadosRequeridos);
		}
	}

	private void prevenirEmpleadosInsuficientesParaRol(Rol rol, int empleadosRequeridos) {
		int empleadosDisponibles = empresa.getEmpleadosDeRol(rol).size();

		if (empleadosDisponibles < empleadosRequeridos) {
			throw new IllegalArgumentException("Empleados Insuficientes. Se solicitaron: " + empleadosRequeridos
					+ ", pero se dispone de: " + empleadosDisponibles);
		}
	}

	public int getRequerimientosParaRol(Rol rol) {
		return this.empleadosPorRol[rol.ordinal()][0];
	}
}
