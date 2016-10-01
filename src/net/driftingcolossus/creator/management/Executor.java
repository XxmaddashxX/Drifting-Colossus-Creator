package net.driftingcolossus.creator.management;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

public final class Executor {
	
	private static ScheduledExecutorService service_timed;
	
	
	static{
		
		service_timed = Executors.newScheduledThreadPool(8);
		
	}

}
