
public enum Desc {
	ClientAdd("Client Added"), ClientRemove("Client Removed"), Withdraw("Withdraw"), Deposit("Deposit"),
	AccountOpen("Account Opened"),AccountClosed("Account Closed"), BankUpdate("Bank Auto Update");
	private String desc;
	private Desc(String desc) {
		this.desc = desc;
	}
	public String getDesc()
	{
		return desc;
	}
}
