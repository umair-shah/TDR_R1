package TermDeposit;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

import Utilities.MaxLengthNumericField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Dimension;

public class Signature {
	public Signature()
	{
		CreateWindow();
	}
	public void CreateWindow()
	{
		JDialog signFrame = new JDialog();
		signFrame.setSize(new Dimension(300, 300));
		signFrame.setTitle("Term Deposit Application");
		signFrame.setResizable(false);
		signFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel signPanel = new JPanel();
		signPanel.setBackground(new Color(143, 188, 143));
		signPanel.setLayout(null);
		
		signFrame.setContentPane(signPanel);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(44, 36, 220, 204);
		signPanel.add(lblNewLabel);
		
		JLabel lblSignature = new JLabel("Signature ");
		lblSignature.setBounds(34, 11, 105, 14);
		signPanel.add(lblSignature);
		signFrame.setLocationRelativeTo(null);
		signFrame.setModal(true);
		signFrame.setVisible(true);
		
	}
public static void main(String[] args) {
		
	Signature sa = new Signature();
		// TODO Auto-generated method stub

	}
}
