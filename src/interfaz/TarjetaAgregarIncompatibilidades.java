package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class TarjetaAgregarIncompatibilidades extends JPanel {

	private static final long serialVersionUID = -4392978621224841628L;
	
	private static final int CANTIDAD_DE_INCOMPATIBLES = 2;
	private static final int POS_Y_INICIAL = 86;
	private static final int SEPARACION = 50;
	
	private JComboBox<String>[] comboBoxesEmpleados;
	
	
	/**
	 * Create the panel.
	 */
	public TarjetaAgregarIncompatibilidades() {
		setLayout(null);
		
		crearLabelExplicativa();
		
		crearComboBoxesEmpleados();
		
		crearBotonIncompatibilizar();

	}

	private void crearLabelExplicativa() {
		JLabel lblAgregarIncompatibilidad = new JLabel("Agregar Incompatibilidad");
		lblAgregarIncompatibilidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAgregarIncompatibilidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarIncompatibilidad.setBounds(120, 27, 205, 27);
		add(lblAgregarIncompatibilidad);
	}
	
	@SuppressWarnings("unchecked")
	private void crearComboBoxesEmpleados() {
		this.comboBoxesEmpleados = (JComboBox<String>[]) new JComboBox<?>[CANTIDAD_DE_INCOMPATIBLES];
		for (int i = 0; i < CANTIDAD_DE_INCOMPATIBLES; i++) {
			crearComboBoxEmpleado(i);
		}
		
		incializarComboBoxes();
	}

	private void crearComboBoxEmpleado(int i) {
		JLabel lblEmpleado = new JLabel("Empleado " + i+1 + ":");
		lblEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmpleado.setBounds(22, POS_Y_INICIAL + SEPARACION*i, 91, 22);
		
		JComboBox<String> cbEmpleado = new JComboBox<String>();
		cbEmpleado.setBounds(120, POS_Y_INICIAL + SEPARACION*i, 205, 22);
		
		this.comboBoxesEmpleados[i] = cbEmpleado;
		
		add(cbEmpleado);
		add(lblEmpleado);
	}

	private void incializarComboBoxes() {
		for (JComboBox<String> comboBoxEmpleado : comboBoxesEmpleados) {
			// TODO: pasarle strings al modelo.
			comboBoxEmpleado.setModel(new DefaultComboBoxModel<String>());
		}
	}
	
	private void crearBotonIncompatibilizar() {
		// TODO: agregar el action listener.
		JButton btnIncompatibilizar = new JButton("Incompatibilizar");
		btnIncompatibilizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIncompatibilizar.setBounds(151, 223, 150, 40);
		add(btnIncompatibilizar);
	}
}
