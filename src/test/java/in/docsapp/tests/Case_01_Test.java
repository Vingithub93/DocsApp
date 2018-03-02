package in.docsapp.tests;

import org.testng.annotations.Test;

import in.docsapp.generics.BaseTest;
import in.docsapp.generics.GenericMethods;
import in.docsapp.pages.OpsDashboardPagePO;
import in.docsapp.workflow.GenericServices;
import in.docsapp.workflow.ServiceLib;

public class Case_01_Test extends BaseTest{
	
	
	@Test
	public void testAddandAssignNewCase(){
		
		/**  Creating the object of Service Library */
		ServiceLib service=new ServiceLib();
		
		
		/** Login to the application as ops */
		service.initService(driver, "ops", "case 2");;
		
		/** Creating a new case as ops */
		service.addCaseFlow(driver, "case 2");
		
		/** Assigning a new case to a doctor */
		service.assignDoctor(driver, "case 2");
		
		/** Logging out from application */
		service.exit(driver);
	}
	
	@Test(dependsOnMethods= {"testAddandAssignNewCase"})
	public void testDiagnoseCaseAsDoctor(){
		
		/**  Creating the object of Service Library */
		ServiceLib service=new ServiceLib();
		
		/**  Logging into the application as doctor */
		service.initService(driver, "doctor", "case 2");
		
		/** Diagnose a case as a doctor */
		service.diagnoseCaseFlow(driver, "case 2");
		
		/** Logging out from application */
		service.exit(driver);
	}
	
}
