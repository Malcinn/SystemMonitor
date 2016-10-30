package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.io.Serializable;

public class FileSystem implements Serializable{

	private static final long serialVersionUID = 9040369552037208398L;

	private String name;

	private long total;

	private long free;

	public FileSystem() {
	}

	public FileSystem(String name, long total, long free) {
		super();
		this.name = name;
		this.total = total;
		this.free = free;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "name: " + name + ", total: " + total + ", free: " + free;
	}
}
