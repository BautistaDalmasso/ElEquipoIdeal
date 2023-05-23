package interfaz;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import negocio.Rol;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.SpinnerNumberModel;

public class TarjetaCrearRequerimientos extends JPanel {

	private static final long serialVersionUID = -3942548359901309794L;

	private static final int SEPARACION_X = 155;
	private static final int SEPARACION_Y = 3;
	
	private JSpinner[] spinnersRoles;

	private ElEquipoIdeal padre;
	
	/**
	 * Create the panel.
	 */
	public TarjetaCrearRequerimientos(ElEquipoIdeal padre) {
		setLayout(null);
		this.padre = padre;
		
		crearLabelExplicativa();
		
		crearSpinners();
		
		crearBotonCrearRequerimientos();

	}

	private void crearLabelExplicativa() {
		JLabel lblCrearRequerimientos = new JLabel("Crear Requerimientos");
		lblCrearRequerimientos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCrearRequerimientos.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrearRequerimientos.setBounds(120, 27, 205, 27);
		add(lblCrearRequerimientos);
	}

	private void crearSpinners() {
		spinnersRoles = new JSpinner[Rol.values().length];
		
		crearSpinnerParaRol(Rol.LIDERDEPROYECTO, 0, 103);
		crearSpinnerParaRol(Rol.ARQUITECTO, 208, 103);
		crearSpinnerParaRol(Rol.PROGRAMADOR, 0, 156);
		crearSpinnerParaRol(Rol.TESTER, 208, 156);
	}

	private void crearSpinnerParaRol(Rol rol, int x, int y) {
		JLabel lblRol = new JLabel(Rol.nombresPluralesRoles[rol.ordinal()]);
		lblRol.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRol.setBounds(x, y, 145, 22);
		add(lblRol);
		
		JSpinner spRol = new JSpinner();
		spRol.setBounds(x+SEPARACION_X, y+SEPARACION_Y, 39, 20);
		spRol.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		add(spRol);
		
		spinnersRoles[rol.ordinal()] = spRol;
	}
	
	private void crearBotonCrearRequerimientos() {
		JButton btnCrear = new JButton("Crear ");
		btnCrear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCrear.setBounds(165, 225, 126, 39);
		
		btnCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				crearRequerimientos();
			}
		});
		
		add(btnCrear);
	}

	private void crearRequerimientos() {
		int[] requerimientos = new int[Rol.values().length];
		
		int i = 0;
		for (JSpinner spinner : spinnersRoles) {
			requerimientos[i++] = (int) spinner.getValue();
		}
		
		padre.getPresenter().crearRequerimientos(requerimientos);
	}
}
