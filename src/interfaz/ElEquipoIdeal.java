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

	private CartaAgregarEmpleados cartaAgregarEmpleados;
	private CartaAgregarIncompatibilidades cartaAgregarIncompatibilidades;
	private CartaCrearRequerimientos cartaCrearRequerimientos;
	private CartaVisualizarEmpresa cartaVisualizarEmpresa;
	private CartaBuscarEquipo cartaBuscarEquipo;
	private CartaArchivo cartaArchivo;

	private CartaVisualizarEquipo cartaVisualizarEquipo;

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
		inicializarCartas();
		inicializarCartasOcultas();
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
	
	private void inicializarCartas() {
		cartaAgregarEmpleados = new CartaAgregarEmpleados(this);
		cartaAgregarIncompatibilidades = new CartaAgregarIncompatibilidades(this);
		cartaCrearRequerimientos = new CartaCrearRequerimientos(this);
		cartaVisualizarEmpresa = new CartaVisualizarEmpresa(this);
		cartaBuscarEquipo = new CartaBuscarEquipo(this);
		cartaArchivo = new CartaArchivo(this);
		
		agregarCartas(new Carta[] 
				{cartaAgregarEmpleados, cartaAgregarIncompatibilidades, cartaCrearRequerimientos, cartaVisualizarEmpresa, cartaBuscarEquipo, cartaArchivo});
	}

	private void agregarCartas(Carta[] cartas) {
		for (Carta carta : cartas) {
			agregarCarta(carta);
		}
	}
	
	private void agregarCarta(Carta carta) {
		crearOpcionMenu(carta);
		
		contentPane.add(carta, carta.getNombre());
	}
	
	private void crearOpcionMenu(Carta carta) {
		JMenuItem itemCarta = new JMenuItem(carta.getNombre());
		
		itemCarta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				cardLayout.show(contentPane, carta.getNombre());
			}
		});
		
		menuAcciones.add(itemCarta);
	}

	private void inicializarCartasOcultas() {
		inicializarCartaVisualizarEquipo();
	}
	
	private void inicializarCartaVisualizarEquipo() {
		this.cartaVisualizarEquipo = new CartaVisualizarEquipo(this);
		contentPane.add(cartaVisualizarEquipo, cartaVisualizarEquipo.getNombre());
	}

	public void equipoEncontrado(Equipo equipo) {
		this.cartaBuscarEquipo.nuevoEncontrado(equipo.getCalificacionTotal());
		this.cartaVisualizarEquipo.cargarEquipo(equipo);
	}

	public void actualizarEstadisticas(EstadisticasDeBusqueda estadisticas) {
		this.cartaBuscarEquipo.actualizarEstadisticas(estadisticas);
	}

	public void actualizarEstado(String estado) {
		this.cartaBuscarEquipo.actualizarEstado(estado);
	}

	public void iniciarBusqueda() {
		deshabilitarAcciones();
		this.cartaBuscarEquipo.busquedaIniciada();
	}
	
	public void deshabilitarAcciones() {
		this.menuAcciones.setEnabled(false);
	}
	
	public void busquedaTerminada() {
		habilitarAcciones();
		this.cartaBuscarEquipo.busquedaTerminada();
	}
	
	public void habilitarAcciones() {
		this.menuAcciones.setEnabled(true);
	}
	
	public void visualizarEquipo() {
		cardLayout.show(contentPane, this.cartaVisualizarEquipo.getNombre());
	}
	
	public Presenter getPresenter() {
		return presenter;
	}
	
	public CartaAgregarIncompatibilidades getCartaAgregarIncompatibilidades() {
		return cartaAgregarIncompatibilidades;
	}

	public CartaVisualizarEmpresa getCartaVisualizarEmpresa() {
		return cartaVisualizarEmpresa;
	}
}
