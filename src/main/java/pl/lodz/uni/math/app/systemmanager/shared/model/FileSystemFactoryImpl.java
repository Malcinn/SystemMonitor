package pl.lodz.uni.math.app.systemmanager.shared.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;

public class FileSystemFactoryImpl extends ComponentFactory implements FileSystemFactory {

	private static final Logger LOG = LogManager.getLogger(FileSystemFactoryImpl.class.getName());

	public FileSystemFactoryImpl(Sigar sigar) {
		super(sigar);
	}

	@Override
	public List<FileSystem> createFileSystem() {
		List<FileSystem> fileSystemList = new ArrayList<>();
		Set<String> fileSystems = new HashSet<String>();
		try {
			org.hyperic.sigar.FileSystem[] fileSystemArray = sigar.getFileSystemList();
			for (org.hyperic.sigar.FileSystem sigarFileSystem : fileSystemArray) {
				try {
					FileSystemUsage fileSystemUsage = sigar.getFileSystemUsage(sigarFileSystem.getDirName());
					if ((fileSystems.add(sigarFileSystem.getDirName())) && (fileSystemUsage.getTotal() != 0)) {
						FileSystem fileSystem = new FileSystem();
						fileSystem.setFree(fileSystemUsage.getFree());
						fileSystem.setName(sigarFileSystem.getDirName());
						fileSystem.setTotal(fileSystemUsage.getTotal());
						fileSystemList.add(fileSystem);
					}
				} catch (Throwable t) {
					LOG.error("Error ocurred while gathering data about fileSystem(name:" + sigarFileSystem.getDirName()
							+ "). Throwable: " + t.getMessage());
				}
			}
		} catch (Throwable t) {
			LOG.error("Error ocurred while getting fileSystemList object. Throwable: " + t.getMessage());
		}
		return fileSystemList;
	}

}
