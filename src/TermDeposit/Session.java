package TermDeposit;

public class Session {
	private static String userName;
	private static int userRoleId;
	private static String branchCode;
	private static String branchDate;
	private static String branchName;
	
	public static void SetUserName(String newUserName)
	{
		userName = newUserName;
	}
	
	public static void SetUserRoleId(int newUserRoleId)
	{
		userRoleId = newUserRoleId;
	}
	
	public static void SetBranchCode(String newBranchCode)
	{
		branchCode = newBranchCode;
	}
	public static void SetBranchName(String nbranchName)
	{
		branchName = nbranchName;
	}
	
	public static void SetBranchDate(String newBranchDate)
	{
		branchDate = newBranchDate;
	}
	
	public static String GetUserName()
	{
		return userName;
	}
	
	public static int getUserRoleId()
	{
		return userRoleId;
	}
	
	public static String GetBranchCode()
	{
		return branchCode;
	}
	
	public static String GetBranchDate()
	{
		return branchDate;
	}
	
	
	public static void clearSession()
	{
		userName = null;
	}
	public static String GetBranchName()
	{
		return branchName;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
