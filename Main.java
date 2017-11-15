package udp;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Main {

	public static int port = 4322;

	public static DatagramSocket clientSocket = null;

	public static Topography tg = new Topography();

	public static void main(String[] args) throws SocketException {
		if (args.length > 0) {
			try {
				port = Integer.parseInt(args[0]);
			} catch (NumberFormatException e) {
				System.err.println("Argument" + args[0] + " must be an integer.");
				System.exit(1);
			}
		}

		clientSocket = new DatagramSocket();

		UDPListenerThread th = new UDPListenerThread(port);
		th.start();

		while (true) {
			String[] input = getUserInput();
			processUserCommand(input);
			if (Objects.equals(input[0], "exit")) {
				break;
			}
		}
		
		clientSocket.close();

		System.exit(0);
	}

	public static void processUserCommand(String[] commands) {
		switch (commands[0]) {
		case "myip":
			printMyIp();
			break;
		case "myport":
			printMyPort();
			break;
		case "send":
			try {
				sendMessageTo(commands);
			} catch (NumberFormatException | IOException e) {
				System.err.println("send command must be in the form of: send <ipaddress> <port> <message>");
				System.err.println("Example: send 0 Greetings and salutations.");
			}
			break;
		case "exit":
			// Handle in main loop.
			break;
		default:
			System.err.println("Invalid syntax. Trying running \"help\" first.");
			break;
		}
		System.out.flush();
		System.err.flush();
	}

	private static void sendMessageTo(String[] commands) throws IOException {       
		byte[] sendData = new byte[1024];

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ObjectOutputStream os = new ObjectOutputStream(outputStream);

		os.writeObject(tg);

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 3; i < commands.length; i++) {
			if(i!=3) {
				stringBuilder.append(" ");
			}
			stringBuilder.append(commands[i]);
		}
		String message = stringBuilder.toString();

		sendData = message.getBytes();

		InetAddress ipAddress = InetAddress.getByName(commands[1]);
		int port = Integer.parseInt(commands[2]);

		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);

		clientSocket.send(sendPacket);
	}

	private static void printMyPort() {
		System.out.println("Currently listening on port " + port + ".");
	}

	private static void printMyIp() {
		try {
			System.out.println("This machine's IP address is: " + InetAddress.getLocalHost().getHostAddress() + ".");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}


	// Returns user input tokenized into String array
	public static String[] getUserInput() {
		Scanner scanner = new Scanner(System.in);
		System.out.print(">> ");
		String input = scanner.nextLine();
		return input.split(" ");
	}


}
