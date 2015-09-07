
public class Platinum extends Client {
	private static double commission = 0.01, interest = 0.005;
	private static String rank = "Platinum";

	public Platinum(int id, String name, double balance) {
		super(id, name, balance,rank,commission,interest);
	}
}
