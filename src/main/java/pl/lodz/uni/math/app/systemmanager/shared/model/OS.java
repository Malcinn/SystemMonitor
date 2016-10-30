package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.io.Serializable;

public class OS implements Serializable {

	private static final long serialVersionUID = 3218475371257311476L;

	private String name;

	private double upTime;

	public OS() {
		super();
	}

	public OS(String name, double upTime) {
		this.name = name;
		this.upTime = upTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUpTime() {
		return upTime;
	}

	public void setUpTime(double upTime) {
		this.upTime = upTime;
	}

	@Override
	public String toString() {
		return "name: " + name + ", upTime: " + upTime;
	}
}
