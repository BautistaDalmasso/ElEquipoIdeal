package interfaz;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class TarjetaArchivo extends Tarjeta {

	private static final long serialVersionUID = -1031556552960918132L;
	private static final String NOMBRE = "Manejar Archivos";
	private JComboBox<String> cbEmpresasGuardadas;
	private JTextField tfEmpresaActual;

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
		lblEmpresasGuardadas.setBounds(10, 85, 128, 29);
		add(lblEmpresasGuardadas);
		
		crearComboBoxGuardadas();
		crearBotonRefrescar();
		crearBotonCargar();
	}

	private void crearComboBoxGuardadas() {
		cbEmpresasGuardadas = new JComboBox<String>();
		cbEmpresasGuardadas.setBounds(134, 89, 162, 22);
		add(cbEmpresasGuardadas);
		
		cargarNombresEmpresasGuardadas();
	}

	private void crearBotonRefrescar() {
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setBounds(306, 89, 89, 23);
		add(btnRefrescar);
		
		btnRefrescar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarNombresEmpresasGuardadas();
			}});
	}
	
	private void cargarNombresEmpresasGuardadas() {
		cbEmpresasGuardadas.setModel(
				new DefaultComboBoxModel<String>(getPadre().getPresenter().getNombresEmpresasGuardadas())
		);
	}
	
	private void crearBotonCargar() {
		JButton btnCargarEmpresaSeleccionada = new JButton("Cargar Seleccionada");
		btnCargarEmpresaSeleccionada.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnCargarEmpresaSeleccionada.setBounds(124, 125, 185, 29);
		add(btnCargarEmpresaSeleccionada);
		
		btnCargarEmpresaSeleccionada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarEmpresaSeleccionada();
			}});
	}

	private void cargarEmpresaSeleccionada() {
		String seleccionada = (String) cbEmpresasGuardadas.getSelectedItem();
		
		getPadre().getPresenter().cargarEmpresa(seleccionada);
	}
	
	private void crearZonaGuardarActual() {
		crearLabelEmpresaActual();
		crearTexfieldEmpresaActual();
		crearBotonGuardarActual();
	}

	private void crearLabelEmpresaActual() {
		JLabel lblNombreActual = new JLabel("Empresa Actual:");
		lblNombreActual.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombreActual.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNombreActual.setBounds(10, 187, 114, 29);
		add(lblNombreActual);
	}

	private void crearTexfieldEmpresaActual() {
		tfEmpresaActual = new JTextField();
		tfEmpresaActual.setHorizontalAlignment(SwingConstants.CENTER);
		tfEmpresaActual.setText("(nombre)");
		tfEmpresaActual.setBounds(134, 192, 162, 20);
		add(tfEmpresaActual);
		tfEmpresaActual.setColumns(10);
	}

	private void crearBotonGuardarActual() {
		JButton btnGuardarEmpresaActual = new JButton("Guardar Empresa Actual");
		btnGuardarEmpresaActual.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGuardarEmpresaActual.setBounds(124, 229, 185, 29);
		
		btnGuardarEmpresaActual.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guardarEmpresa();
			}
		});
		
		add(btnGuardarEmpresaActual);
	}
	
	private void guardarEmpresa() {
		getPadre().getPresenter().guardarEmpresa(tfEmpresaActual.getText());
	}

	@Override
	public String getNombre() {
		return NOMBRE;
	}
}
