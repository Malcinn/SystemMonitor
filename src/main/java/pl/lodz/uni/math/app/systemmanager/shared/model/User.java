package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 6214373918231350071L;

	private String name;

	private String device;

	public User() {
		super();
	}

	public User(String name, String device) {
		super();
		this.name = name;
		this.device = device;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@Override
	public String toString() {
		return "name: " + name + ", device: " + device;
	}
}
