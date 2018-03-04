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
		ServiceLib service=new ServiceLib(driver);
		
		
		/** Login to the application as ops */
		service.initService("ops", "case 3");;
		
		/** Creating a new case as ops */
		service.addCaseFlow("case 3");
		
		/** Assigning a new case to a doctor */
		service.assignDoctor("case 3");
		
		/** Logging out from application */
		service.exit();
	}
	
	@Test(dependsOnMethods= {"testAddandAssignNewCase"})
	public void testDiagnoseCaseAsDoctor(){
		
		/**  Creating the object of Service Library */
		ServiceLib service=new ServiceLib(driver);
		
		/**  Logging into the application as doctor */
		service.initService("doctor", "case 3");
		
		/** Diagnose a case as a doctor */
		service.diagnoseCaseFlow("case 3");
		
		/** Logging out from application */
		service.exit();
	}
	
	@Test
	public void testVerifyCaseAsOps(){
		
		/**  Creating the object of Service Library */
		ServiceLib service=new ServiceLib(driver);
		
		/**  Logging into the application as doctor */
		service.initService("ops", "case 2");
		
		/** Verifying a case as a ops */
		service.verifyCaseFlow("case 2");
		
		/** Logging out from application */
		service.exit();
	}
	
	@Test
	public void testDownloadCaseAsVendor(){
		
		/**  Creating the object of Service Library */
		ServiceLib service=new ServiceLib(driver);
		
		/**  Logging into the application as doctor */
		service.initService("ops", "case 2");
		
		/** Verifying a case as a ops */
		service.verifyCaseFlow("case 2");
		
		/** Logging out from application */
		service.exit();
	}
}
