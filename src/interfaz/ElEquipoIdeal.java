package interfaz;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import negocio.Equipo;
import negocio.EstadisticasDeBusqueda;

public class ElEquipoIdeal extends JFrame {

	private static final long serialVersionUID = 8211971627980187103L;

	private JPanel contentPane;
	private CardLayout cardLayout;
		
	private Presenter presenter;

	private JMenuBar barraMenus;
	private JMenu menuAcciones;

	private CartaAgregarEmpleados tarjetaAgregarEmpleados;
	private CartaAgregarIncompatibilidades tarjetaAgregarIncompatibilidades;
	private CartaCrearRequerimientos tarjetaCrearRequerimientos;
	private CartaVisualizarEmpresa tarjetaVisualizarEmpresa;
	private CartaBuscarEquipo tarjetaBuscarEquipo;
	private CartaArchivo tarjetaArchivo;

	private CartaVisualizarEquipo tarjetaVisualizarEquipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					ElEquipoIdeal frame = new ElEquipoIdeal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ElEquipoIdeal() {
		inicializarDimensiones();
		inicializarPresenter();

		crearMenuDesplegable();
		inicializarTarjetas();
		inicializarTarjetasOcultas();
	}

	private void inicializarDimensiones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 335);
		cardLayout = new CardLayout();
		contentPane = new JPanel(cardLayout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);
		
		setContentPane(contentPane);
	}

	private void inicializarPresenter() {
		this.presenter = new Presenter(this);
	}
	
	private void crearMenuDesplegable() {
		barraMenus = new JMenuBar();
		menuAcciones = new JMenu("Acciones");

		barraMenus.add(menuAcciones);
		this.setJMenuBar(barraMenus);
	}
	
	private void inicializarTarjetas() {
		tarjetaAgregarEmpleados = new CartaAgregarEmpleados(this);
		tarjetaAgregarIncompatibilidades = new CartaAgregarIncompatibilidades(this);
		tarjetaCrearRequerimientos = new CartaCrearRequerimientos(this);
		tarjetaVisualizarEmpresa = new CartaVisualizarEmpresa(this);
		tarjetaBuscarEquipo = new CartaBuscarEquipo(this);
		tarjetaArchivo = new CartaArchivo(this);
		
		agregarTarjetas(new Carta[] 
				{tarjetaAgregarEmpleados, tarjetaAgregarIncompatibilidades, tarjetaCrearRequerimientos, tarjetaVisualizarEmpresa, tarjetaBuscarEquipo, tarjetaArchivo});
	}

	private void agregarTarjetas(Carta[] tarjetas) {
		for (Carta tarjeta : tarjetas) {
			agregarTarjeta(tarjeta);
		}
	}
	
	private void agregarTarjeta(Carta tarjeta) {
		crearOpcionMenu(tarjeta);
		
		contentPane.add(tarjeta, tarjeta.getNombre());
	}
	
	private void crearOpcionMenu(Carta tarjeta) {
		JMenuItem itemTarjeta = new JMenuItem(tarjeta.getNombre());
		
		itemTarjeta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				cardLayout.show(contentPane, tarjeta.getNombre());
			}
		});
		
		menuAcciones.add(itemTarjeta);
	}

	private void inicializarTarjetasOcultas() {
		inicializarTarjetaVisualizarEquipo();
	}
	
	private void inicializarTarjetaVisualizarEquipo() {
		this.tarjetaVisualizarEquipo = new CartaVisualizarEquipo(this);
		contentPane.add(tarjetaVisualizarEquipo, tarjetaVisualizarEquipo.getNombre());
	}

	public void equipoEncontrado(Equipo equipo) {
		this.tarjetaBuscarEquipo.nuevoEncontrado(equipo.getCalificacionTotal());
		this.tarjetaVisualizarEquipo.cargarEquipo(equipo);
	}

	public void actualizarEstadisticas(EstadisticasDeBusqueda estadisticas) {
		this.tarjetaBuscarEquipo.actualizarEstadisticas(estadisticas);
	}

	public void actualizarEstado(String estado) {
		this.tarjetaBuscarEquipo.actualizarEstado(estado);
	}

	public void iniciarBusqueda() {
		deshabilitarAcciones();
		this.tarjetaBuscarEquipo.busquedaIniciada();
	}
	
	public void deshabilitarAcciones() {
		this.menuAcciones.setEnabled(false);
	}
	
	public void busquedaTerminada() {
		habilitarAcciones();
		this.tarjetaBuscarEquipo.busquedaTerminada();
	}
	
	public void habilitarAcciones() {
		this.menuAcciones.setEnabled(true);
	}
	
	public void visualizarEquipo() {
		cardLayout.show(contentPane, this.tarjetaVisualizarEquipo.getNombre());
	}
	
	public Presenter getPresenter() {
		return presenter;
	}
	
	public CartaAgregarIncompatibilidades getTarjetaAgregarIncompatibilidades() {
		return tarjetaAgregarIncompatibilidades;
	}

	public CartaVisualizarEmpresa getTarjetaVisualizarEmpresa() {
		return tarjetaVisualizarEmpresa;
	}
}
