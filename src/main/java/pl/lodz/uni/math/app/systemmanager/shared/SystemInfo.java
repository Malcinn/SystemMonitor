package pl.lodz.uni.math.app.systemmanager.shared;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import pl.lodz.uni.math.app.systemmanager.shared.model.CPU;
import pl.lodz.uni.math.app.systemmanager.shared.model.FileSystem;
import pl.lodz.uni.math.app.systemmanager.shared.model.Memory;
import pl.lodz.uni.math.app.systemmanager.shared.model.NetworkInterface;
import pl.lodz.uni.math.app.systemmanager.shared.model.OS;
import pl.lodz.uni.math.app.systemmanager.shared.model.User;

@XmlRootElement
public class SystemInfo implements Serializable {

	private static final long serialVersionUID = 6078629592108354335L;

	private CPU cpu;

	private List<FileSystem> fileSystems;

	private Memory memory;

	private List<NetworkInterface> networkInterfaces;

	private OS os;

	private List<User> users;

	public SystemInfo() {
	}

	public SystemInfo(CPU cpu, List<FileSystem> fileSystems, Memory memory, List<NetworkInterface> networkInterfaces,
			OS os, List<User> users) {
		this.cpu = cpu;
		this.fileSystems = fileSystems;
		this.memory = memory;
		this.networkInterfaces = networkInterfaces;
		this.os = os;
		this.users = users;
	}

	public CPU getCpu() {
		return cpu;
	}

	public void setCpu(CPU cpu) {
		this.cpu = cpu;
	}

	public List<FileSystem> getFileSystems() {
		return fileSystems;
	}

	public void setFileSystems(List<FileSystem> fileSystems) {
		this.fileSystems = fileSystems;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public List<NetworkInterface> getNetworkInterfaces() {
		return networkInterfaces;
	}

	public void setNetworkInterfaces(List<NetworkInterface> networkInterfaces) {
		this.networkInterfaces = networkInterfaces;
	}

	public OS getOs() {
		return os;
	}

	public void setOs(OS os) {
		this.os = os;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
