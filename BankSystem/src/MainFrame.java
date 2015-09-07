import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import java.awt.Font;
@SuppressWarnings("rawtypes")
public class MainFrame {
	private JFrame frame;
	private JLabel cNameLbl;
	private JList list, aList;
	private static DefaultListModel<Client> clientModel = new DefaultListModel<Client>();
	private static DefaultListModel<Log> logModel = new DefaultListModel<Log>();
	private DefaultListModel<Account> accountModel;
	private JScrollPane scrollPane;
	private JTextField cNameTxt;
	private JTextField cBalanceTxt;
	private JComboBox cRankCombo;
	private JPanel cListPanel;
	private JScrollPane cListScroll;
	private JList cList;
	private JButton btnDeleteClient;
	private JLabel lblClientId;
	private JTextField cIdTxt;
	private JLabel lblSearch;
	private static JLabel lblTotalBankBalance;
	private JLabel lblClientname;
	private JLabel clientId;
	private JLabel lblClientRank;
	private JLabel lblClientRankTxt;
	private JLabel lblClientBalance;
	private JLabel lblClientBalanceTxt;
	private JPanel panel;
	private JPanel crtAccountPane;
	private JLabel lblAccountBalance;
	private JTextField addAccountTxt;
	private JButton btnWithdraw;
	private JPanel panel_1;
	private JLabel lblWidthdrawAmount;
	private JTextField withdrawTxt;
	private JButton btnDeposit;
	private JPanel panel_2;
	private JLabel lblDepositAmount;
	private JTextField depositTxt;
	private JButton btnSetBalance;
	private JPanel panel_3;
	private JLabel label;
	private JTextField setBalanceTxt;
	private JButton btnRemoveAccount;
	private final static long updateTime = 86400000;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(updateTime);
						Bank.instance().updateClients();
						logModel.add(0, Logger.getLogs().getLast());
						lblTotalBankBalance.setText("Total Bank Balance: " + Bank.instance().getBalance());
					} catch (InterruptedException e) {}
				}
			}}).start();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
					window.frame.setTitle("Bank System");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1255, 743);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel bMng = new JPanel();
		bMng.setBounds(0, 0, 1232, 701);
		frame.getContentPane().add(bMng);
		bMng.setLayout(null);

		JPanel clientCrtPanel = new JPanel();																							//Client Creation panel
		clientCrtPanel.setBorder(new TitledBorder(null, "Client Creation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		clientCrtPanel.setBounds(10, 11, 282, 145);
		bMng.add(clientCrtPanel);
		clientCrtPanel.setLayout(null);

		cNameLbl = new JLabel("Client Name: ");
		cNameLbl.setBounds(10, 30, 82, 14);
		clientCrtPanel.add(cNameLbl);

		JButton btnCreateClient = new JButton("Create Client");
		btnCreateClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (!Pattern.matches("[a-zA-Z]+(\\s+[a-zA-Z]+)*",cNameTxt.getText())) 
						throw new Exception();
					Bank.instance().addClient(cNameTxt.getText(), Double.valueOf(cBalanceTxt.getText()), (Rank)cRankCombo.getSelectedItem());
					logModel.add(0, Logger.getLogs().getLast());
					Update.clientModel(cList, clientModel);
					//clientModel.addElement(Bank.instance().getClients().getLast());
					cNameTxt.setText("");
					cBalanceTxt.setText("");
					lblTotalBankBalance.setText("Total Bank Balance: " + Bank.instance().getBalance());
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Please fill all fields correctly");
				}
			}
		});
		btnCreateClient.setBounds(10, 114, 114, 23);
		clientCrtPanel.add(btnCreateClient);

		cNameTxt = new JTextField();
		cNameTxt.setBounds(102, 24, 160, 20);
		clientCrtPanel.add(cNameTxt);
		cNameTxt.setColumns(10);

		JLabel cBalanceLbl = new JLabel("Client Balance: ");
		cBalanceLbl.setBounds(10, 55, 92, 14);
		clientCrtPanel.add(cBalanceLbl);

		cBalanceTxt = new JTextField();
		cBalanceTxt.setColumns(10);
		cBalanceTxt.setBounds(102, 49, 160, 20);
		clientCrtPanel.add(cBalanceTxt);

		JLabel cRankLbl = new JLabel("Client Rank:");
		cRankLbl.setBounds(10, 84, 92, 14);
		clientCrtPanel.add(cRankLbl);

		cRankCombo = new JComboBox();
		cRankCombo.setModel(new DefaultComboBoxModel(Rank.values()));
		cRankCombo.setBounds(102, 80, 160, 23);
		clientCrtPanel.add(cRankCombo);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 438, 1202, 252);
		bMng.add(scrollPane);
		list = new JList(logModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		list.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Bank Log", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		cListPanel = new JPanel();
		cListPanel.setBorder(new TitledBorder(null, "Client List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cListPanel.setBounds(302, 11, 378, 418);
		bMng.add(cListPanel);
		cListPanel.setLayout(null);

		cListScroll = new JScrollPane();
		cListScroll.setBounds(10, 22, 358, 351);
		cListPanel.add(cListScroll);

		cList = new JList();
		cList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		cList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (arg0.getValueIsAdjusting()) {
					Update.accountModel(aList, accountModel,clientModel.getElementAt(cList.getSelectedIndex()));
					cIdTxt.setText(clientModel.getElementAt(cList.getSelectedIndex()).getId()+"");
					lblClientname.setText(clientModel.getElementAt(cList.getSelectedIndex()).getName());
					clientId.setText(clientModel.getElementAt(cList.getSelectedIndex()).getId()+"");
					lblClientRankTxt.setText(clientModel.getElementAt(cList.getSelectedIndex()).getRank());
					lblClientBalanceTxt.setText(clientModel.getElementAt(cList.getSelectedIndex()).getBalance()+"");
				}
			}
		});

		cList.setModel(clientModel);
		cListScroll.setViewportView(cList);


		btnDeleteClient = new JButton("Delete Client");
		btnDeleteClient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int tmpSelection=cList.getSelectedIndex();
				try{
					Bank.instance().removeClient(clientModel.getElementAt(tmpSelection).getId());
					Update.clientModel(cList, clientModel);
					logModel.add(0, Logger.getLogs().getLast());
					lblTotalBankBalance.setText("Total Bank Balance: " + Bank.instance().getBalance());
				}
				catch(Exception e3)
				{
					JOptionPane.showMessageDialog(null, "Please choose a client");
				}
			}
		});
		btnDeleteClient.setBounds(256, 384, 112, 23);
		cListPanel.add(btnDeleteClient);
		lblClientId = new JLabel("Client ID: ");
		lblClientId.setBounds(10, 388, 56, 14);
		cListPanel.add(lblClientId);

		cIdTxt = new JTextField();
		cIdTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					for (int i = 0; i <= Bank.instance().getClients().size(); i++) {
						if(Integer.valueOf(cIdTxt.getText()) == clientModel.getElementAt(i).getId()){
							cList.setSelectedIndex(i);
							break;
						}
					}
				}
				catch(Exception e) {}
			}
		});
		cIdTxt.setHorizontalAlignment(SwingConstants.CENTER);
		cIdTxt.setBounds(65, 386, 56, 20);
		cListPanel.add(cIdTxt);
		cIdTxt.setColumns(10);

		lblSearch = new JLabel("(Search)");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblSearch.setBounds(122, 388, 56, 14);
		cListPanel.add(lblSearch);

		lblTotalBankBalance = new JLabel("Total Bank Balance: 0");
		lblTotalBankBalance.setFont(new Font("Arial", Font.BOLD, 16));
		lblTotalBankBalance.setBounds(10, 385, 282, 42);
		bMng.add(lblTotalBankBalance);

		JPanel aListPane = new JPanel();
		aListPane.setBorder(new TitledBorder(null, "Accounts Managment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		aListPane.setBounds(703, 11, 519, 418);
		bMng.add(aListPane);
		aListPane.setLayout(null);

		JLabel lblClientName = new JLabel("Client Name:");
		lblClientName.setBounds(21, 26, 75, 14);
		aListPane.add(lblClientName);

		JLabel lblcId = new JLabel("Client ID: ");
		lblcId.setBounds(21, 48, 75, 14);
		aListPane.add(lblcId);

		lblClientname = new JLabel("");
		lblClientname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClientname.setBounds(111, 26, 106, 14);
		aListPane.add(lblClientname);

		clientId = new JLabel("");
		clientId.setFont(new Font("Tahoma", Font.BOLD, 12));
		clientId.setBounds(111, 48, 106, 14);
		aListPane.add(clientId);

		lblClientRank = new JLabel("Client Rank: ");
		lblClientRank.setBounds(21, 73, 75, 14);
		aListPane.add(lblClientRank);

		lblClientRankTxt = new JLabel("");
		lblClientRankTxt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClientRankTxt.setBounds(111, 73, 106, 14);
		aListPane.add(lblClientRankTxt);

		lblClientBalance = new JLabel("Client Balance:");
		lblClientBalance.setBounds(21, 98, 85, 14);
		aListPane.add(lblClientBalance);

		lblClientBalanceTxt = new JLabel("");
		lblClientBalanceTxt.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClientBalanceTxt.setBounds(111, 98, 106, 14);
		aListPane.add(lblClientBalanceTxt);

		btnRemoveAccount = new JButton("Remove Account");
		btnRemoveAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = accountModel.get(aList.getSelectedIndex()).GetId();
				clientModel.getElementAt(cList.getSelectedIndex()).removeAccount(id);
				Update.accountModel(aList, accountModel, clientModel.getElementAt(cList.getSelectedIndex()));
				logModel.add(0, Logger.getLogs().getLast());
			}
		});
		btnRemoveAccount.setBounds(356, 298, 131, 23);
		aListPane.add(btnRemoveAccount);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Account List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(246, 26, 263, 271);
		aListPane.add(panel);
		panel.setLayout(null);
		accountModel = new DefaultListModel<Account>();
		aList = new JList(accountModel);
		JScrollPane aScrollPane = new JScrollPane();
		aScrollPane.setBounds(3, 21, 258, 249);
		panel.add(aScrollPane);


		aScrollPane.setViewportView(aList);

		JButton crtAccountBtn = new JButton("Add Account");
		crtAccountBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					clientModel.getElementAt(cList.getSelectedIndex()).addAccount(Double.valueOf(addAccountTxt.getText()));
					logModel.add(0, Logger.getLogs().getLast());
					lblClientBalanceTxt.setText(clientModel.getElementAt(cList.getSelectedIndex()).getBalance()+"");
					Update.accountModel(aList, accountModel, clientModel.getElementAt(cList.getSelectedIndex()));
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(null, "Please choose a client");
				}
			}
		});
		crtAccountBtn.setBounds(11, 179, 106, 23);
		aListPane.add(crtAccountBtn);

		crtAccountPane = new JPanel();
		crtAccountPane.setBorder(new TitledBorder(null, "Add Account", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		crtAccountPane.setBounds(10, 123, 226, 53);
		aListPane.add(crtAccountPane);
		crtAccountPane.setLayout(null);

		lblAccountBalance = new JLabel("New Account Balance: ");
		lblAccountBalance.setBounds(10, 26, 133, 14);
		crtAccountPane.add(lblAccountBalance);

		addAccountTxt = new JTextField();
		addAccountTxt.setBounds(142, 23, 74, 20);
		crtAccountPane.add(addAccountTxt);
		addAccountTxt.setColumns(10);

		btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					clientModel.getElementAt(cList.getSelectedIndex()).withdraw(Double.valueOf(withdrawTxt.getText()));
					lblClientBalanceTxt.setText(clientModel.getElementAt(cList.getSelectedIndex()).getBalance()+"");
					lblTotalBankBalance.setText("Total Bank Balance: " + Bank.instance().getBalance());
					logModel.add(0, Logger.getLogs().getLast());
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, "Please choose a client");
				}
			}
		});
		btnWithdraw.setBounds(12, 269, 106, 23);
		aListPane.add(btnWithdraw);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Withdraw Cash to Client", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(11, 213, 226, 53);
		aListPane.add(panel_1);

		lblWidthdrawAmount = new JLabel("Widthdraw amount:");
		lblWidthdrawAmount.setBounds(10, 26, 133, 14);
		panel_1.add(lblWidthdrawAmount);

		withdrawTxt = new JTextField();
		withdrawTxt.setColumns(10);
		withdrawTxt.setBounds(142, 23, 74, 20);
		panel_1.add(withdrawTxt);

		btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					clientModel.getElementAt(cList.getSelectedIndex()).deposit(Double.valueOf(depositTxt.getText()));
					lblClientBalanceTxt.setText(clientModel.getElementAt(cList.getSelectedIndex()).getBalance()+"");
					lblTotalBankBalance.setText("Total Bank Balance: " + Bank.instance().getBalance());
					logModel.add(0, Logger.getLogs().getLast());
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null, "Please choose a client");
				}
			}
		});
		btnDeposit.setBounds(12, 358, 106, 23);
		aListPane.add(btnDeposit);

		panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Deposit Cash to Client", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.setBounds(11, 302, 226, 53);
		aListPane.add(panel_2);

		lblDepositAmount = new JLabel("Deposit amount:");
		lblDepositAmount.setBounds(10, 26, 133, 14);
		panel_2.add(lblDepositAmount);

		depositTxt = new JTextField();
		depositTxt.setColumns(10);
		depositTxt.setBounds(142, 23, 74, 20);
		panel_2.add(depositTxt);

		btnSetBalance = new JButton("Set Balance");
		btnSetBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int id = accountModel.getElementAt(aList.getSelectedIndex()).GetId();
					clientModel.get(cList.getSelectedIndex()).setAccountBalance(id, Double.valueOf(setBalanceTxt.getText()));
					Update.accountModel(aList, accountModel, clientModel.getElementAt(cList.getSelectedIndex()));
					lblClientBalanceTxt.setText(clientModel.getElementAt(cList.getSelectedIndex()).getBalance()+"");
				}
				catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null, "Please choose an account");
				}
			}
		});
		btnSetBalance.setBounds(270, 387, 106, 23);
		aListPane.add(btnSetBalance);

		panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Set new balance to selected account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(269, 331, 226, 53);
		aListPane.add(panel_3);

		label = new JLabel("New Account Balance: ");
		label.setBounds(10, 26, 133, 14);
		panel_3.add(label);

		setBalanceTxt = new JTextField();
		setBalanceTxt.setColumns(10);
		setBalanceTxt.setBounds(142, 23, 74, 20);
		panel_3.add(setBalanceTxt);


	}

}
