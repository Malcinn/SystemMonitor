package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 6214373918231350071L;

	private String name;

	private String device;

	private double upTime;

	public User() {
		super();
	}

	public User(String name, String device, double upTime) {
		super();
		this.name = name;
		this.device = device;
		this.upTime = upTime;
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

	public double getUpTime() {
		return upTime;
	}

	public void setUpTime(double upTime) {
		this.upTime = upTime;
	}

	@Override
	public String toString() {
		return "name: " + name + ", device: " + device + ", upTime: " + upTime;
	}
}
