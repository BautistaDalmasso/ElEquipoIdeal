package negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ArchivoEmpresa {
	@SuppressWarnings("unused")
	private List<Empleado> empleados;
	@SuppressWarnings("unused")
	private Set<ParIncompatible> paresIncompatibles;
	
	public ArchivoEmpresa(Empresa empresa) {
		this.empleados = empresa.getEmpleados();
		this.paresIncompatibles = empresa.paresIncompatibles();
	}
	
	public void guardarEmpresaConCuidado(String nombreArchivo) {
		forzarArchivoInexistente(nombreArchivo);
		
		guardarEmpresaConSobreescritura(nombreArchivo);
	}

	public void guardarEmpresaConSobreescritura(String nombreArchivo) {
		FileWriter writer;
		try {
			writer = new FileWriter(nombreArchivo);
			writer.write(obtenerRepresentacionJson());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String obtenerRepresentacionJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public static ArchivoEmpresa obtenerArchivoEmpresa(String nombreArchivo) throws IOException {
		forzarArchivoExistente(nombreArchivo);
		
		Gson gson = new Gson();
		FileReader fr = new FileReader(nombreArchivo);
		BufferedReader br = new BufferedReader(fr);
		ArchivoEmpresa archivoEmpresa = gson.fromJson(br, ArchivoEmpresa.class);
		br.close();
		fr.close();
		
		return archivoEmpresa;
	}
	
	private static void forzarArchivoExistente(String nombreArchivo) throws FileNotFoundException {
		File archivo = new File(nombreArchivo);
		if (!archivo.exists()) {
			throw new FileNotFoundException("El archivo: " + nombreArchivo + " no existe.");
		}
	}

	private static void forzarArchivoInexistente(String nombreArchivo) {
		File archivo = new File(nombreArchivo);
		
		if (archivo.exists()) {
			throw new IllegalArgumentException("El archivo: " + nombreArchivo + " ya existe.");
		}
	}

	public List<Empleado> getEmpleados() {
		return empleados;
	}
	
	public Set<ParIncompatible> getParesIncompatibles() {
		return paresIncompatibles;
	}
}
