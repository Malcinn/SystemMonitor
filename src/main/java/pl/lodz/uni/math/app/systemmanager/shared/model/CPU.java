package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.io.Serializable;

public class CPU implements Serializable{

	private static final long serialVersionUID = 3049059280349088450L;

	private String model;

	private String vendor;

	private int cores;

	private int mhz;

	private double loadAverage;

	public CPU() {
	}

	public CPU(String model, String vendor, int cores, int mhz, double loadAverage) {
		super();
		this.model = model;
		this.vendor = vendor;
		this.cores = cores;
		this.mhz = mhz;
		this.loadAverage = loadAverage;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public int getCores() {
		return cores;
	}

	public void setCores(int cores) {
		this.cores = cores;
	}

	public int getMhz() {
		return mhz;
	}

	public void setMhz(int mhz) {
		this.mhz = mhz;
	}

	public double getLoadAverage() {
		return loadAverage;
	}

	public void setLoadAverage(double loadAverage) {
		this.loadAverage = loadAverage;
	}

	@Override
	public String toString() {
		return "model: " + model + ", vendor: " + vendor + ", " + vendor + ", cores: " + cores + ", Mhz: " + mhz
				+ ", loadAverage: " + loadAverage;
	}
}
