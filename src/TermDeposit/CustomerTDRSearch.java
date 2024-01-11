package TermDeposit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Utilities.MaxLengthNumericField;

public class CustomerTDRSearch {
private MaxLengthNumericField accountNoField;
	
	public CustomerTDRSearch()
	{
		CreateWindow();
	}
	
	public void CreateWindow()
	{
		final JDialog frame = new JDialog();
		frame.setTitle("Search Account");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(143, 188, 143));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		accountNoField = new MaxLengthNumericField(17);
		accountNoField.setBounds(120, 30, 197, 20);
		panel.add(accountNoField);
		accountNoField.setColumns(10);
		
		JLabel lblAccountNo = new JLabel("Account No:");
		lblAccountNo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccountNo.setBounds(31, 31, 100, 14);
		panel.add(lblAccountNo);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(28, 76, 89, 23);
		panel.add(searchButton);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(228, 76, 89, 23);
		panel.add(exitButton);
		panel.repaint();
		
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent searchButtonClicked) {
				String accountNo = accountNoField.getText().toString();
				if(accountNo.isEmpty())
				{
					JOptionPane.showMessageDialog(frame, "Field account No is required!","Validation Error",JOptionPane.ERROR_MESSAGE);
					
				}
				else if(accountNo.length()<17)
				{
					JOptionPane.showMessageDialog(frame, "Invalid Account Number Format","Validation Error",JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					SearchAccountService searchAccountService = new SearchAccountService();
					int count = searchAccountService.CheckTDRAgainstAccount(accountNo);
					if(count <= 0)
					{
						JOptionPane.showMessageDialog(frame, "Account Not Exist","Invalid Account No",JOptionPane.ERROR_MESSAGE);
					}	
					else
					{
						frame.dispose();
						CustomerTDRService tdr = new CustomerTDRService();
						Object [][] data= tdr.AccountTDR(accountNo);
						tdrApplications(data);
						
					}
				}
			}
		});
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent exitButtonPressed) {
				frame.dispose();
			}
		});
		
		frame.setContentPane(panel);
		frame.setSize(368,161);
		frame.setLocationRelativeTo(null);
		frame.setModal(true);
		frame.setVisible(true);
}
	public void tdrApplications(Object[][] data)
	{
		final JDialog frame = new JDialog();
		frame.setTitle("Authorize Premature Term Deposit");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(834,362);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(143, 188, 143));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);


		String[] columnNames = { "Deal No", "Application No","Amount","ApplicationStatus", "Tenure", "Action at Maturity","Maturity_date"};
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames){
			 public boolean isCellEditable(int row, int column)
			 {
			     return false;
			 }
			};
			
		JTable termDepositTable = new JTable(model);
		termDepositTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane jScrollPane = new JScrollPane(termDepositTable);
		jScrollPane.setForeground(Color.BLACK);
		jScrollPane.setLocation(31, 44);
		//termDepositTable.setBounds(142, 196, 202, -91);
		panel.add(jScrollPane);
		jScrollPane.setSize(771,173);
		
		termDepositTable.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					//JOptionPane.showMessageDialog(frame, "Amount is required!","row = "+table.getValueAt(row, 0).toString() ,JOptionPane.ERROR_MESSAGE);
		        	CustomerTDRService tdr = new CustomerTDRService();
		        	String DealNo= table.getValueAt(row, 0).toString();
		        	DealNo = DealNo.replace("/","");
		        	Object[][] data = tdr.getDealTransactions(DealNo);
		        	displayTransactions(data);
		        	
		        	
		        	
		        }
		    }
		});
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(690, 268, 89, 23);
		panel.add(btnBack);
		jScrollPane.setVisible(true);
		
		
		panel.repaint();
		frame.setContentPane(panel);
		frame.setLocationRelativeTo(null);
		frame.setModal(true);
		frame.setVisible(true);
	}
	
	
	public void displayTransactions(Object[][] data)
	{
		final JDialog frame = new JDialog();
		frame.setTitle("Authorize Premature Term Deposit");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(834,362);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(143, 188, 143));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		String[] columnNames = {"Transaction ID","Account No","Account_title","Debit","Credit"};
		DefaultTableModel model = new DefaultTableModel(data,columnNames){
			 public boolean isCellEditable(int row, int column)
			 {
			     return false;
			 }
			};
			
		JTable termDepositTable = new JTable(model);
		termDepositTable.getTableHeader().setReorderingAllowed(false);
		JScrollPane jScrollPane = new JScrollPane(termDepositTable);
		jScrollPane.setForeground(Color.BLACK);
		jScrollPane.setLocation(31, 44);
		//termDepositTable.setBounds(142, 196, 202, -91);
		panel.add(jScrollPane);
		jScrollPane.setSize(771,173);
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnBack.setBounds(690, 268, 89, 23);
		panel.add(btnBack);
		jScrollPane.setVisible(true);
		
		
		panel.repaint();
		frame.setContentPane(panel);
		frame.setLocationRelativeTo(null);
		frame.setModal(true);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CustomerTDRSearch sa = new CustomerTDRSearch();
	}
}

