import java.util.LinkedList;

public class Logger {

	private static LinkedList<Log> logs = new LinkedList<Log>();;
	public Logger() {
	}

	public static void log(String name, Time timeStamp, int clientId, Desc description, double amount)
	{
		Log l = new Log(name, timeStamp, clientId, description, amount);
		logs.add(l);
	}
	public static LinkedList<Log> getLogs()
	{
		return logs;
	}
}
