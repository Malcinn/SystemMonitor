package pl.lodz.uni.math.app.systemmanager.shared;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The SocketInfo class stores informations about socket.
 * 
 * @author marcin
 *
 */
public class SocketInfo implements Serializable {

	/**
	 * class version number.
	 */
	private static final long serialVersionUID = 1169939767666549706L;

	private String hostName = null;

	private int port = 0;

	/**
	 * 
	 * @param hostName
	 *            - socket host name
	 * @param port
	 *            - socket port number
	 */
	public SocketInfo(String hostName, int port) {
		setHostName(hostName);
		setPort(port);
	}

	/**
	 * 
	 * @param socket
	 *            - Socket which data will be stored in this class
	 */
	public SocketInfo(Socket socket) {
		setHostName(socket.getLocalAddress().getHostAddress());
		setPort(socket.getLocalPort());
	}

	/**
	 * 
	 * @param serverSocket
	 *            - ServerSocket which data will be stored in this class
	 */
	public SocketInfo(ServerSocket serverSocket) {
		setHostName(serverSocket.getInetAddress().getHostAddress());
		setPort(serverSocket.getLocalPort());
	}

	/**
	 * 
	 * @return a String representing the host name
	 */
	public String getHostName() {
		return hostName;
	}

	private void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * 
	 * @return a int representing the port number
	 */
	public int getPort() {
		return port;
	}

	private void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return String.format("host name: %s port: %s", getHostName(), getPort());
	}
}
