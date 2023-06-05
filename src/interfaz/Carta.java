package interfaz;

import javax.swing.JPanel;

public abstract class Carta extends JPanel {
	
	private static final long serialVersionUID = -6130341013156231055L;
	
	private ElEquipoIdeal padre;

	/**
	 * Create the panel.
	 */
	public Carta(ElEquipoIdeal padre) {
		this.padre = padre;
		setLayout(null);
	}

	public ElEquipoIdeal getPadre() {
		return padre;
	}
	
	public abstract String getNombre();
}
