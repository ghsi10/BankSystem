import java.sql.Date;
import java.text.SimpleDateFormat;

public class Time {
	private long ts;
	private Date localTime;
	private String format;
	private SimpleDateFormat sdf;

	public Time() {
		ts = System.currentTimeMillis();
		localTime = new Date(ts);
		format = "dd/MM/yyyy HH:mm:ss";
		sdf = new SimpleDateFormat(format);
	}
	public String toString()
	{
		return sdf.format(localTime);
	}
}
