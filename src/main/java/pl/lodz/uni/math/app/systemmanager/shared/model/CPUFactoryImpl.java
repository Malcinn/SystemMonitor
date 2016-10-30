package pl.lodz.uni.math.app.systemmanager.shared.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.Sigar;

public class CPUFactoryImpl extends ComponentFactory implements CPUFactory {

	private static final Logger LOG = LogManager.getLogger(CPUFactoryImpl.class.getName());

	public CPUFactoryImpl(Sigar sigar) {
		super(sigar);
	}

	@Override
	public CPU createCPU() {
		CPU cpu = new CPU();
		try {
			double[] loadAverageList = sigar.getLoadAverage();
			if (loadAverageList != null && (loadAverageList.length > 0))
				cpu.setLoadAverage(loadAverageList[0]);

			CpuInfo[] cpuInfoList = sigar.getCpuInfoList();
			if (cpuInfoList != null && (cpuInfoList.length > 0)) {
				CpuInfo cpuInfo = cpuInfoList[0];
				cpu.setCores(cpuInfo.getTotalCores());
				cpu.setMhz(cpuInfo.getMhz());
				cpu.setModel(cpuInfo.getModel());
				cpu.setVendor(cpuInfo.getVendor());
			}
		} catch (Throwable t) {
			LOG.error("Error ocurred while getting Cpu object. Throwable: " + t.getMessage());
		}
		return cpu;
	}

}
