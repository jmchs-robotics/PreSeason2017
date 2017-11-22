
package org.usfirst.frc5933.PreSeason2017;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SocketVision extends Thread {
	public static final String NADA = "nada";
	public static final String RIGHT = "right";
	public static final String LEFT = "left";

	private String ip_;
	private int port_;
	private boolean is_connected_ = false;
	private boolean keep_running = false;
	private byte[] data_ = new byte[1024];
	private DatagramSocket socket_;
	private String direction_ = new String();
	private double degrees_x = 0;
	private double degrees_y = 0;
	private double degrees_width = 0;

	private double distanceHeight = 0;
	private double distanceWidth = 0;

	/**
	 * Each SocketVision object is created with an ip and a port. There is no default constructor. The ip address
	 * is simply checked to be valid, but the port MUST match the port that the co-processor will be writing to.
	 * @param ip
	 * @param port
	 */
	public SocketVision(String ip, int port) {
		ip_ = ip;
		port_ = port;
	}

	/**
	 * DO NOT USE THE DEFAULT CONSTRUCTOR - Every SocketVision object requires an ip and a port, formatted
	 * as a string and an int respectively.
	 * @param ip
	 * @param port
	 */
	public SocketVision(){
	}
	
	/**
	 * This function tries to create a new DatagramSocket object with the port and ip set in the constructor.
	 * 
	 * @return true if IP and port are valid
	 * @return <code>true</code> <strong>IF</strong> valid IP <strong>AND</strong> port
	 * <p></p> <code>false</code> <strong>IF</strong> invalid IP <strong>OR</strong> port
	 */
	public boolean connect() {
		try {
			socket_ = new DatagramSocket(port_);
			socket_.setSoTimeout(1000);
			InetAddress.getByName(ip_);
			is_connected_ = true;
		} catch (UnknownHostException ex) {
			return false;
		} catch (IOException ex) {
			System.out.println("IOExcepton " + ex.getMessage());
			return false;
		}
		return true;
	}

	public boolean is_connected() {
		return is_connected_;
	}

	/**
	 * Read DatagramPackets from the UDP Socket and parses the information into usable variables. 
	 * @return <code>true</code> ::<strong> Datagram Packets </strong>  in valid format
	 * <p></p> <code>false</code> ::<strong> Datagram Packets</strong> failed <code>try...catch</code> block or are <code>null</code>
	 */
	public boolean recv() {
		DatagramPacket packet = new DatagramPacket(data_, data_.length);
		try {
			packet.setLength(data_.length);
			socket_.receive(packet);
			if (packet.getData().length > 0) {

				/*
				 * Once the packet has been received and proven to have some
				 * data in it, the parsing begins. First, get the 'stuff' from
				 * the packet. Then start standardization and splitting.
				 * Finally, use the indices of appropriate data (as shown in the
				 * example) to share the new info with the rest of the robot.
				 * Since these are both threaded, synchronization is an issue,
				 * but is handled nicely later on.
				 */

				String stuffInThePacket = new String(packet.getData(), 0, packet.getLength());

				// String contains:: Identifier: x position, y, width, distance,
				// L/C/R
				// e.g. "Peg found at: -100.14,20.33,15.75,172.56,L "

				// standardize everything. Just in case.
				stuffInThePacket = stuffInThePacket.toLowerCase();

				if (Robot.show_debug_vision) {
					System.out.println("Stuff in the packet is: " + stuffInThePacket);
				}

				// make sure that this is a string you want by testing for an
				// unique character
				if (!stuffInThePacket.contains(":"))
					return false;

				// take out the identifying string to make the rest of
				// processing easier

				String[] noIdentifier = stuffInThePacket.split(":");

				// now is {"peg found at"," -100.14,20.33,15.75,172.56,l "}

				// now is: {"-100.14","20.33","15.75","172.56","l"}
				String[] packetParsing = noIdentifier[1].split(",");

				int count = 0; // keep track of the index in the for loop below
				for (String x : packetParsing) {
					// take the spaces out, just in case there needs to be other
					// recognition later
					packetParsing[count] = x.trim();
					++count;
				}
				// now is: {"-100.14","20.33","15.75","172.56","l"} Look at the
				// last and first indices (pl. index) for the difference

				// following example, is-100.14
				double ldegrees_x = Double.parseDouble(packetParsing[0]);

				// is 20.33
				double ldegrees_y = Double.parseDouble(packetParsing[1]);

				// is 15.75
				double ldegrees_width = Double.parseDouble(packetParsing[2]);

				// is 172.56
				double ldistanceW = Double.parseDouble(packetParsing[3]);

				/*
				 * ADDED 2/21/17 due to small change in UpBoard code. Example is
				 * still valid otherwise.
				 */
				double ldistanceH = Double.parseDouble(packetParsing[4]);
				String ldirection_;
				if (packetParsing[5].equalsIgnoreCase("l")) {
					ldirection_ = LEFT;

				} else if (packetParsing[5].equalsIgnoreCase("r")) {
					ldirection_ = RIGHT;

				} else if (packetParsing[5].equalsIgnoreCase("c")) {
					ldirection_ = NADA;

				} else {
					if (Robot.show_debug_vision) {
						System.err.println("My mayonnaise went bad!! :(");
					}
					ldirection_ = NADA;
				}

				synchronized (this) {
					degrees_x = ldegrees_x;
					degrees_y = ldegrees_y;
					degrees_width = ldegrees_width;
					distanceWidth = ldistanceW;
					distanceHeight = ldistanceH;
					direction_ = ldirection_;
				}

				if (Robot.show_debug_vision) {
					System.out.println("Done got that data! " + stuffInThePacket);
					SmartDashboard.putString("Port " + port_ + " output: ", stuffInThePacket);

				}
				return true;
			}
		} catch (Exception e) {
			if (Robot.show_debug_vision) {
				System.err.println(e);
			}
			return false;
		}
		return false;
	}

	@Override
	public void run() {
		// this is the threaded method that constantly checks
		// and reads the socket.
		keep_running = true;
		while (keep_running) {
			if (!is_connected()) {
				connect();
			}
			recv();
		}
	}
	
	/**
	 * This function stops the UDP socket properly, so it can be restarted later.
	 */
	public void stoprunning() {
		keep_running = false;

		socket_.disconnect();
		socket_.close();
	}

	// the below methods are the easiest way to access the data that was grabbed
	// from the string below
	public synchronized String get_direction() {
		String tmp = NADA;

		if (direction_ != null) {
			tmp = direction_;
			direction_ = NADA;
		}
		return tmp;
	}

	public synchronized double get_degrees_x() {
		double tmp = degrees_x;
		degrees_x = 0;
		return tmp;
	}

	public synchronized double get_degrees_y() {
		double tmp = degrees_y;
		degrees_y = 0;
		return tmp;
	}

	public synchronized double get_width() {
		double tmp = degrees_width;
		degrees_width = 0;
		return tmp;
	}

	public synchronized double get_distance_height() {
		double tmp = distanceHeight;
		distanceHeight = 0;
		return tmp;
	}

	public synchronized double get_distance_width() {
		double tmp = distanceWidth;
		distanceWidth = 0;
		return tmp;
	}
}