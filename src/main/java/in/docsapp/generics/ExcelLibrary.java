package in.docsapp.generics;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelLibrary {

	public static FileInputStream fis;
	public static Workbook wb;
	
	//Get last row count
	public static int getLastRowCount(String sheetName)
	{
		int count=0;
		try 
		{
			fis=new FileInputStream(Auto_Const.EXCEL_PATH);
			wb=WorkbookFactory.create(fis);
			count=wb.getSheet(sheetName).getLastRowNum();
			
		}
		catch (Exception e) {
			System.out.println("Unable to get Last row count");
		}
		return count;
	}
	
	//Get last cell count
	public static int getLastCellCount(String sheetName, int rowNum)
	{
		int count=0;
		try 
		{
			fis=new FileInputStream(Auto_Const.EXCEL_PATH);
			wb=WorkbookFactory.create(fis);
			count=wb.getSheet(sheetName).getRow(rowNum).getLastCellNum();
		}
		catch (Exception e) {
			System.out.println("Unable to get Last cell count");
		}
		return count;
	}
	
	//Get last cell value
	public static String getCellValue(String sheetName, int rowNum, int cellNum)
	{
		String value="";
		try 
		{
			fis=new FileInputStream(Auto_Const.EXCEL_PATH);
			wb=WorkbookFactory.create(fis);
			value=wb.getSheet(sheetName).getRow(rowNum).getCell(cellNum).toString();
		}
		catch (Exception e) {
			System.out.println("Unable to get Cell value");
		}
		return value;
	}
	
	//Get Values
	public static List<String> getColumnValues(String sheetName, int cellNum)
	{
		List<String> values = new ArrayList<>();
		try 
		{
			fis=new FileInputStream(Auto_Const.EXCEL_PATH);
			wb=WorkbookFactory.create(fis);
			int rowCount=wb.getSheet(sheetName).getLastRowNum();
			System.out.println(rowCount);
			for(int i=0;i<rowCount;i++)
			{
				values.add(wb.getSheet(sheetName).getRow(i).getCell(cellNum).toString());
			}
		}
		catch (Exception e) {
			System.out.println("Unable to get Cell values");
		}
		
		return values;
	}
	
	//To find the row number for particular test case
	public static int findRowNum(String caseName, String sheetName)
	{
		int value=0;
		int count=0;
		try {
			fis=new FileInputStream(Auto_Const.EXCEL_PATH);
			wb=WorkbookFactory.create(fis);
			count=wb.getSheet(sheetName).getLastRowNum();
			
			first:
			for(int i=1;i<=count;i++)
			{
				if(wb.getSheet(sheetName).getRow(i).getCell(0).toString().equalsIgnoreCase(caseName))
				{
					value=i;
					break first;
				}
			}
		}
		catch (Exception e) {
			System.out.println("Unable to find row number for "+caseName);
		}
		return value;
	}
	
	//To find the row number for particular test case
		public static int findCellNum(String caseName, String sheetName)
		{
			int value=0;
			int count=0;
			try {
				fis=new FileInputStream(Auto_Const.EXCEL_PATH);
				wb=WorkbookFactory.create(fis);
				count=wb.getSheet(sheetName).getRow(0).getLastCellNum();
				
				
				first:
				for(int i=1;i<=count;i++)
				{
					if(wb.getSheet(sheetName).getRow(0).getCell(i).toString().equalsIgnoreCase(caseName))
					{
						value=i;
						break first;
					}
				}
			}
			catch (Exception e) {
				System.out.println("Unable to find cell number for "+caseName);
			}
			return value;
		}
	
		//Get Values
		public static List<String> getRowValues(String sheetName, int rowNum)
		{
			List<String> values = new ArrayList<>();
			try 
			{
				fis=new FileInputStream(Auto_Const.EXCEL_PATH);
				wb=WorkbookFactory.create(fis);
				int cellCount=wb.getSheet(sheetName).getRow(rowNum).getLastCellNum();
				System.out.println(cellCount);
				for(int i=0;i<cellCount;i++)
				{
					values.add(wb.getSheet(sheetName).getRow(rowNum).getCell(i).toString());
				}
			}
			catch (Exception e) {
				System.out.println("Unable to get Row values");
			}
			
			return values;
		}
		
		//To find the row number for particular test case
				public static String getSheetName(String vendoeName)
				{
					String value="";
					int count=0;
					try {
						fis=new FileInputStream(Auto_Const.EXCEL_PATH);
						wb=WorkbookFactory.create(fis);
						count=wb.getNumberOfSheets();
						
						first:
						for(int i=1;i<=count;i++)
						{
							if(wb.getSheetName(i).contains(vendoeName))
							{
								value=wb.getSheetName(i);
								break first;
							}
						}
					}
					catch (Exception e) {
						System.out.println("Unable to find vendor sheet for " + vendoeName);
					}
					return value;
				}
				
			public static String getSingleCell(String sheetName, int rowNum, String cellName)
			{
				String value="";
				int count=0;
				
				try 
				{
					fis=new FileInputStream(Auto_Const.EXCEL_PATH);
					wb=WorkbookFactory.create(fis);
					value=getCellValue(sheetName, rowNum, findCellNum(cellName, sheetName));
				}
				catch (Exception e) {
					System.out.println("Unable to find Sigle cell Name");
				}
				return value;
			}
}
