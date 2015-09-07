
public class Gold extends Client{
	private static double commission = 0.02, interest = 0.003;
	private static String rank = "Gold";
	public Gold(int id, String name, double balance) {
		super(id, name, balance,rank,commission,interest);
	}
}
