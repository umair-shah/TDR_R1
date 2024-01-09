package Utilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import TermDeposit.LoginUserDTO;
import TermDeposit.UploadFile;
import javax.swing.JTextField;

public class Account_Signature {	
	UploadFile fileHandler;
	private JTextField account_id;
	public Account_Signature()
	{
		fileHandler= new UploadFile();
		CreateWindow();
	}
	
	public void CreateWindow()
	{
		final JDialog frame = new JDialog();
		frame.setTitle("Upload Signature");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(143, 188, 143));
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnSelectFile = new JButton("Select Sign");
		btnSelectFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fileHandler.selectFile();
			}
		});
		btnSelectFile.setBounds(105, 113, 89, 23);
		panel.add(btnSelectFile);
		final JLabel uploadStatus = new JLabel("");
		uploadStatus.setBounds(113, 164, 239, 14);
		panel.add(uploadStatus);
		
		JButton btnUploadSign = new JButton("Upload Sign");
		
		btnUploadSign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				byte[] filedata=null;
				File file = new File(fileHandler.path);
				try {
					filedata = fileHandler.readFileData(file);
					
					String insertSign = "update account_tl set signature = ? where account_id = ?" ;
					Connection lcl_conn_dt = utility.db_conn();
					PreparedStatement preparedStatement1 = lcl_conn_dt.prepareStatement(insertSign);
					preparedStatement1.setBytes(1,filedata );
		        	preparedStatement1.setString(2, account_id.getText().toString());
					
		        	int result = preparedStatement1.executeUpdate();
					if(result > 0)
					{
						
						uploadStatus.setText("Signature Uploaded");
					}

					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		btnUploadSign.setBounds(263, 113, 89, 23);
		panel.add(btnUploadSign);
		
		account_id = new JTextField();
		account_id.setBounds(189, 58, 163, 20);
		panel.add(account_id);
		account_id.setColumns(10);
		
		JLabel lblAccountNo = new JLabel("Account ID");
		lblAccountNo.setBounds(104, 61, 75, 14);
		panel.add(lblAccountNo);
		
		
		
		frame.setContentPane(panel);
		frame.setSize(400,200);
		frame.setLocationRelativeTo(null);
		frame.setModal(true);
		frame.setVisible(true);


	}
	 
	public static void main(String[] args) {
		
		Account_Signature accountSign = new Account_Signature();
		// TODO Auto-generated method stub

	}
}
