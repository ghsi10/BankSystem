
public enum Rank {

	Regular("Regular"), Gold("Gold"), Platinum("Platinum");

	private String rank;

	private Rank(String rank) {
		this.rank = rank;
	}
	public String toString()
	{
		return rank;
	}
}
