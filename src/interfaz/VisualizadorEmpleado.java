package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import negocio.Empleado;

import javax.swing.JTextField;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Button;

public class VisualizadorEmpleado extends JPanel {
	private static final long serialVersionUID = -4888068994821293491L;

	private JTextField tfNombre;
	private JTextField tfRol;
	private JTextField tfCalificacion;
	private Canvas canvasFoto;

	private Empleado empleado;

	protected ElEquipoIdeal interfaz;

	/**
	 * Create the panel.
	 */
	public VisualizadorEmpleado(Empleado empleado, ElEquipoIdeal interfaz) {
		setLayout(null);

		this.empleado = empleado;
		this.interfaz = interfaz;
		
		crearZonaFoto();
		crearZonaDatos();
	}

	private void crearZonaFoto() {
		canvasFoto = new CanvasFoto("./FotosEmpleados/" + empleado.getNombreFoto());
		canvasFoto.setBounds(10, 15, 100, 100);
		add(canvasFoto);
	}

	private void crearZonaDatos() {
		crearLabelDato();
		crearZonaNombre();
		crearZonaRol();
		crearZonaCalificacion();
		crearBotonIncompatibilidades();
	}

	private void crearLabelDato() {
		JLabel lblDatos = new JLabel("Datos");
		lblDatos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDatos.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatos.setBounds(213, 10, 46, 20);
		add(lblDatos);
	}

	private void crearZonaNombre() {
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setBounds(132, 37, 46, 14);
		add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setEditable(false);
		tfNombre.setBounds(188, 34, 111, 20);
		add(tfNombre);
		tfNombre.setColumns(10);
		
		tfNombre.setText(empleado.getNombre());
	}

	private void crearZonaRol() {
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRol.setBounds(132, 65, 46, 14);
		add(lblRol);
		
		tfRol = new JTextField();
		tfRol.setEditable(false);
		tfRol.setColumns(10);
		tfRol.setBounds(188, 62, 111, 20);
		add(tfRol);
		
		tfRol.setText(empleado.getRol().toString());
	}

	private void crearZonaCalificacion() {
		JLabel lblCalificacion = new JLabel("Calificacion");
		lblCalificacion.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCalificacion.setBounds(116, 90, 62, 14);
		add(lblCalificacion);
		
		tfCalificacion = new JTextField();
		tfCalificacion.setEditable(false);
		tfCalificacion.setColumns(10);
		tfCalificacion.setBounds(188, 90, 111, 20);
		add(tfCalificacion);
		
		tfCalificacion.setText(Integer.valueOf(empleado.getCalificacion()).toString());
	}

	private void crearBotonIncompatibilidades() {
		Button btnIncompatibilidades = new Button("Incompatibilidades");
		btnIncompatibilidades.setBounds(188, 120, 111, 22);
		btnIncompatibilidades.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				interfaz.getPresenter().mostrarIncompatibilidades(empleado);
			}
		});
		add(btnIncompatibilidades);
	}
}
