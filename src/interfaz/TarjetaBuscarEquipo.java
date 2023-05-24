package interfaz;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TarjetaBuscarEquipo extends JPanel {
	/*
	 * TODO: esto esta feisimo
	 * -> poner la ventana más bonita
	 * -> mostrar el equipo encontrado de mejor manera
	 * -> evitar que el usuario cambie de ventana mientras se busca
	 * -> permitir que el usuario corte la busqueda antes de tiempo
	 */
	
	
	private static final long serialVersionUID = 5084902088676552823L;
	
	@SuppressWarnings("unused")
	private ElEquipoIdeal padre;
	
	private JTextField tfResultado;

	/**
	 * Create the panel.
	 */
	public TarjetaBuscarEquipo(ElEquipoIdeal padre) {
		setLayout(null);
		
		this.padre = padre;
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(181, 226, 89, 23);
		btnBuscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				padre.getPresenter().resolverInstancia();
			}
		});
		add(btnBuscar);
		
		JLabel lblEncontrado = new JLabel("Encontrado:");
		lblEncontrado.setBounds(77, 109, 62, 14);
		add(lblEncontrado);
		
		tfResultado = new JTextField();
		tfResultado.setEditable(false);
		tfResultado.setBounds(148, 106, 41, 20);
		add(tfResultado);
		tfResultado.setColumns(10);

	}
	
	public void nuevoEncontrado(int valor) {
		tfResultado.setText(Integer.valueOf(valor).toString());
	}
}
