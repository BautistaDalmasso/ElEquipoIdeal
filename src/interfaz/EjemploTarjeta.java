package interfaz;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class EjemploTarjeta extends JPanel {

	private static final long serialVersionUID = -2210692703946552413L;
	
	public EjemploTarjeta(JPanel parent, CardLayout cardLayout, String name) {
		setLayout(null);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(182, 46, 46, 14);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel.setText(name);
				cardLayout.next(parent);
			}
		});
		btnNewButton.setBounds(158, 184, 89, 23);
		add(btnNewButton);
		
	}
}
