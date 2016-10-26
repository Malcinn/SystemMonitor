package pl.lodz.uni.math.app.systemmanager.shared;

import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class SocketInfo implements Serializable {

	private static final long serialVersionUID = 1169939767666549706L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	@Column
	private String hostName = null;

	@Column
	private int port = 0;

	public SocketInfo() {
	}

	public SocketInfo(String hostName, int port) {
		setHostName(hostName);
		setPort(port);
	}

	public SocketInfo(Socket socket) {
		setHostName(socket.getLocalAddress().getHostAddress());
		setPort(socket.getLocalPort());
	}

	public SocketInfo(ServerSocket serverSocket) {
		setHostName(serverSocket.getInetAddress().getHostAddress());
		setPort(serverSocket.getLocalPort());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHostName() {
		return hostName;
	}

	private void setHostName(String hostName) {
		this.hostName = hostName;
	}

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
