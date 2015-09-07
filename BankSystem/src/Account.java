
public class Account {

	private int id;
	private double balance;

	public Account(int id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	public int GetId()
	{
		return id;
	}
	public double getBalance()
	{
		return balance;
	}
	public void setBalance(double value)
	{
		balance = value;
	}
	public String toString()
	{
		return "ID: " + id + " Balance: " + balance;
	}

}
