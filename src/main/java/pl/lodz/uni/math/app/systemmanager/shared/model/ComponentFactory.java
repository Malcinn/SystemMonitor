package pl.lodz.uni.math.app.systemmanager.shared.model;

import org.hyperic.sigar.Sigar;

public abstract class ComponentFactory {

	protected Sigar sigar;
	
	public ComponentFactory(Sigar sigar){
		this.sigar = sigar;
	}
}
