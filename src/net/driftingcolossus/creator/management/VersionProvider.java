package net.driftingcolossus.creator.management;

public final class VersionProvider {
	
	
	private static final String BUILD_NAME = "Apollo";
	private static final int BUILD_MAJOR = 1;
	private static final int BUILD_MEDIUM = 0;
	private static final int BUILD_MINOR = 0;
	private static final int BUILD_VERSION = 1000;
	
	
	
	public static String getVersionAsString(){
		return BUILD_NAME + " " + BUILD_MAJOR + "." + BUILD_MEDIUM + "." + BUILD_MINOR + ":" + BUILD_VERSION;
	}

}
