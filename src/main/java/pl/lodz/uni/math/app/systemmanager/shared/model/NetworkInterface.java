package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.io.Serializable;

public class NetworkInterface implements Serializable{

	private static final long serialVersionUID = -2432919121232155072L;

	private String name;

	private String type;

	private String address;

	private String hardwareAddress;

	private long rxBytes;

	private long txBytes;

	public NetworkInterface() {
	}

	public NetworkInterface(String name, String type, String address, String hardwareAddress, long rxBytes,
			long txBytes) {
		this.name = name;
		this.type = type;
		this.address = address;
		this.hardwareAddress = hardwareAddress;
		this.rxBytes = rxBytes;
		this.txBytes = txBytes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHardwareAddress() {
		return hardwareAddress;
	}

	public void setHardwareAddress(String hardwareAddress) {
		this.hardwareAddress = hardwareAddress;
	}

	public long getRxBytes() {
		return rxBytes;
	}

	public void setRxBytes(long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public long getTxBytes() {
		return txBytes;
	}

	public void setTxBytes(long txBytes) {
		this.txBytes = txBytes;
	}

	@Override
	public String toString() {
		return "name: " + name + ", type: " + type + ", address: " + address + ", hardwareAddress: " + hardwareAddress
				+ ", rxBytes: " + rxBytes + ", txBytes: " + txBytes;
	}
}
