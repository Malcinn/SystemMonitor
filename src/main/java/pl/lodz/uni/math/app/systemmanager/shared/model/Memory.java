package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.io.Serializable;

public class Memory implements Serializable{

	private static final long serialVersionUID = -4050752609659660583L;

	private long total;

	private long free;

	public Memory() {
	}

	public Memory(long total, long free) {
		this.total = total;
		this.free = free;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getFree() {
		return free;
	}

	public void setFree(long free) {
		this.free = free;
	}

	@Override
	public String toString() {
		return "total: " + total + ", free: " + free;
	}
}
