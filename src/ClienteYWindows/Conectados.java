package ClienteYWindows;

import java.awt.*;
import javax.swing.*;
import org.netbeans.lib.awtextra.*;

public class Conectados extends javax.swing.JPanel {

	private static final long serialVersionUID = 1L;

	public Conectados(String usuario) {
		initComponents();
		txtUsuario.setText("Conectado:  "+usuario);
	}

	private void initComponents() {

		txtUsuario = new JLabel();

		setBorder(BorderFactory.createEtchedBorder(new Color(255, 255, 255),
				new Color(255, 255, 255)));
		setMinimumSize(new Dimension(921, 100));
		setPreferredSize(new Dimension(921, 100));
		setLayout(new AbsoluteLayout());

		txtUsuario.setBackground(new Color(204, 0, 51));
		txtUsuario.setFont(new Font("Arial Black", 0, 18));
		txtUsuario.setText(" txtUsuario");
		add(txtUsuario, new AbsoluteConstraints(30, 40, 840, -1));
	}

	private JLabel txtUsuario;
}