package interfaz;

import javax.swing.JPanel;

public abstract class Tarjeta extends JPanel {
	
	private static final long serialVersionUID = -6130341013156231055L;
	
	private ElEquipoIdeal padre;

	/**
	 * Create the panel.
	 */
	public Tarjeta(ElEquipoIdeal padre) {
		this.padre = padre;
	}

	public ElEquipoIdeal getPadre() {
		return padre;
	}
	
	public abstract String getNombre();
}
