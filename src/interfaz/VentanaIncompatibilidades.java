package interfaz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import negocio.Empleado;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.Set;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaIncompatibilidades extends JFrame {

	private static final long serialVersionUID = -6738629369452951629L;
	private static final String[] COLUMNAS = {"Nombre", "Rol", "Calificacion Historica"};
	
	private static VentanaIncompatibilidades instancia;
	
	private JPanel contentPane;
	private JLabel lblIncompatibilidades;
	private JTable tablaIncompatibilidades;

	private DefaultTableModel modeloTabla;


	/**
	 * Create the frame.
	 */
	private VentanaIncompatibilidades() {
		inicializarFrame();
		crearLblExplicativa();
		crearTablaIncompatibilidades();
	}

	private void inicializarFrame() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setResizable(false);
		setVisible(false);
	}


	private void crearLblExplicativa() {
		lblIncompatibilidades = new JLabel("Incompatibilidades de [...]");
		lblIncompatibilidades.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncompatibilidades.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIncompatibilidades.setBounds(10, 11, 414, 22);
		contentPane.add(lblIncompatibilidades);
	}


	private void crearTablaIncompatibilidades() {
		JScrollPane spIncompatibilidades = new JScrollPane();
		spIncompatibilidades.setBounds(40, 44, 350, 200);
		contentPane.add(spIncompatibilidades);
		
		inicializarTablaIncompatibilidades();
		
		spIncompatibilidades.setViewportView(tablaIncompatibilidades);
	}

	private void inicializarTablaIncompatibilidades() {
		tablaIncompatibilidades = new JTable();
		tablaIncompatibilidades.setBounds(40, 44, 350, 200);
		
		inicializarModeloTabla();
	}

	private void inicializarModeloTabla() {
		modeloTabla = new DefaultTableModel();
		for (String columna : COLUMNAS) {
			modeloTabla.addColumn(columna);
		}
		tablaIncompatibilidades.setModel(modeloTabla);
	}
	
	public static void mostrarIncompatibilidades(String nombreEmpleado, Set<Empleado> empleadosIncompatibles) {
		VentanaIncompatibilidades instancia = getInstancia();
		instancia.setVisible(true);
		instancia.toFront();
		
		instancia.actualizarLabelExplicativa(nombreEmpleado);
		instancia.refrescarTabla(empleadosIncompatibles);
	}
	
	private static VentanaIncompatibilidades getInstancia() {
		if (instancia == null) {
			instancia = new VentanaIncompatibilidades ();
		}
		return instancia;
	}

	private void actualizarLabelExplicativa(String nombreEmpleado) {
		this.lblIncompatibilidades.setText("Empleados Incompatibles Con " + nombreEmpleado);
	}

	private void refrescarTabla(Set<Empleado> empleadosIncompatibles) {
		inicializarModeloTabla();
		agregarEmpleadosATabla(empleadosIncompatibles);
	}

	private void agregarEmpleadosATabla(Set<Empleado> empleadosIncompatibles) {
		for (Empleado empleado : empleadosIncompatibles) {
			modeloTabla.addRow(getDataEmpleado(empleado));
		}
	}

	private String[] getDataEmpleado(Empleado empleado) {
		return new String[] {empleado.getNombre(), empleado.getRol().toString(), Integer.valueOf(empleado.getCalificacion()).toString()};
	}
}
