package com.kalpesh.devil;

import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Text;

public class Runner {

	public static void main(String[] args) {
		try {
//			writeInDocxFile();
			readFromDocxFile();
		} catch (Docx4JException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private static void writeInDocxFile(StringBuilder myNewString) throws InvalidFormatException, Docx4JException {
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
//		wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "Welcome to CodeDictator");
//		wordMLPackage.getMainDocumentPart().addParagraphOfText("This is Demo for docx file read and write");
		wordMLPackage.getMainDocumentPart().addParagraphOfText(new String(myNewString));
		wordMLPackage.save(Constants.PATH);
	}

	private static void readFromDocxFile() throws Docx4JException, JAXBException {
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(Constants.PATH);
		MainDocumentPart mainDocumentPart = wordMLPackage.getMainDocumentPart();
		String textNodesXPath = "//w:t";
		List<Object> textNodes = mainDocumentPart.getJAXBNodesViaXPath(textNodesXPath, true);
		String textValue = "";
		for (Object obj : textNodes) {
			Text text = (Text) ((JAXBElement) obj).getValue();
			textValue = text.getValue();
			// //Read from file on console
			System.out.println(textValue);
			// //Logic for write new line after keyword found:
			seperateLineIfKeywordFound(textValue);
		}
	}

	private static void seperateLineIfKeywordFound(String textValue) throws InvalidFormatException, Docx4JException {
		String[] str = textValue.split(" ");
		StringBuilder myNewString = new StringBuilder();
		for (String myStr : str) {
			if (myStr.equals("Kalpesh")) {
				myNewString = myNewString.append("\n");
			}
			myNewString = myNewString.append(myStr + " ");
		}
		System.out.println(myNewString);
		writeInDocxFile(myNewString);
	}
}
