package ClienteYWindows;

import java.util.logging.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.*;

public class ClienteBroadcast extends JFrame {  
	private static final long serialVersionUID = 1L;

	private JButton btnCerrarSesion,btnEnviar;
	private JPanel jPanel1,jPanel10,jPanel11,jPanel12,jPanel2,jPanel4,jPanel5,jPanel6,jPanel7,
	jPanel8,jPanel9;
	private static JPanel jPanelMensajes,jpanelUsuariosConectados;
	private JScrollPane jSPSMStoSend,jSPMessages,jSPUsersConec;
	private static JTextArea txtInputTexto;
	private JLabel txtTitle;
	static BufferedReader in;
	static PrintWriter out;
	static final String IPSERVER = "localhost";//ip del servidor al que se quiere conectar

	public ClienteBroadcast() {
		this.setUndecorated(true);
		initComponents();
		this.setBounds(10, 10, 900, 600);
	}

	private void initComponents() {

		jPanel1 = new JPanel();
		jPanel4 = new JPanel();
		jPanel9 = new JPanel();
		txtTitle = new JLabel();
		jPanel8 = new JPanel();
		btnCerrarSesion = new JButton();
		jPanel7 = new JPanel();
		jPanel10 = new JPanel();
		jSPMessages = new JScrollPane();
		jPanel11 = new JPanel();
		jPanelMensajes = new JPanel();
		jSPUsersConec = new JScrollPane();
		jPanel12 = new JPanel();
		jpanelUsuariosConectados = new JPanel();
		jPanel2 = new JPanel();
		jPanel6 = new JPanel();
		jSPSMStoSend = new JScrollPane();
		txtInputTexto = new JTextArea();
		jPanel5 = new JPanel();
		btnEnviar = new JButton();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().setLayout(new CardLayout());

		jPanel1.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
		jPanel4.setBackground(new Color(0, 51, 102));
		jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.LINE_AXIS));
		jPanel9.setBackground(new Color(255, 255, 255));
		jPanel9.setBorder(BorderFactory.createEtchedBorder(new Color(153, 153, 153), 
				new Color(153, 153, 153)));
		jPanel9.setLayout(new GridLayout(1, 0));

		txtTitle.setBackground(new Color(255, 255, 255));
		txtTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtTitle.setText("Broadcasting");
		txtTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		jPanel9.add(txtTitle);
		jPanel4.add(jPanel9);

		jPanel8.setBackground(new Color(255, 255, 255));
		jPanel8.setLayout(new GridLayout(1, 0));

		btnCerrarSesion.setToolTipText("Cerrar programa");
		btnCerrarSesion.setText("Cerrar Sesion");
		btnCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnCloseActionPerformed(evt);
			}
		});
		jPanel8.add(btnCerrarSesion);
		jPanel4.add(jPanel8);
		jPanel1.add(jPanel4);

		jPanel7.setBackground(new Color(51, 51, 51));
		jPanel7.setBorder(BorderFactory.createEtchedBorder(new Color(204, 204, 204),
				new Color(204, 204, 204)));
		jPanel7.setLayout(new CardLayout());
		jPanel10.setLayout(new GridLayout(1, 0));

		jSPMessages.setBorder(BorderFactory.createTitledBorder(null, "Mensajes",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
				new Font("Arial Black", 0, 36)));
		jSPMessages.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jSPMessages.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		jPanel11.setBackground(new Color(255, 255, 255));
		jPanel11.setBorder(BorderFactory.createEtchedBorder());
		jPanel11.setLayout(new CardLayout());

		jPanelMensajes.setLayout(new BoxLayout(jPanelMensajes, BoxLayout.Y_AXIS));
		jPanel11.add(jPanelMensajes, "card2");

		jSPMessages.setViewportView(jPanel11);
		jPanel10.add(jSPMessages);

		jSPUsersConec.setBorder(BorderFactory.createTitledBorder(null, "Usuarios conectados",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, 
				new Font("Arial Black", 0, 36)));
		jSPUsersConec.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jSPUsersConec.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		jPanel12.setBackground(new Color(255, 255, 255));
		jPanel12.setBorder(BorderFactory.createEtchedBorder());
		jPanel12.setLayout(new CardLayout());

		jpanelUsuariosConectados.setLayout(new BoxLayout(jpanelUsuariosConectados, BoxLayout.Y_AXIS));
		jPanel12.add(jpanelUsuariosConectados, "card2");

		jSPUsersConec.setViewportView(jPanel12);

		jPanel10.add(jSPUsersConec);
		jPanel7.add(jPanel10, "card3");
		jPanel1.add(jPanel7);

		jPanel2.setBackground(new Color(51, 51, 51));
		jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.LINE_AXIS));

		jPanel6.setBackground(new Color(51, 51, 51));
		jPanel6.setLayout(new GridLayout(1, 0));

		txtInputTexto.setColumns(20);
		txtInputTexto.setRows(5);
		txtInputTexto.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent evt) {
				txtInputTextoKeyTyped(evt);
			}
		});
		jSPSMStoSend.setViewportView(txtInputTexto);

		jPanel6.add(jSPSMStoSend);
		jPanel2.add(jPanel6);

		jPanel5.setBackground(new Color(255, 255, 255));
		jPanel5.setLayout(new GridLayout(1, 1));

		btnEnviar.setToolTipText("Enviar mensaje");
		btnEnviar.setText("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnEnviarActionPerformed(evt);
			}
		});
		jPanel5.add(btnEnviar);
		jPanel2.add(jPanel5);
		jPanel1.add(jPanel2);

		getContentPane().add(jPanel1, "card2");
		pack();
	}

	private void btnCloseActionPerformed(ActionEvent evt) {
		if (JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?") == 0) {
			this.dispose();
			System.exit(0);
		}
	}

	private void btnEnviarActionPerformed(ActionEvent evt) {
		enviarTexto();
	}

	private void txtInputTextoKeyTyped(KeyEvent evt) {
		char c = evt.getKeyChar();

		if (c == KeyEvent.VK_ENTER) {
			txtInputTexto.setText(txtInputTexto.getText().substring(0, txtInputTexto.getText().length() - 1));
			enviarTexto();
		}
	}

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(ClienteBroadcast.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(ClienteBroadcast.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(ClienteBroadcast.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(ClienteBroadcast.class.getName()).log(Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new ClienteBroadcast().setVisible(true);
					@SuppressWarnings("resource")
					Socket socket = new Socket(IPSERVER, 9001);
					in = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					out = new PrintWriter(socket.getOutputStream(), true);

					while (true) {
						String line = in.readLine();
						if (line.startsWith("INGRESARNOMBRE")) {
							out.println(obtenerName());
							break;
						}
					}
					new Thread() {
						public void run() {
							while (true) {
								try {
									String line = in.readLine();
									if (line.startsWith("MESSAGE")) {
										StringTokenizer stSMSs = new StringTokenizer(line," ");
										stSMSs.nextToken();
										String nombreUsuario = stSMSs.nextToken();

										Mensajes mensaje = new Mensajes(nombreUsuario,  line.substring(8 + nombreUsuario.length()),false);
										mensaje.setSize(jPanelMensajes.getWidth(), mensaje.getHeight());
										jPanelMensajes.add(mensaje);

										jPanelMensajes.revalidate();
										jPanelMensajes.repaint();
									} else {
										if (line.startsWith("ONLINES")) {
											jpanelUsuariosConectados.removeAll();
											StringTokenizer stOnlines = new StringTokenizer(line," ");
											stOnlines.nextToken();

											while (stOnlines.hasMoreTokens()) {
												jpanelUsuariosConectados.add(new Conectados(stOnlines.nextToken()));                                                
											}

										}else{
											if (line.startsWith("OUT")){
												StringTokenizer stOut = new StringTokenizer(line);
												stOut.nextToken();
												String nombreUsuario = stOut.nextToken();
												Mensajes mensaje = new Mensajes(nombreUsuario, line.substring(4 + nombreUsuario.length()),true);
												mensaje.setSize(jPanelMensajes.getWidth(), mensaje.getHeight());
												jPanelMensajes.add(mensaje);
												jPanelMensajes.revalidate();
												jPanelMensajes.repaint();
											}else{
												if (line.startsWith("NOMBREACEPTADO")){
													StringTokenizer stAccept = new StringTokenizer(line);
													stAccept.nextToken();
													String nombreUsuario = stAccept.nextToken();
													Mensajes mensaje = new Mensajes(nombreUsuario, line.substring(15 + nombreUsuario.length()),true);
													mensaje.setSize(jPanelMensajes.getWidth(), mensaje.getHeight());
													jPanelMensajes.add(mensaje);
													jPanelMensajes.revalidate();
													jPanelMensajes.repaint();
												}
											}
										}
									}
								} catch (IOException ex) {
									Logger.getLogger(ClienteBroadcast.class.getName()).log(Level.SEVERE, null, ex);
								}
							}
						}
					}.start();

				} catch (IOException ex) {
					Logger.getLogger(ClienteBroadcast.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	private static String obtenerName() {
		return JOptionPane.showInputDialog(null, "Nombre que se muestra a cada usuario", "Ingrese su nombre de usuario:", JOptionPane.PLAIN_MESSAGE);
	}

	public void enviarTexto() {
		if (txtInputTexto.getText().trim().isEmpty()) {
			txtInputTexto.setText("");
		} else {

			out.println(txtInputTexto.getText());
			txtInputTexto.setText("");
		}
	}    
}