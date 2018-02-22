package ClienteYWindows;

import java.awt.*;
import org.netbeans.lib.awtextra.*;
import javax.swing.*;
import javax.swing.border.*;

public class Mensajes extends JPanel {

	private JLabel iconAbandonar;
	private JPanel jPanel1,jPanel2,jPanel3,jPanel5;
	private JScrollPane jSPOnlines;
	private JTextArea jTextArea1;

	private static final long serialVersionUID = 1L;
	public Mensajes(String usuario,String mensaje, boolean flag) {
		initComponents();
		jPanel1.setBorder(BorderFactory.createTitledBorder(null, usuario,
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION,
				new Font("Arial", 0, 18)));
		jTextArea1.setText(mensaje);
		if(flag==true){
		}
	}

	private void initComponents() {

		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jPanel5 = new JPanel();
		iconAbandonar = new JLabel();
		jPanel3 = new JPanel();
		jSPOnlines = new JScrollPane();
		jTextArea1 = new JTextArea();

		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		setLayout(new AbsoluteLayout());

		jPanel1.setBorder(BorderFactory.createTitledBorder(null, "Usuario", 
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
				new Font("Arial", 0, 18), Color.BLACK));
		jPanel1.setLayout(new CardLayout());

		jPanel2.setBackground(new Color(204, 255, 255));
		jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.X_AXIS));

		jPanel5.setBackground(new Color(204, 0, 0));
		jPanel5.setLayout(new GridLayout());
		jPanel5.add(iconAbandonar);

		jPanel2.add(jPanel5);

		jPanel3.setBackground(new Color(204, 255, 255));
		jPanel3.setLayout(new CardLayout());

		jTextArea1.setColumns(20);
		jTextArea1.setFont(new Font("Times New Roman", 0, 14));
		jTextArea1.setRows(5);
		jSPOnlines.setViewportView(jTextArea1);

		jPanel3.add(jSPOnlines, "card3");

		jPanel2.add(jPanel3);

		jPanel1.add(jPanel2, "card2");

		add(jPanel1, new AbsoluteConstraints(1, 1, 920, 110));
	}
}