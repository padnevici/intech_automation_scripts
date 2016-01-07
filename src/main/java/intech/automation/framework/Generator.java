package intech.automation.framework;

public class Generator {
	public static String getEpoch() {
		return (System.currentTimeMillis() / 1000) + "";
	}

	public static String getRandomName() {
		return "testname_" + getEpoch();
	}
	
	public static String getRandomMessage() {
		return "test message " + getEpoch();
	}

	public static String getRandomEmail() {
		return String.format("test_%s@testmail.com", getEpoch());
	}
}
