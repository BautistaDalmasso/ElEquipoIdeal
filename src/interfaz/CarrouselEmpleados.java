package interfaz;

import javax.swing.JPanel;

import negocio.Empleado;

import java.awt.CardLayout;

public class CarrouselEmpleados extends JPanel {

	private static final long serialVersionUID = -5781947526493556533L;
	private CardLayout cardLayout;
	private Empleado[] empleados;
	
	private int cartaActual;
	private ElEquipoIdeal interfaz;

	public CarrouselEmpleados(Empleado[] empleados, ElEquipoIdeal interfaz) {
		cardLayout = new CardLayout(0, 0);
		setLayout(cardLayout);
		
		this.empleados = empleados;
		this.interfaz = interfaz;
		
		inicializarVisualizadores();
		inicializarCartaActual();
	}

	public CarrouselEmpleados(ElEquipoIdeal interfaz) {
		this(new Empleado[] {}, interfaz);
	}

	private void inicializarVisualizadores() {
		for (Empleado empleado : empleados) {
			add(new VisualizadorEmpleado(empleado, interfaz));
		}
	}
	
	private void inicializarCartaActual() {
		cartaActual = 0;
	}
	
	public void siguiente() {
		cardLayout.next(this);
		siguienteCarta();
	}

	private void siguienteCarta() {		
		cartaActual = ++cartaActual > empleados.length ? 0 : cartaActual;
	}

	public void anterior() {
		cardLayout.previous(this);
		retrocederCarta();
	}

	private void retrocederCarta() {
		cartaActual = --cartaActual < 0 ? empleados.length-1 : cartaActual;
	}
	
	public void refrescar(Empleado[] empleados) {
		this.removeAll();
		this.empleados = empleados;
		inicializarVisualizadores();
		inicializarCartaActual();
	}
}
