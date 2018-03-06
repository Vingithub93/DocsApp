package in.docsapp.tests;

import org.testng.annotations.Test;

import in.docsapp.generics.BaseTest;
import in.docsapp.workflow.ServiceLib;

/**
 * @author Vinayak
 *
 */
public class MaxLifeCaseTest extends BaseTest{
	
	public String testDesc = "Create a new case of max life, diagnose the case and verify the diagnosed case";
	
	public String caseName="case 1";
	
	/**
	 * 
	 * <p>The testAddandAssignNewCase method will create a new case from ops login and will assign a doctor to that case</p>
	 * step 1 : Login to the application as Ops<br>
	 * step 2 : Creating a new max life case<br>
	 * step 3 : Assigning the created case to a doctor<br>
	 * step 4 : Logout from the application<br>
	 * step 5 : Login to the application as Doctor<br>
	 * step 6 : Diagnose the created case as doctor<br>
	 * step 7 : Logout from the application<br>
	 * step 8 : Login to the application as Ops<br>
	 * step 9 : Verifying the diagnosed case as Ops<br>
	 * step 10 : Logout from the application<br>
	 * 
	 * 
	 */
	@Test()
	public void createDiagnoseVerifyMaxLifeCase(){
		
		/* Creating the object of Service Library */
		ServiceLib service=new ServiceLib(driver);
		
		/* step 1: Login to the application as ops */
		service.loginservice("ops", caseName);
		
		
		/*step 2: Creating a new case as ops */
		service.addCaseFlow(caseName);
		
		/*step 3: Assigning a new case to a doctor */
		service.assignDoctor(caseName);
		
		/*step 4: Logging out from application */
		service.logoutService();
		
		/*step 5: Login to the application as doctor */
		service.loginservice("doctor", caseName);
		
		/*step 6: Diagnose a case as a doctor */
		service.diagnoseCaseFlow(caseName);
		
		/*step 7: Logging out from application */
		service.logoutService();
		
		/*step 8: Login to the application as ops */
		service.loginservice("ops", caseName);
		
		/*step 9: Verifying the diagnosed case */
		service.verifyCaseFlow(caseName);
		
		/*step 10: Logging out from application */
		service.logoutService();
		
	}
	


}
