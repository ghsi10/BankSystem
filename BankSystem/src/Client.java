import java.util.Iterator;
import java.util.LinkedList;

public abstract class Client {
	private int id, tempID;
	private String rank, name;
	private double interest, balance, sum, commission;
	private LinkedList<Account> accounts;
	private LinkedList<Integer> deletedAccounts;
	private int accountId;
	public Client(int id, String name ,double balance, String rank, double commission, double interest) {
		accountId = 0;
		this.id = id;
		this.name = name;
		this.balance = balance;
		this.rank = rank;
		this.interest = interest;
		this.commission = commission;
		accounts = new LinkedList<Account>();
		deletedAccounts = new LinkedList<Integer>();
	}
	public String getRank()
	{
		return rank;
	}
	public int getAccountId()
	{
		if(deletedAccounts.size()==0)
			return accountId++;

		tempID = deletedAccounts.getFirst();
		deletedAccounts.removeFirst();

		return tempID;
	}
	public int getId()
	{
		return id;
	}
	public String getName()
	{
		return name;
	}
	public double getBalance()
	{
		return balance;
	}
	public double getTotalBalance()
	{	sum = 0;
	Iterator<Account> iter = accounts.iterator();
	while(iter.hasNext())
		sum += iter.next().getBalance();

	return balance + sum;
	}
	public LinkedList<Account> getAccounts()
	{
		return accounts;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public void setBalance(int value)
	{ 									//add money removed or added to bank
		balance = value;
	}
	public double getFortune()
	{
		sum = 0;
		sum += balance;
		Iterator<Account> iter = accounts.iterator();
		while(iter.hasNext())
			sum+=iter.next().getBalance();
		return sum;
	}
	public void addAccount(double balance)
	{
		this.balance -= balance;
		Account a = new Account(getAccountId(), balance);
		accounts.add(a);
		Logger.log(name, new Time(), getId(), Desc.AccountOpen, a.getBalance());

	}
	public void removeAccount(int id)
	{
		Iterator<Account> iter = accounts.iterator();
		Account a;
		while(iter.hasNext())
		{
			a = iter.next();
			if(a.GetId() == id)
			{
				balance += a.getBalance();
				Logger.log(name, new Time(), getId(), Desc.AccountClosed, a.getBalance());
				iter.remove();
				deletedAccounts.add(id);
				break;
			}
		}
	}

	public String toString()
	{
		return "ID: " + getId() + "  Name: \"" + name + "\"  Rank: " + rank;
	}
	public void deposit(double value)
	{
		if(value <= 0)
			return;									// bank total changes with commission
		Bank.instance().addToBalance(value+value*commission);
		balance += value-value*commission;
		Logger.log(name, new Time(), getId(), Desc.Deposit, value);

	}
	public void withdraw(double value)
	{
		if(value <= 0)
			return;									// bank total changes with commission
		Bank.instance().addToBalance(value*commission-value);
		balance -= value+value*commission;
		Logger.log(name, new Time(), getId(), Desc.Withdraw, value);
	}
	public void setAccountBalance(int id, int value)
	{
		Iterator<Account> iter =  accounts.iterator();
		Account a;
		while(iter.hasNext())
		{
			a = iter.next();
			if(a.GetId() == id){
				balance += a.getBalance();
				balance -= value;
				a.setBalance(value);
				break;
			}
		}
	}
	public void updateAccounts()
	{
		Iterator<Account> iter = accounts.iterator();
		Account a;
		double tmpIntrest, accountBalance;
		while(iter.hasNext())
		{
			a = iter.next();
			accountBalance=a.getBalance();
			tmpIntrest = accountBalance*interest;
			Bank.instance().addToBalance(tmpIntrest*-1);
			a.setBalance(accountBalance + tmpIntrest);
		}
	}
	public void setAccountBalance(int id, double value)
	{
		Iterator<Account> iter = accounts.iterator();
		Account a;
		while(iter.hasNext())
		{
			a = iter.next();
			if(a.GetId() == id)
			{
				balance += a.getBalance();
				balance -= value;
				a.setBalance(value);
			}

		}
	}
	public boolean equals(Object other)
	{
		if (other instanceof Client && id==((Client)other).id)
			return true;
		return false;
	}
}
