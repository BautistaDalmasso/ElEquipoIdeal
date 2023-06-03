package interfaz;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class CanvasFoto extends Canvas {

	private static final long serialVersionUID = 2597946796102676445L;
	private String nombreFoto;

	public CanvasFoto(String nombreFoto) {
		this.nombreFoto = nombreFoto;
	}
	
	public void paint(Graphics g) {
		Image i = Toolkit.getDefaultToolkit().getImage(nombreFoto);
		g.drawImage(i, 0, 0, 100, 100, this);
	}
}
