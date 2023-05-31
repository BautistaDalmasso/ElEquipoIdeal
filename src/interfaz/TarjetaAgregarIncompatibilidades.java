package interfaz;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class TarjetaAgregarIncompatibilidades extends Tarjeta {

	private static final long serialVersionUID = -4392978621224841628L;
	private static final String NOMBRE = "Agregar Incompatibilidades";
	
	private static final int CANTIDAD_DE_INCOMPATIBLES = 2;
	private static final int POS_Y_INICIAL = 86;
	private static final int SEPARACION = 50;
	
	private JComboBox<String>[] comboBoxesEmpleados;
	
	
	/**
	 * Create the panel.
	 */
	public TarjetaAgregarIncompatibilidades(ElEquipoIdeal padre) {
		super(padre);

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
		
		agregarComboBoxModels();
	}

	private void crearComboBoxEmpleado(int i) {
		Integer numero = i+1;
		JLabel lblEmpleado = new JLabel("Empleado " + numero.toString() + ":");
		lblEmpleado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmpleado.setBounds(22, POS_Y_INICIAL + SEPARACION*i, 91, 22);
		
		JComboBox<String> cbEmpleado = new JComboBox<String>();
		cbEmpleado.setBounds(120, POS_Y_INICIAL + SEPARACION*i, 205, 22);
		
		this.comboBoxesEmpleados[i] = cbEmpleado;
		
		add(cbEmpleado);
		add(lblEmpleado);
	}

	public void agregarComboBoxModels() {
		for (JComboBox<String> comboBoxEmpleado : comboBoxesEmpleados) {
			comboBoxEmpleado.setModel(new DefaultComboBoxModel<String>(this.getPadre().getPresenter().crearArregloConNombres()));
		}
	}
	
	private void crearBotonIncompatibilizar() {
		JButton btnIncompatibilizar = new JButton("Incompatibilizar");
		btnIncompatibilizar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnIncompatibilizar.setBounds(151, 223, 150, 40);
		add(btnIncompatibilizar);
		btnIncompatibilizar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				incompatibilizar();
			}
		});
	}

	private void incompatibilizar() {
		String[] empleadosSeleccionados = obtenerEmpleadosSeleccionados();
		
		this.getPadre().getPresenter().incompatibilizar(empleadosSeleccionados[0], empleadosSeleccionados[1]);
	}

	private String[] obtenerEmpleadosSeleccionados() {
		String[] ret = new String[2];
		int i = 0;
		for (JComboBox<String> comboBox : this.comboBoxesEmpleados) {
			ret[i++] = (String) comboBox.getSelectedItem();
		}
		return ret;
	}
	
	@Override
	public String getNombre() {
		return NOMBRE;
	}
}
