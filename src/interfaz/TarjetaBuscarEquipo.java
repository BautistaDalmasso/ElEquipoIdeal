package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import negocio.EstadisticasDeBusqueda;

import java.awt.Font;

public class TarjetaBuscarEquipo extends Tarjeta {
	/*
	 * TODO:
	 * -> mostrar el equipo encontrado de mejor manera
	 */
	
	
	private static final long serialVersionUID = 5084902088676552823L;
	private static final String NOMBRE = "Buscar Equipo";
	
	private JTextField tfResultado;
	private JTextField tfCasosConsiderados;
	private JTextField tfCasosDescartados;
	private JTextField tfEstadoBusqueda;
	private JButton btnBuscar;
	private JButton btnDetenerBusqueda;
	private JButton btnVisualizarEncontrado;
	
	private Boolean algunoEncontrado;

	/**
	 * Create the panel.
	 */
	public TarjetaBuscarEquipo(ElEquipoIdeal padre) {
		super(padre);
		
		crearLabelExplicativa();
		crearZonaEstadisticas();
		crearBotones();
	}

	private void crearLabelExplicativa() {
		JLabel lblBuscarEquipo = new JLabel("Buscar Equipo");
		lblBuscarEquipo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBuscarEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscarEquipo.setBounds(166, 5, 123, 23);
		add(lblBuscarEquipo);
	}

	private void crearZonaEstadisticas() {
		crearZonaMejorCalificacionTotal();
		crearZonaCasosConsiderados();
		crearZonaCasosDescartados();
		crearZonaEstadoDeBusqueda();
	}

	private void crearZonaMejorCalificacionTotal() {
		crearLabelEstadisticas("Mejor Calificacion Total Encontrada:", 45);
		
		tfResultado = new JTextField();
		inicializarTextFieldEstadisticas(tfResultado, 49);
	}

	private void crearZonaCasosConsiderados() {
		crearLabelEstadisticas("Casos Considerados:", 80);
		
		tfCasosConsiderados = new JTextField();
		inicializarTextFieldEstadisticas(tfCasosConsiderados, 84);
	}

	private void crearZonaCasosDescartados() {
		crearLabelEstadisticas("Casos Descartados:", 115);
		
		tfCasosDescartados = new JTextField();
		inicializarTextFieldEstadisticas(tfCasosDescartados, 119);
	}
	
	private void crearZonaEstadoDeBusqueda() {
		crearLabelEstadisticas("Estado de la Busqueda:", 150);
		
		tfEstadoBusqueda = new JTextField();
		inicializarTextFieldEstadisticas(tfEstadoBusqueda, 154);
	}

	private void crearLabelEstadisticas(String texto, int posicionY) {
		JLabel lblEstadistica = new JLabel(texto);
		lblEstadistica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstadistica.setBounds(1, posicionY, 179, 29);
		add(lblEstadistica);
	}
	
	private void inicializarTextFieldEstadisticas(JTextField textField, int posicionY) {
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(185, posicionY, 225, 20);
		add(textField);
	}
	
	private void crearBotones() {
		crearBotonBuscar();
		
		crearBotonDetenerBusqueda();
		
		crearBotonVisualizarEncontrado();
	}

	private void crearBotonBuscar() {
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(94, 190, 123, 23);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getPadre().getPresenter().resolverInstancia();
			}
		});
		add(btnBuscar);
	}

	private void crearBotonDetenerBusqueda() {
		btnDetenerBusqueda = new JButton("Detener Busqueda");
		btnDetenerBusqueda.setBounds(234, 190, 123, 23);
		btnDetenerBusqueda.setEnabled(false);
		btnDetenerBusqueda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getPadre().getPresenter().detenerBusqueda();
			}
		});
		add(btnDetenerBusqueda);
	}

	private void crearBotonVisualizarEncontrado() {
		btnVisualizarEncontrado = new JButton("Visualizar Equipo");
		btnVisualizarEncontrado.setBounds(170, 224, 123, 23);
		btnVisualizarEncontrado.setEnabled(false);
		btnVisualizarEncontrado.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getPadre().visualizarEquipo();
			}
		});
		add(btnVisualizarEncontrado);
	}
	
	public void busquedaIniciada() {
		this.algunoEncontrado = false;
		this.btnBuscar.setEnabled(false);
		this.btnDetenerBusqueda.setEnabled(true);
		this.btnVisualizarEncontrado.setEnabled(false);
	}
	
	public void busquedaTerminada() {
		this.btnBuscar.setEnabled(true);
		this.btnDetenerBusqueda.setEnabled(false);
		this.btnVisualizarEncontrado.setEnabled(this.algunoEncontrado);
	}
	
	public void nuevoEncontrado(int valor) {
		tfResultado.setText(Integer.valueOf(valor).toString());
		this.algunoEncontrado = true;
	}
	
	public void actualizarEstadisticas(EstadisticasDeBusqueda estadisticas) {
		this.tfCasosConsiderados.setText(Integer.valueOf(estadisticas.getCasosConsiderados()).toString());
		this.tfCasosDescartados.setText(Integer.valueOf(estadisticas.getCasosDescartados()).toString());
	}
	
	public void actualizarEstado(String estado) {
		this.tfEstadoBusqueda.setText(estado);
	}
	
	@Override
	public String getNombre() {
		return NOMBRE;
	}
}
