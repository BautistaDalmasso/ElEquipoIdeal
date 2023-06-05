package interfaz;

import javax.swing.JPanel;

import negocio.Empleado;

import java.awt.CardLayout;

public class CarrouselEmpleados extends JPanel {

	private static final long serialVersionUID = -5781947526493556533L;
	private CardLayout cardLayout;
	private Empleado[] empleados;
	
	private int tarjetaActual;
	private ElEquipoIdeal interfaz;

	public CarrouselEmpleados(Empleado[] empleados, ElEquipoIdeal interfaz) {
		cardLayout = new CardLayout(0, 0);
		setLayout(cardLayout);
		
		this.empleados = empleados;
		this.interfaz = interfaz;
		
		inicializarVisualizadores();
		inicializarTarjetaActual();
	}

	public CarrouselEmpleados(ElEquipoIdeal interfaz) {
		this(new Empleado[] {}, interfaz);
	}

	private void inicializarVisualizadores() {
		for (Empleado empleado : empleados) {
			add(new VisualizadorEmpleado(empleado, interfaz));
		}
	}
	
	private void inicializarTarjetaActual() {
		tarjetaActual = 0;
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
