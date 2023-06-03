package interfaz;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

import negocio.Empleado;
import javax.swing.JButton;

public class TarjetaVisualizarEmpresa extends Tarjeta {

	private static final long serialVersionUID = 3960547276495318473L;
	private static final String NOMBRE = "Visualizar Empresa";
	private CarrouselEmpleados carrouselEmpleados;
	
	public TarjetaVisualizarEmpresa(ElEquipoIdeal padre) {
		super(padre);
		
		crearLabelExplicativa();
		inicializarCarrouselEmpleados();
		crearBotones();
	}

	private void crearLabelExplicativa() {
		JLabel lblVisualizarEmpresa = new JLabel("Visualizar Empresa");
		lblVisualizarEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizarEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVisualizarEmpresa.setBounds(139, 11, 159, 32);
		add(lblVisualizarEmpresa);
	}

	private void inicializarCarrouselEmpleados() {
		carrouselEmpleados = new CarrouselEmpleados();
		carrouselEmpleados.setBounds(65, 65, 320, 145);
		add(carrouselEmpleados);
		carrouselEmpleados.setBorder(BorderFactory.createLineBorder(Color.black));
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

	public void refrescarCarrouselEmpleados(Empleado[] empleados) {
		this.carrouselEmpleados.refrescar(empleados);
	}
	
	@Override
	public String getNombre() {
		return NOMBRE;
	}
}
