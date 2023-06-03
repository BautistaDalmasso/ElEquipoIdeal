package negocio;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArchivosGuardados {

	public static String[] cargarNombres(String directorio) {
		Set<String> nombres = Stream.of(new File(directorio)
				.listFiles())
				.filter(file -> !file.isDirectory())
				.map(File::getName).collect(Collectors.toSet());

		return nombres.toArray(new String[nombres.size()]);
	}
}
