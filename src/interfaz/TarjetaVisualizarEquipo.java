package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import negocio.Empleado;
import negocio.Equipo;

import javax.swing.JTextField;

public class TarjetaVisualizarEquipo extends Tarjeta {

	private static final long serialVersionUID = 4918117592521695651L;
	private static final String NOMBRE = "Visualizar Equipo";
	private CarrouselEmpleados carrouselEmpleados;
	private JTextField tfCalificacionTotal;
	
	public TarjetaVisualizarEquipo(ElEquipoIdeal padre) {
		super(padre);
		
		crearLabelExplicativa();
		
		inicializarCarrouselEmpleados();
		crearZonaCalificacionTotal();
		crearBotones();
	}

	private void crearLabelExplicativa() {
		JLabel lblVisualizarEquipo = new JLabel("Visualizar Equipo");
		lblVisualizarEquipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizarEquipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVisualizarEquipo.setBounds(139, 11, 159, 32);
		add(lblVisualizarEquipo);
	}
	
	private void inicializarCarrouselEmpleados() {
		carrouselEmpleados = new CarrouselEmpleados(getPadre());
		carrouselEmpleados.setBounds(64, 44, 320, 145);
		add(carrouselEmpleados);
		carrouselEmpleados.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	private void crearZonaCalificacionTotal() {
		JLabel lblCalificacionTotal = new JLabel("Calificacion Total Del Equipo:");
		lblCalificacionTotal.setBounds(204, 193, 145, 14);
		add(lblCalificacionTotal);

		tfCalificacionTotal = new JTextField();
		tfCalificacionTotal.setEditable(false);
		tfCalificacionTotal.setColumns(10);
		tfCalificacionTotal.setBounds(343, 190, 41, 20);
		add(tfCalificacionTotal);
	}
	
	private void crearBotones() {
		crearBotonAnterior();
		crearBotonSiguiente();		
	}

	private void crearBotonAnterior() {
		JButton btnAnterior = new JButton("<");
		btnAnterior.setBounds(139, 221, 41, 23);
		btnAnterior.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carrouselEmpleados.anterior();
			}});
		add(btnAnterior);
	}

	private void crearBotonSiguiente() {
		JButton btnSiguiente = new JButton(">");
		btnSiguiente.setBounds(273, 221, 41, 23);
		btnSiguiente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				carrouselEmpleados.siguiente();
			}});
		add(btnSiguiente);
	}

	public void cargarEquipo(Equipo equipo) {
		cargarCalificacionTotal(equipo.getCalificacionTotal());
		refrescarCarrouselEmpleados(equipo.getArregloEmpleados());
	}
	
	private void cargarCalificacionTotal(int calificacionTotal) {
		tfCalificacionTotal.setText(Integer.valueOf(calificacionTotal).toString());
	}

	private void refrescarCarrouselEmpleados(Empleado[] empleados) {
		this.carrouselEmpleados.refrescar(empleados);
	}
	
	@Override
	public String getNombre() {
		return NOMBRE;
	}
}
