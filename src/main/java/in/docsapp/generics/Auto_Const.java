package in.docsapp.generics;

public interface Auto_Const {
	
	//Directory Path
	String DIR_PATH=System.getProperty("user.dir");
	
	//Excel Sheet Path
	String EXCEL_PATH=DIR_PATH+"\\src\\test\\resources\\controller\\execution_controller.xlsx";
	
	//screenshots path
	String SCREENSHOT_PATH=DIR_PATH+"\\screenshots";

	//screenshots path
	String REPORTS_PATH=DIR_PATH+"\\reports";
	
	//Config properties path
		String CONFIG_PATH=DIR_PATH+"\\src\\test\\resources\\properties\\config.properties";
}
