package scripts;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
public class PDFReader {
	public static void main(String args[]) throws IOException {
		//Loading an existing document
		File file = new File("./pdf/sample.pdf");
		PDDocument document = PDDocument.load(file);
		//Instantiate PDFTextStripper class
		PDFTextStripper pdfStripper = new PDFTextStripper();
		//Retrieving text from PDF document
		String text = pdfStripper.getText(document);
		
		String[] words=text.split(".com");
		System.out.println(words[0]);
		System.out.println(words[1]);
		
		//Closing the document
		document.close();
	}
}
