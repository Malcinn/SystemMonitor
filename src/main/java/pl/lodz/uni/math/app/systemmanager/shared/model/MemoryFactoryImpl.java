package pl.lodz.uni.math.app.systemmanager.shared.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;

public class MemoryFactoryImpl extends ComponentFactory implements MemoryFactory {

	private static final Logger LOG = LogManager.getLogger(MemoryFactoryImpl.class.getName());

	public MemoryFactoryImpl(Sigar sigar) {
		super(sigar);
	}

	@Override
	public Memory createMemory() {
		Memory memory = new Memory();
		try {
			Mem mem = sigar.getMem();
			memory.setFree(mem.getActualFree());
			memory.setTotal(mem.getTotal());
		} catch (Throwable t) {
			LOG.error("Error ocurred while gathering data about Memory. Throwable: " + t.getMessage());
		}
		return memory;
	}

}
