package interfaz;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import negocio.Empleado;
import negocio.Rol;

public class TarjetaVisualizarEmpresa extends Tarjeta {

	private static final long serialVersionUID = 3960547276495318473L;
	private static final String NOMBRE = "Visualizar Empresa";
	private CarrouselEmpleados carrouselEmpleados;
	
	public TarjetaVisualizarEmpresa(ElEquipoIdeal padre) {
		super(padre);
		
		crearLabelExplicativa();
		inicializarCarrouselEmpleados();
	}

	private void crearLabelExplicativa() {
		JLabel lblVisualizarEmpresa = new JLabel("Visualizar Empresa");
		lblVisualizarEmpresa.setHorizontalAlignment(SwingConstants.CENTER);
		lblVisualizarEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblVisualizarEmpresa.setBounds(139, 11, 159, 32);
		add(lblVisualizarEmpresa);
	}

	private void inicializarCarrouselEmpleados() {
		// TODO: pasar un array de empleados más apropiado.
		carrouselEmpleados = new CarrouselEmpleados(new Empleado[] {new Empleado("Juan", Rol.LIDERDEPROYECTO, 5)});
		carrouselEmpleados.setBounds(65, 65, 320, 145);
		add(carrouselEmpleados);
		carrouselEmpleados.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	@Override
	public String getNombre() {
		return NOMBRE;
	}
}
