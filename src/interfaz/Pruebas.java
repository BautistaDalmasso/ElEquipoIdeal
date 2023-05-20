package interfaz;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Pruebas extends JFrame {

	private static final long serialVersionUID = 8211971627980187103L;
	private JPanel contentPane;
	private CardLayout cardLayout;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pruebas frame = new Pruebas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Pruebas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		cardLayout = new CardLayout();
		contentPane = new JPanel(cardLayout);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.add(new EjemploTarjeta(contentPane, cardLayout, "1"), "Ejemplo1");
		contentPane.add(new EjemploTarjeta(contentPane, cardLayout, "2"), "Ejemplo2");
		
		cardLayout.show(contentPane, "Ejemplo1");
	}

}
