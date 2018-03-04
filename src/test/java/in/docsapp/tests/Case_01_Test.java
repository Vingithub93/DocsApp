package in.docsapp.tests;

import org.testng.annotations.Test;

import in.docsapp.generics.BaseTest;
import in.docsapp.generics.GenericMethods;
import in.docsapp.pages.OpsDashboardPagePO;
import in.docsapp.workflow.GenericServices;
import in.docsapp.workflow.ServiceLib;

/**
 * @author Vinayak
 *
 */
public class Case_01_Test extends BaseTest{
	
	public String caseName="case 3";
	
	/**
	 * 
	 * The testAddandAssignNewCase method will create a new case from ops login and will assign a doctor to that case
	 */
	@Test
	public void testAddandAssignNewCase(){
		
		/**  Creating the object of Service Library */
		ServiceLib service=new ServiceLib(driver);
		
		
		/** Login to the application as ops */
		service.initService("ops", caseName);;
		
		/** Creating a new case as ops */
		service.addCaseFlow(caseName);
		
		/** Assigning a new case to a doctor */
		service.assignDoctor(caseName);
		
		/** Logging out from application */
		service.exit();
	}
	
	
	/**
	 * 
	 * The testDiagnoseCaseAsDoctor method will diagnose a new case from doctor login
	 */	
	@Test(dependsOnMethods= {"testAddandAssignNewCase"})
	public void testDiagnoseCaseAsDoctor(){
		
		/**  Creating the object of Service Library */
		ServiceLib service=new ServiceLib(driver);
		
		/**  Logging into the application as doctor */
		service.initService("doctor", caseName);
		
		/** Diagnose a case as a doctor */
		service.diagnoseCaseFlow(caseName);
		
		/** Logging out from application */
		service.exit();
	}
	
	/**
	 * 
	 * The testVerifyCaseAsOps method will verify the case which is diagnosed by a doctor, from ops login
	 */
	@Test
	public void testVerifyCaseAsOps(){
		
		/**  Creating the object of Service Library */
		ServiceLib service=new ServiceLib(driver);
		
		/**  Logging into the application as doctor */
		service.initService("ops", caseName);
		
		/** Verifying a case as a ops */
		service.verifyCaseFlow(caseName);
		
		/** Logging out from application */
		service.exit();
	}
	
	/**
	 * @
	 * The testDownloadCaseAsVendor method will download the verified case from vendor login
	 */
	@Test
	public void testDownloadCaseAsVendor(){
		
		/**  Creating the object of Service Library */
		ServiceLib service=new ServiceLib(driver);
		
		/**  Logging into the application as doctor */
		service.initService("vendor", caseName);
		
		
		
		/** Logging out from application */
		service.exit();
	}
}
