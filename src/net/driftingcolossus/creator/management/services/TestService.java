package net.driftingcolossus.creator.management.services;

import net.driftingcolossus.creator.management.LogLevel;

public class TestService extends LogService {

	public TestService() {
		super("Test");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onLogRecieved(String timedate, LogLevel level, String category, String message) {
		System.out.println(category + "    " + message);
		// TODO Auto-generated method stub

	}

	@Override
	public void onErrorRecieved(String timedate, LogLevel level, String category, String error) {
		// TODO Auto-generated method stub

	}

}
