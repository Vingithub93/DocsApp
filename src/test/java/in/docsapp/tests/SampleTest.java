package in.docsapp.tests;

import java.util.List;

import in.docsapp.generics.Auto_Const;
import in.docsapp.generics.ExcelLibrary;
import in.docsapp.generics.GenericMethods;

public class SampleTest  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Auto_Const.EXCEL_PATH);
		
		System.out.println(GenericMethods.getProperty("sheet4"));
		
		List<String> ele=ExcelLibrary.getColumnValues(GenericMethods.getProperty("sheet4"), 2);
		for(String s:ele)
		{
			System.out.println(s);
		}
		System.out.println("casD");
	}

}
