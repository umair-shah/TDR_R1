package Utilities;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
	public class PDF {
	    public static void main(String[] args) {
	    	PDDocument document =null;
	        try {
	        	document = new PDDocument();
	            PDPage page = new PDPage();
	            document.addPage(page);

	            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
	                PDFont font = PDType1Font.HELVETICA_BOLD;

	                // Sample data - replace with your actual data
	                String applicationId = "12345";
	                String customerName = "John Doe";
	                String applicationDate = "2024-01-11";
	                String maturityDate = "2026-01-11";
	                String amount = "$1000";
	                String dealId = "D123";
	                String dealDate = "2024-01-10";

	                // Writing text
	                contentStream.beginText();
	                contentStream.setFont(font, 12);
	                contentStream.newLineAtOffset(25, 700);
	                contentStream.showText("Application ID: " + applicationId);
	                contentStream.newLineAtOffset(0, -15);
	                contentStream.showText("Customer Name: " + customerName);
	                contentStream.newLineAtOffset(0, -15);
	                contentStream.showText("Application Date: " + applicationDate);
	                
	                contentStream.setFont(font, 12);
	                contentStream.newLineAtOffset(25, 700);
	                contentStream.showText("Application ID: " + applicationId);
	                contentStream.newLineAtOffset(0, -15);
	                contentStream.showText("Customer Name: " + customerName);
	                contentStream.newLineAtOffset(0, -15);
	                contentStream.showText("Application Date: " + applicationDate);
	                // Add other fields similarly
	                contentStream.endText();

	                 //Example transaction data
	                List<Transaction> transactions = new ArrayList<Transaction> ();
	                transactions.add(new Transaction("T1", "V1", "$200", "Account1"));
	                transactions.add(new Transaction("T2", "V2", "$300", "Account2"));

	                // Draw a table for transactions
	                drawTransactionsTable(contentStream, transactions);
	
	                
	            }

	            document.save("pdf_document.pdf");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        
	    }
	    private static void drawTransactionsTable(PDPageContentStream contentStream, List<Transaction> transactions) throws IOException {
	    	contentStream.beginText();
	    	contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
	    	contentStream.newLineAtOffset(25, 600);
	    	contentStream.showText("Transaction ID Voucher ID Amount Account");
	    	contentStream.setFont(PDType1Font.HELVETICA, 10);
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}	    	for (Transaction transaction : transactions) {
		    	contentStream.newLineAtOffset(0, -15);
		    	contentStream.showText(transaction.toString());
		    	}
	    	
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}	    	for (Transaction transaction : transactions) {
		    	contentStream.newLineAtOffset(0, -15);
		    	contentStream.showText(transaction.toString());
		    	}
	    	
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}	    	for (Transaction transaction : transactions) {
		    	contentStream.newLineAtOffset(0, -15);
		    	contentStream.showText(transaction.toString());
		    	}
	    	
	    	for (Transaction transaction : transactions) {
	    	contentStream.newLineAtOffset(0, -15);
	    	contentStream.showText(transaction.toString());
	    	}
	    	
	
	    	contentStream.endText();
	    	}
	    static class Transaction {
	    	String transactionId;
	    	String voucherId;
	    	String amount;
	    	String account;
	    	public Transaction(String transactionId, String voucherId, String amount, String account) {
	    	this.transactionId = transactionId;
	    	this.voucherId = voucherId;
	    	this.amount = amount;
	    	this.account = account;
	    	}
	    	@Override
	    	public String toString() {
	    	return transactionId + " " + voucherId + " " + amount + " " + account;
	    	}
	}
}
	

