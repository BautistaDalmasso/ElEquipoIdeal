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

	private TarjetaAgregarEmpleados tarjetaAgregarEmpleados;
	private TarjetaAgregarIncompatibilidades tarjetaAgregarIncompatibilidades;
	private TarjetaCrearRequerimientos tarjetaCrearRequerimientos;
	private TarjetaBuscarEquipo tarjetaBuscarEquipo;
	private TarjetaArchivo tarjetaArchivo;

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
		tarjetaAgregarEmpleados = new TarjetaAgregarEmpleados(this);
		tarjetaAgregarIncompatibilidades = new TarjetaAgregarIncompatibilidades(this);
		tarjetaCrearRequerimientos = new TarjetaCrearRequerimientos(this);
		tarjetaBuscarEquipo = new TarjetaBuscarEquipo(this);
		tarjetaArchivo = new TarjetaArchivo(this);
		
		agregarTarjetas(new Tarjeta[] 
				{tarjetaAgregarEmpleados, tarjetaAgregarIncompatibilidades, tarjetaCrearRequerimientos, tarjetaBuscarEquipo, tarjetaArchivo});
	}

	private void agregarTarjetas(Tarjeta[] tarjetas) {
		for (Tarjeta tarjeta : tarjetas) {
			agregarTarjetas(tarjeta, tarjeta.getNombre());
		}
	}
	
	private void agregarTarjetas(JPanel tarjeta, String nombre) {
		crearOpcionMenu(tarjeta, nombre);
		
		contentPane.add(tarjeta, nombre);
	}
	
	private void crearOpcionMenu(JPanel tarjeta, String nombreTarjeta) {
		JMenuItem itemTarjeta = new JMenuItem(nombreTarjeta);
		
		itemTarjeta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				cardLayout.show(contentPane, nombreTarjeta);
			}
		});
		
		menuAcciones.add(itemTarjeta);
	}

	public Presenter getPresenter() {
		return presenter;
	}
	
	public TarjetaAgregarIncompatibilidades getTarjetaAgregarIncompatibilidades() {
		return tarjetaAgregarIncompatibilidades;
	}

	public void equipoEncontrado(Equipo equipo) {
		this.tarjetaBuscarEquipo.nuevoEncontrado(equipo.getCalificacionTotal());
	}

	public void actualizarEstadisticas(EstadisticasDeBusqueda estadisticas) {
		this.tarjetaBuscarEquipo.actualizarEstadisticas(estadisticas);
	}
}
