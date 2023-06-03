package negocio;

import java.io.IOException;

public class EmpresasGuardadas {
	private static String DIRECTORIO_EMPRESAS = "./Empresas";

	public static String[] cargarNombresEmpresas() {
		return ArchivosGuardados.cargarNombres(DIRECTORIO_EMPRESAS);
	}

	public static Empresa cargarEmpresa(String nombre) throws IOException {
		return Empresa.cargarEmpresaDesdeArchivo(DIRECTORIO_EMPRESAS + "/" + nombre);
	}

	public static void guardarEmpresa(Empresa empresa, String nombre) {
		(new ArchivoEmpresa(empresa)).guardarEmpresaConSobreescritura(DIRECTORIO_EMPRESAS + "/" + nombre);
	}
}
