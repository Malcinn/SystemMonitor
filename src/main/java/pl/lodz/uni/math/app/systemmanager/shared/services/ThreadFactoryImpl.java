package pl.lodz.uni.math.app.systemmanager.shared.services;

import java.util.concurrent.ThreadFactory;

public class ThreadFactoryImpl implements ThreadFactory {

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r);
	}

}
