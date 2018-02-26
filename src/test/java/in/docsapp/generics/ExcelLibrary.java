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
					
		}
		
		return values;
	}
	
}
