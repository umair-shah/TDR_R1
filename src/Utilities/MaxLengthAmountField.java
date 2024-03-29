package Utilities;

import javax.swing.text.AttributeSet;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class MaxLengthAmountField extends JTextField {
	
	public MaxLengthAmountField(int columns, final int digitsBeforeDecimal, final int digitsAfterDecimal) {
	    super(columns);

	    ((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {
	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
	            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());

	            String regex = "^\\d{0," + digitsBeforeDecimal + "}(\\.\\d{0," + digitsAfterDecimal + "})?$";
	            if ((currentText + text).matches(regex)) {
	                super.replace(fb, offset, length, text, attrs);
	            }
	        }
	    });
	}
  

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
