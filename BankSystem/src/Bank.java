
import java.util.Iterator;
import java.util.LinkedList;

public class Bank {
	private static Bank bank=null;
	private int tempID;
	private double balance;
	private static int clientId;
	private LinkedList<Client> clients;
	private LinkedList<Integer> deletedClients;
	private final String bankName = "Bank";

	private Bank() {
		clientId = 0;
		clients = new LinkedList<Client>();
		deletedClients = new LinkedList<Integer>();
	}
	public static Bank instance() {
		if (bank==null)
			bank=new Bank();
		return bank;
	}
	public void addToBalance(double value)
	{
		balance += value;
	}
	public LinkedList<Client> getClients()
	{
		return clients;
	}
	public int getClientId()
	{
		if(deletedClients.size()==0)
			return clientId++;

		tempID = deletedClients.getFirst();
		deletedClients.removeFirst();
		return tempID;
	}
	public double getBalance()
	{
		return balance;
	}
	public void addClient(String name, double balance, Rank rank)
	{
		switch(rank.toString()){
		case "Gold":
			Client c = new Gold(getClientId(), name, balance);
			clients.add(c);
			this.balance += c.getTotalBalance();
			Logger.log(bankName, new Time(), c.getId(), Desc.ClientAdd, balance);
			break;
		case "Regular":
			Client c1 = new Regular(getClientId(), name, balance);
			clients.add(c1);
			this.balance += c1.getTotalBalance();
			Logger.log(bankName, new Time(), c1.getId(), Desc.ClientAdd, balance);
			break;
		case "Platinum":
			Client c2 = new Platinum(getClientId(), name, balance);
			clients.add(c2);
			this.balance += c2.getTotalBalance();
			Logger.log(bankName, new Time(), c2.getId(), Desc.ClientAdd, balance);
			break;
		}
	}
	public void removeClient(int id)
	{
		Iterator<Client> iter = clients.iterator();
		Client c;
		while(iter.hasNext())
		{
			c = iter.next();
			if(c.getId() == id)
			{
				balance -= c.getTotalBalance();
				deletedClients.add(c.getId());
				Logger.log(bankName, new Time(), id, Desc.ClientRemove, c.getTotalBalance());
				iter.remove();
				break;
			}

		}
	}
	public LinkedList<Log> viewLogs()
	{
		return Logger.getLogs();
	}
	public void updateClients()
	{
		double tmpBalance = balance;
		Iterator<Client> iter = clients.iterator();
		while(iter.hasNext())
			iter.next().updateAccounts();
		Logger.log(bankName, new Time(), -1, Desc.BankUpdate, (balance - tmpBalance));
	}
}
