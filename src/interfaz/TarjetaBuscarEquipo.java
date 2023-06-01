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
	 * TODO: esto esta feisimo
	 * -> mostrar el equipo encontrado de mejor manera
	 * -> evitar que el usuario cambie de ventana mientras se busca
	 * -> permitir que el usuario corte la busqueda antes de tiempo
	 */
	
	
	private static final long serialVersionUID = 5084902088676552823L;
	private static final String NOMBRE = "Buscar Equipo";
	
	private JTextField tfResultado;
	private JTextField tfCasosConsiderados;
	private JTextField tfCasosDescartados;
	private JTextField tfEstadoBusqueda;

	/**
	 * Create the panel.
	 */
	public TarjetaBuscarEquipo(ElEquipoIdeal padre) {
		super(padre);
		setLayout(null);
		
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
		JButton btnBuscar = new JButton("Buscar");
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
		JButton btnDetenerBusqueda = new JButton("Detener Busqueda");
		btnDetenerBusqueda.setBounds(234, 190, 123, 23);
		add(btnDetenerBusqueda);
	}

	private void crearBotonVisualizarEncontrado() {
		JButton btnVisualizarEncontrado = new JButton("Visualizar Equipo");
		btnVisualizarEncontrado.setBounds(170, 224, 123, 23);
		add(btnVisualizarEncontrado);
	}
	
	public void nuevoEncontrado(int valor) {
		tfResultado.setText(Integer.valueOf(valor).toString());
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
