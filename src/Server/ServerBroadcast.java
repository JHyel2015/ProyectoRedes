package Server;

import java.io.*;
import java.net.*;
import java.util.HashSet;

public class ServerBroadcast {

	private static final int PUERTO = 9001;
	private static HashSet<String> nombres = new HashSet<String>();
	private static HashSet<PrintWriter> inOuts = new HashSet<PrintWriter>();

	public static void main(String[] args) throws Exception {
		ServerSocket listener = new ServerSocket(PUERTO);
		try {
			while (true) {
				Socket s = listener.accept();
				InetAddress in= s.getInetAddress();
				System.out.println(in.getHostAddress());
				new Handler(listener.accept()).start();
			}
		} finally {
			listener.close();
		}
	}

	private static class Handler extends Thread {
		private String nombre;
		private Socket socket;
		private BufferedReader inputs;
		private PrintWriter outputs;

		public Handler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {

				inputs = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				outputs = new PrintWriter(socket.getOutputStream(), true);

				while (true) {
					outputs.println("INGRESARNOMBRE");
					nombre = inputs.readLine();
					if (nombre == null) {
						return;
					}
					synchronized (nombres) {
						if (!nombres.contains(nombre)) {
							nombres.add(nombre);
							break;
						}
					}
				}
				for (PrintWriter writer : inOuts) {
					writer.println("NOMBREACEPTADO "+nombre+" Ha ingresado al chat");
				}

				inOuts.add(outputs);
				new Thread() {
					public void run() {
						while (true) {
							try {
								String usersOnline = "ONLINES ";
								for (String nom : nombres) {
									usersOnline+=nom+" ";

								}
								for (PrintWriter writer : inOuts) {
									writer.println(usersOnline);
								}
								sleep(5000);
							}catch(Exception e){

							}
						}
					}
				}.start();

				while (true) {

					String input = inputs.readLine();


					if (input == null) {
						return;
					}
					for (PrintWriter writer : inOuts) {
						writer.println("MESSAGE " + nombre + " " + input);
					}
				}
			} catch (IOException e) {
				for (PrintWriter writer : inOuts) {
					writer.println("OUT " + nombre + " Deconectado... Ya no esta disponible en el chat!" );
				}
			} finally {

				if (nombre != null) {
					nombres.remove(nombre);
				}
				if (outputs != null) {
					inOuts.remove(outputs);
				}
				try {
					socket.close();
				} catch (IOException e) {
				}
			}
		}
	}
}