package com.javagyanmantra.xml.editor;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

/**
 * Hello world!
 *
 */
public class MessageGenerator {

	public String generateMessage() throws XPathExpressionException {
		// giving client response to InputStream
		InputSource source = new InputSource(new StringReader(
				getClinetResponse()));
		// Create XPath
		XPath xpath = XPathFactory.newInstance().newXPath();
		// Specify Root Element where we need to start parsing
		Object policy = xpath.evaluate("//POLICYDETAILS", source,
				XPathConstants.NODE);
		// provide the tag which field you want to read
		String name = xpath.evaluate("POLICYHOLDERNAME", policy);
		String dueDate = xpath.evaluate("PREMIUMDUEDATE", policy);
		String amount = xpath.evaluate("PREMIUMPAYABLE", policy);
		return "Hello " + name + " your premium due date is " + dueDate
				+ ". The amount due is " + amount
				+ ". Looking forward to hear from you again. Good Day.";
	}

	// assume this is your client call and you are getting below response
	private String getClinetResponse() {
		String response = "<POLICYDETAILS>" + "<STATUS>IF</STATUS>"
				+ "<POLICYNO>0000010</POLICYNO>"
				+ "<POLICYHOLDERNAME>Testing Payment Gatway</POLICYHOLDERNAME>"
				+ "<PREMIUMDUEDATE>2016-06-01</PREMIUMDUEDATE>"
				+ "<PREMIUMPAYABLE>2.00</PREMIUMPAYABLE>"
				+ "<MINPREMIUMPAYABLE>2.00</MINPREMIUMPAYABLE>"
				+ "<MAXPREMIUMPAYABLE>2.00</MAXPREMIUMPAYABLE>"
				+ "<BASEPREMIUM>2.00</BASEPREMIUM>"
				+ "<RIDERPREMIUM>0.00</RIDERPREMIUM>"
				+ "<SERVICETAX>0.00</SERVICETAX>"
				+ "<AMOUNTAVAILABLE>0.00</AMOUNTAVAILABLE>"
				+ "<LATEFEE>0.00</LATEFEE>" + "<REASON>NA</REASON>"
				+ "</POLICYDETAILS>";
		return response;
	}

	public static void main(String[] args) throws XPathExpressionException {

		MessageGenerator generator = new MessageGenerator();
		System.out.println(generator.generateMessage());
	}
}
