package interfaz;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import negocio.Rol;

import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

public class TarjetaAgregarEmpleados extends Tarjeta {

	private static final long serialVersionUID = 2497385370618487907L;
	private static final String NOMBRE = "Agregar Empleados";
	
	private JTextField fieldNombre;
	private JSlider sliderCalificacion;
	private JComboBox<String> cbRol;
	private JComboBox<String> cbFotos;
	
	/**
	 * Create the panel.
	 */
	public TarjetaAgregarEmpleados(ElEquipoIdeal padre) {
		super(padre);
		
		crearLabelExplicativa();

		crearIngresoDeNombre();
		crearSeleccionDeRol();
		crearSeleccionDeCalificacion();
		crearComboBoxFotos();
		crearBotonAgregar();
	}

	private void crearLabelExplicativa() {
		JLabel lblAgregarEmpleado = new JLabel("Agregar Empleado");
		lblAgregarEmpleado.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarEmpleado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAgregarEmpleado.setBounds(131, 28, 161, 27);
		add(lblAgregarEmpleado);
	}

	private void crearIngresoDeNombre() {
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setLabelFor(fieldNombre);
		lblNombre.setBounds(57, 67, 60, 17);
		add(lblNombre);
		
		fieldNombre = new JTextField();
		fieldNombre.setBounds(127, 67, 197, 20);
		add(fieldNombre);
		fieldNombre.setColumns(10);
	}

	private void crearSeleccionDeRol() {
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblRol.setBounds(58, 98, 59, 18);
		add(lblRol);
		
		cbRol = new JComboBox<String>();
		cbRol.setBounds(127, 98, 197, 22);
		add(cbRol);
		
		cbRol.setModel(new DefaultComboBoxModel<String>(Rol.nombresRoles));
	}

	private void crearSeleccionDeCalificacion() {
		JLabel lblCalificacion = new JLabel("Calificacion:");
		lblCalificacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCalificacion.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCalificacion.setBounds(42, 138, 75, 14);
		add(lblCalificacion);
		
		sliderCalificacion = new JSlider();
		sliderCalificacion.setToolTipText("");
		sliderCalificacion.setPaintTicks(true);
		sliderCalificacion.setPaintLabels(true);
		sliderCalificacion.setSnapToTicks(true);
		sliderCalificacion.setMajorTickSpacing(1);
		sliderCalificacion.setMaximum(5);
		sliderCalificacion.setMinimum(1);
		sliderCalificacion.setBounds(124, 131, 200, 45);
		add(sliderCalificacion);
	}

	private void crearComboBoxFotos() {
		JLabel lblFoto = new JLabel("Foto:");
		lblFoto.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFoto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFoto.setBounds(42, 178, 75, 14);
		add(lblFoto);
		
		cbFotos = new JComboBox<String>();
		cbFotos.setBounds(124, 178, 197, 22);
		add(cbFotos);
		
		cbFotos.setModel(new DefaultComboBoxModel<String>(getPadre().getPresenter().cargarFotosPosibles()));
	}
	
	private void crearBotonAgregar() {
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAgregar.setBounds(165, 223, 121, 40);
		add(btnAgregar);
		
		btnAgregar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarEmpleadoIngresado();
			}
		});
	}
	
	private void agregarEmpleadoIngresado() {		
		this.getPadre().getPresenter().agregarEmpleado(obtenerNombre(), obtenerRol(), obtenerCalificacion(), obtenerFoto());
	}

	private String obtenerNombre() {
		return this.fieldNombre.getText();
	}

	private String obtenerRol() {
		return (String) this.cbRol.getSelectedItem();
	}

	private int obtenerCalificacion() {
		return this.sliderCalificacion.getValue();
	}

	private String obtenerFoto() {
		return (String) this.cbFotos.getSelectedItem();
	}
	
	@Override
	public String getNombre() {
		return NOMBRE;
	}
}
