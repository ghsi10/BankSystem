
public class Regular extends Client {
	private static double commission = 0.03, interest = 0.001;
	private static String rank = "Regular";
	public Regular(int id, String name, double balance) {
		super(id, name, balance,rank,commission,interest);
	}
}
