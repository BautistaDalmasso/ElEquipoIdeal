package negocio;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmpresasGuardadas {
	private static String DIRECTORIO_EMPRESAS = "./Empresas";
	
	public static String[] cargarNombresEmpresas() {
		Set<String> empresas = Stream.of(new File(DIRECTORIO_EMPRESAS).listFiles())
								.filter(file -> !file.isDirectory())
								.map(File::getName)
								.collect(Collectors.toSet());
		
		return empresas.toArray(new String[empresas.size()]);
	}
}
