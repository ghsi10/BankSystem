import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Log {
	private int clientId;
	private Time timeStamp;
	private String description, name;
	private double amount;

	public Log(String name ,Time timeStamp, int clientId, Desc description, double amount) {
		this.name = name;
		this.timeStamp = timeStamp;
		this.clientId = clientId;
		this.description = description.getDesc();
		this.amount = amount;
		addToFile();
	}
	public void addToFile()
	{
		FileWriter f;
		try {
			f = new FileWriter("Log.txt", true);
			PrintWriter pw = new PrintWriter(f);
			pw.append(toString());
			pw.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String toString()
	{
		return "Logged From - " +name + "   Time: " + timeStamp +"   Client ID: "+ clientId + "   Description: \"" + description + "\"   Amount: " + amount+ "\n";
	}


}
