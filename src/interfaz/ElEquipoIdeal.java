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

public class ElEquipoIdeal extends JFrame {

	private static final long serialVersionUID = 8211971627980187103L;
	
	private static final String NOMBRE_AGREGAR_EMPLEADOS = "Agregar Empleados";
	private static final String NOMBRE_AGREGAR_INCOMPATIBILIDADES = "Agregar Incompatibilidades";
	private static final String[] NOMBRES_ACCIONES = {NOMBRE_AGREGAR_EMPLEADOS, NOMBRE_AGREGAR_INCOMPATIBILIDADES};
	
	private static final String PRIMERA_TARJETA = NOMBRE_AGREGAR_EMPLEADOS;

	private JPanel contentPane;
	private CardLayout cardLayout;
		
	private Presenter presenter;

	private JMenuBar barraMenus;
	private JMenu menuAcciones;

	private TarjetaAgregarEmpleados tarjetaAgregarEmpleados;
	private TarjetaAgregarIncompatibilidades tarjetaAgregarIncompatibilidades;



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
		
		inicializarTarjetas();
		
		crearMenuDesplegable();
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
	
	private void inicializarTarjetas() {
		inicializarTarjetaAgregarEmpleados();
		inicializarTarjetaAgregarIncompatibilidades();
		
		setTarjetaInicial();
	}

	private void inicializarTarjetaAgregarEmpleados() {
		this.tarjetaAgregarEmpleados = new TarjetaAgregarEmpleados(this);
		
		contentPane.add(tarjetaAgregarEmpleados, NOMBRE_AGREGAR_EMPLEADOS);
	}
	
	private void inicializarTarjetaAgregarIncompatibilidades() {
		this.tarjetaAgregarIncompatibilidades = new TarjetaAgregarIncompatibilidades(this);
		
		contentPane.add(tarjetaAgregarIncompatibilidades, NOMBRE_AGREGAR_INCOMPATIBILIDADES);
	}
	
	private void setTarjetaInicial() {
		this.cardLayout.show(contentPane, PRIMERA_TARJETA);
	}

	private void crearMenuDesplegable() {
		barraMenus = new JMenuBar();
		menuAcciones = new JMenu("Acciones");
		
		JPanel[] tarjetas = {tarjetaAgregarEmpleados, tarjetaAgregarIncompatibilidades};
		
		for (int i = 0; i < tarjetas.length; i++) {
			crearOpcionMenu(tarjetas[i], NOMBRES_ACCIONES[i]);
		}
		
		barraMenus.add(menuAcciones);
		this.setJMenuBar(barraMenus);
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
}
