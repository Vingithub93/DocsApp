package in.docsapp.generics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class CreatingXML {

	public static List<String> readTestNames()
	{
		
		List<String> testcasenames=new ArrayList<String>();
		int testcasecount = ExcelLibrary.getLastRowCount(GenericMethods.getProperty("sheet8"));
		System.out.println(testcasecount);
		for(int i=1; i<=testcasecount; i++)
		{ 
			String status = ExcelLibrary.getCellValue(GenericMethods.getProperty("sheet8"), i, 1);
			if(status.equalsIgnoreCase("yes"))
			{
				String testcasename = ExcelLibrary.getCellValue(GenericMethods.getProperty("sheet8"), i, 0);
				testcasenames.add("in.docsapp.tests." + testcasename);
			}
		}
		return testcasenames;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		System.out.println(ExcelLibrary.getCellValue(GenericMethods.getProperty("sheet2"), 1, 0));
		//public void creatTestNGXML() {
			System.out.println("***********Creating Testng.xml***********");
			XmlSuite suite = new XmlSuite();
			suite.setName("Demo_Suite");
			XmlTest test = new XmlTest(suite);
			test.setName("Demo_Test");
			List<XmlClass> classes = new ArrayList<XmlClass>();
			List<String> testCasesToExecute=readTestNames();
			System.out.println(testCasesToExecute);
			for(int i=0; i<testCasesToExecute.size(); i++)
			{
				System.out.println(testCasesToExecute.get(i));
				classes.add(new XmlClass(testCasesToExecute.get(i)));
			}	
			/*classes.add(new XmlClass("scripts.ToVerifyInvalidLoginLogout"));
			classes.add(new XmlClass("scripts.ToVerifyTheVersion"));
			classes.add(new XmlClass("scripts.validLoginLogout"));*/
			test.setXmlClasses(classes) ;
			List<XmlSuite> suites = new ArrayList<XmlSuite>();
			suites.add(suite);
			//System.out.println(suite);
			File file = new File("./testng.xml");
	        //System.out.println("file"+file);

	        FileWriter writer;
			try {
				writer = new FileWriter(file);
				writer.write(suite.toXml());
		        writer.close(); 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
