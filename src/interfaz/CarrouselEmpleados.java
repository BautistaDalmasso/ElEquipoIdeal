package interfaz;

import javax.swing.JPanel;

import negocio.Empleado;

import java.awt.CardLayout;

public class CarrouselEmpleados extends JPanel {

	private static final long serialVersionUID = -5781947526493556533L;
	private CardLayout cardLayout;
	private Empleado[] empleados;
	
	private int tarjetaActual;

	public CarrouselEmpleados(Empleado[] empleados) {
		cardLayout = new CardLayout(0, 0);
		setLayout(cardLayout);
		
		this.empleados = empleados;
		
		inicializarVisualizadores();
		inicializarTarjetaActual();
	}

	public CarrouselEmpleados() {
		this(new Empleado[] {});
	}

	private void inicializarVisualizadores() {
		int i = 0;
		for (Empleado empleado : empleados) {
			add(new VisualizadorEmpleado(empleado), "" + i++);
		}
	}
	
	private void inicializarTarjetaActual() {
		tarjetaActual = 0;
		cardLayout.show(this, "" + tarjetaActual);
	}
	
	public void siguiente() {
		cardLayout.next(this);
		siguienteTarjeta();
	}

	private void siguienteTarjeta() {		
		tarjetaActual = ++tarjetaActual > empleados.length ? 0 : tarjetaActual;
	}

	public void anterior() {
		cardLayout.previous(this);
		retrocederTarjeta();
	}

	private void retrocederTarjeta() {
		tarjetaActual = --tarjetaActual < 0 ? empleados.length-1 : tarjetaActual;
	}
	
	public void refrescar(Empleado[] empleados) {
		this.removeAll();
		this.empleados = empleados;
		inicializarVisualizadores();
		inicializarTarjetaActual();
	}
}
