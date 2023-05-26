package interfaz;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class TarjetaArchivo extends Tarjeta {

	private static final long serialVersionUID = -1031556552960918132L;
	private static final String NOMBRE = "Manejar Archivos";

	public TarjetaArchivo(ElEquipoIdeal padre) {
		super(padre);
		
		setLayout(null);
		
		crearLabelExplicativa();
		
		crearZonaCargarGuardada();
		
		crearZonaGuardarActual();
	}

	private void crearLabelExplicativa() {
		JLabel lblManejoDeArchivos = new JLabel("Manejar Archivos");
		lblManejoDeArchivos.setHorizontalAlignment(SwingConstants.CENTER);
		lblManejoDeArchivos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblManejoDeArchivos.setBounds(134, 24, 162, 22);
		add(lblManejoDeArchivos);
	}

	private void crearZonaCargarGuardada() {
		JLabel lblEmpresasGuardadas = new JLabel("Empresas Guardadas:");
		lblEmpresasGuardadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmpresasGuardadas.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmpresasGuardadas.setBounds(10, 106, 128, 29);
		add(lblEmpresasGuardadas);
		
		crearComboBoxGuardadas();
		crearBotonRefrescar();
		crearBotonCargar();
	}

	private void crearComboBoxGuardadas() {
		JComboBox<String> cbEmpresasGuardadas = new JComboBox<String>();
		cbEmpresasGuardadas.setBounds(134, 110, 162, 22);
		add(cbEmpresasGuardadas);
	}

	private void crearBotonRefrescar() {
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setBounds(310, 110, 89, 23);
		add(btnRefrescar);
	}
	
	private void crearBotonCargar() {
		JButton btnCargarEmpresaSeleccionada = new JButton("Cargar Seleccionada");
		btnCargarEmpresaSeleccionada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCargarEmpresaSeleccionada.setBounds(124, 155, 185, 29);
		add(btnCargarEmpresaSeleccionada);
	}

	private void crearZonaGuardarActual() {
		JButton btnGuardarEmpresaActual = new JButton("Guardar Empresa Actual");
		btnGuardarEmpresaActual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGuardarEmpresaActual.setBounds(124, 229, 185, 29);
		add(btnGuardarEmpresaActual);
	}
	
	@Override
	public String getNombre() {
		return NOMBRE;
	}
}
