package interfaz;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import negocio.Empresa;

public class ElEquipoIdeal extends JFrame {

	private static final long serialVersionUID = 8211971627980187103L;
	private JPanel contentPane;
	private CardLayout cardLayout;
	
	Empresa empresa;
	
	
	private TarjetaAgregarEmpleados tarjetaAgregarEmpleados;
	private static final String NOMBRE_AGREGAR_EMPLEADOS = "Agregar Empleados";

	private static final String PRIMERA_TARJETA = NOMBRE_AGREGAR_EMPLEADOS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		
		inicializarTarjetas();
		
		empresa = new Empresa();
	}

	private void inicializarDimensiones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 325);
		cardLayout = new CardLayout();
		contentPane = new JPanel(cardLayout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setResizable(false);
		
		setContentPane(contentPane);
	}

	private void inicializarTarjetas() {
		inicializarTarjetaAgregarEmpleados();
		
		setTarjetaInicial();
	}

	private void inicializarTarjetaAgregarEmpleados() {
		this.tarjetaAgregarEmpleados = new TarjetaAgregarEmpleados(this);
		
		contentPane.add(tarjetaAgregarEmpleados, NOMBRE_AGREGAR_EMPLEADOS);
	}
	
	private void setTarjetaInicial() {
		this.cardLayout.show(contentPane, PRIMERA_TARJETA);
	}
}
