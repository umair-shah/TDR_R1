package TermDeposit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utilities.utility;

public class CustomerTDRService {
	public Object[][] AccountTDR(String accountNo)
	{
		String brnCd=accountNo.substring(0,4);
		String accType=accountNo.substring(4,8);
		String customerNo=accountNo.substring(8,14);
		String runNo=accountNo.substring(14,16);
		String chkDigit=accountNo.substring(16,17);

		Connection lcl_conn_dt = utility.db_conn();
		ResultSet PreMatureDeals=null;
		
		String tdrAppQuery = "Select B.brn_cd||lpad(At.acc_type_cd,4,'0')||C.Customer_no ||lpad(acc.run_no,2,'0') || acc.Check_digit  " +
				"As AccountNo ,tdr.amount,lpad(tdr.application_id,5,'0')||'/'||C.Customer_No||'/'||Year(tdr.application_date)  As ApplicationNo ,P.Desc " +
				"As Tenure ,M.Desc  As ActionatMaturity, lpad(td.deal_id,6,'0')||'/'||b.brn_cd||'/'|| lpad(Day(td.Deal_date), 2, '0')||'/'||Year(td.deal_date) As Deal_No ," +
				"tdr.maturity_date,tdr.application_date, M.Desc AS MaturityAction,ts.Desc AS ApplicationStatus from TDR_Application tdr inner join Account_tl acc on tdr.account_id= acc.account_id " +
				"inner join Account_Type At on acc.Acc_type_id=At.Acc_type_id inner join Branch_tl B on acc.brn_ID = B.brn_ID inner join " +
				"Customer C on C.Customer_ID= acc.Customer_ID inner join TDR_product " +
				"P on tdr.Product_ID = P.ID inner join Maturity_action M on m.ID=tdr.maturity_action " +
				"inner join tdr_deal td on td.tdr_app_id =tdr.application_id inner join tdr_deal_status ts on td.deal_status=ts.ID "+ 
				"where B.brn_cd='"+brnCd+"' and At.acc_type_cd ='"+accType+"' and C.Customer_no ='"+customerNo+"' and acc.run_no='"+runNo+"' and acc.Check_digit='"+chkDigit+"'";
		Object[][] data=null;
		java.sql.Statement lcl_stmt;
		try {
			 lcl_stmt= lcl_conn_dt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			 PreMatureDeals = lcl_stmt.executeQuery(tdrAppQuery);
			 
			 PreMatureDeals.last();
	         int rowCount = PreMatureDeals.getRow();
	         PreMatureDeals.beforeFirst();

	         // Assuming two columns: name and age
	         int columnCount = 8;

	         // Declare and initialize a 2D array
	         data = new Object[rowCount][columnCount];

	         // Populate the array with data from the ResultSet
	         int rowIndex = 0;
	         while (PreMatureDeals.next()) {   
	        	
	        	 data[rowIndex][0] = PreMatureDeals.getString("Deal_No");  
	        	 data[rowIndex][1] = PreMatureDeals.getString("ApplicationNo");
	        	 data[rowIndex][2] = PreMatureDeals.getFloat("Amount");
	        	 data[rowIndex][3] = PreMatureDeals.getString("ApplicationStatus");
	        	 data[rowIndex][4] = PreMatureDeals.getString("application_date");
	             data[rowIndex][5] = PreMatureDeals.getString("Tenure");
	             data[rowIndex][6] = PreMatureDeals.getString("ActionatMaturity");
	             data[rowIndex][7] = PreMatureDeals.getString("Maturity_date");  
	             rowIndex++;
	         }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return data;
	}
	
	public Object[][] getDealTransactions(String deal)
	{
//		String accountSearchQuery = "Select B.brn_cd||lpad(At.acc_type_cd,4,'0')||Cus.Customer_no ||lpad(acc.run_no,2,'0') || acc.Check_digit As AccountNo, At.acc_type_cd from Account_tl acc inner join Branch_tl B on acc.brn_ID= B.brn_ID inner join Customer Cus on acc.Customer_ID= Cus.Customer_ID  inner join Account_type At on acc.Acc_type_ID = At.Acc_type_ID " +
//				" WHERE acc.Account_ID in ('"+TDADTO.GetAccountID()+"','"+TDADTO.GetTDRAccountID()+"')";
		String tdrVoucherQuery = "SELECT V.VOUCHER_ID,V.voucher_date,vt.voucher_desc FROM Voucher V INNER JOIN TDR_Deal_Voucher TDV ON TDV.VOUCHER_ID = V.VOUCHER_ID  inner join voucher_type vt on vt.voucher_Type_id = v.voucher_type_id  " +
				"WHERE lpad(DEAL_ID,6,'0') = '"+deal.substring(0,6)+"'";
		
		Connection lcl_conn_dt = utility.db_conn();		
		ResultSet TDRVouchersRS = null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = lcl_conn_dt.prepareStatement(tdrVoucherQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			TDRVouchersRS = preparedStatement.executeQuery();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Object[][] data = null;
		int rowIndex=0;
		String [] transactionHeader = {"Transaction ID","Account No","Account_title","Debit","Credit"};
		List<Object> linespace =new ArrayList<>();
		for(int i=0;i<6;i++)
		{
			linespace.add(" ");
		}
		List<List<Object>> grid= new ArrayList<>();
		try {	
			while(TDRVouchersRS.next())
			{

				List<Object> voucherdet =new ArrayList<>();
				voucherdet.add("Voucher No:"+TDRVouchersRS.getString("VOUCHER_ID"));
				voucherdet.add("Type :"+TDRVouchersRS.getString("voucher_desc"));
				voucherdet.add("Date :"+TDRVouchersRS.getString("Voucher_date"));
				for(int i=0;i<3;i++)
				{
					voucherdet.add(" ");
				}
				grid.add(voucherdet);
				
				List<Object> trnhead=new ArrayList<>();
				for(String header : transactionHeader)
				{
					trnhead.add(header);
				}
			
				grid.add(trnhead);
					
				
				String tdrTransactionQuery = "SELECT * FROM Transaction_TL WHERE VOUCHER_ID = ?";
				PreparedStatement preparedStatement1 = lcl_conn_dt.prepareStatement(tdrTransactionQuery);
				preparedStatement1.setInt(1, TDRVouchersRS.getInt("VOUCHER_ID"));
				ResultSet voucherTransactionRS = preparedStatement1.executeQuery();
				while(voucherTransactionRS.next())
				{
					List<Object> trndet=new ArrayList<>();
					trndet.add(voucherTransactionRS.getString("transaction_id"));
					if(voucherTransactionRS.getString("CUS_ACCOUNT_ID") != null)
					{
						String accountSearchQuery = "Select B.brn_cd||lpad(At.acc_type_cd,4,'0')||Cus.Customer_no ||lpad(acc.run_no,2,'0') || acc.Check_digit As AccountNo, acc.TITLE" +
								" From Account_tl acc inner join Branch_tl B on acc.brn_ID= B.brn_ID inner join Customer Cus on acc.Customer_ID= Cus.Customer_ID  inner join Account_type At on acc.Acc_type_ID = At.Acc_type_ID" +
								" WHERE acc.Account_ID = '" +voucherTransactionRS.getString("CUS_ACCOUNT_ID") +"'";
						ResultSet AccountRS=null;
						try 
						{
							java.sql.Statement lcl_stmt;
							 lcl_stmt= lcl_conn_dt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							 AccountRS = lcl_stmt.executeQuery(accountSearchQuery);
						} 
						catch (SQLException e1) 
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (AccountRS.next())
						{
							trndet.add(AccountRS.getString("AccountNo"));
							trndet.add(AccountRS.getString("Title"));
						}
					}
					else if(voucherTransactionRS.getString("INTERNAL_ACCOUNT_ID") != null)
					{
						String accountSearchQuery = "Select IDEN || lpad(Main_HD,3,'0')|| lpad(Sub_HD_1,2,'0') || lpad(Sub_Hd_2,2,'0') || lpad(Run_No,4,'0') || Check_Digit As AccountNo, TITLE" +
								" From internal_account_tl"+
								" WHERE INTERNAL_ACCOUNT_ID = '" + voucherTransactionRS.getString("INTERNAL_ACCOUNT_ID") + "'";
						ResultSet AccountRS=null;
						try 
						{
							java.sql.Statement lcl_stmt;
							 lcl_stmt= lcl_conn_dt.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
							 AccountRS = lcl_stmt.executeQuery(accountSearchQuery);
						} 
						catch (SQLException e1) 
						{
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if (AccountRS.next())
						{
							trndet.add(AccountRS.getString("AccountNo"));
							trndet.add(AccountRS.getString("Title"));
						}
					}
					//data[rowIndex][3] = voucherTransactionRS.getFloat("Amount");
					if(voucherTransactionRS.getInt("Trans_Type_Id")== 1)
			       	 {
						trndet.add(String.format("%.2f",voucherTransactionRS.getFloat("Amount")));
						trndet.add(" ");
			       	 }
			       	 else
			       	 {
			       		trndet.add(" ");
			       		trndet.add(String.format("%.2f",voucherTransactionRS.getFloat("Amount")));
			       	 }
		           grid.add(trndet);
		           
				}
				
				grid.add(linespace);		
			}
			
			data=convertTo2DArray(grid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
	private static Object[][] convertTo2DArray(List<List<Object>> dynamicArray) {
        int maxColumns = 0;
        for (List<Object> row : dynamicArray) {
            maxColumns = Math.max(maxColumns, row.size());
        }

        Object[][] staticArray = new Object[dynamicArray.size()][maxColumns];
        for (int i = 0; i < dynamicArray.size(); i++) {
            List<Object> row = dynamicArray.get(i);
            for (int j = 0; j < row.size(); j++) {
                staticArray[i][j] = row.get(j);
            }
        }

        return staticArray;
    }
}
