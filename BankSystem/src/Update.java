import javax.swing.DefaultListModel;
import javax.swing.JList;
import java.util.Iterator;
public class Update {

	public static void clientModel(JList<Client> aList, DefaultListModel<Client> model) {

		DefaultListModel<Client> listModel = (DefaultListModel<Client>) aList.getModel();
		listModel.removeAllElements();
		model = listModel;
		Iterator<Client> iter = Bank.instance().getClients().iterator();
		while (iter.hasNext()) {
			model.addElement(iter.next());
		}
	}
	public static void accountModel(JList<Account> aList, DefaultListModel<Account> model, Client client) {

		DefaultListModel<Account> listModel = (DefaultListModel<Account>) aList.getModel();
		listModel.removeAllElements();
		model = listModel;
		Iterator<Account> iter = client.getAccounts().iterator();
		while (iter.hasNext()) {
			model.addElement(iter.next());
		}
	}
}
