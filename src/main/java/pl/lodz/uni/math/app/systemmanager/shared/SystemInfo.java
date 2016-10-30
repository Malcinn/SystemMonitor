package pl.lodz.uni.math.app.systemmanager.shared;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SystemInfo implements Serializable{

	private static final long serialVersionUID = 6078629592108354335L;

	private String os;
	
	public SystemInfo() {
	}
	
	public SystemInfo(String os) {
		this.setOs(os);
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
}
