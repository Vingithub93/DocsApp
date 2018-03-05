/*
 * 
 */
package in.docsapp.generics;

import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtentReportUtils.
 */
public class ExtentReportUtils 
{

	/**
	 * Log info.
	 *
	 * @param info the info
	 */
	//BaseClass baseClass = new BaseClass();
	public void logInfo(String info)
	{
		BaseTest.logger.log(LogStatus.INFO,info);
	}
	
	/**
	 * Log error.
	 *
	 * @param info the info
	 */
	public void logError(String info)
	{
		BaseTest.logger.log(LogStatus.ERROR,info);
	}
	
	/**
	 * Log fail.
	 *
	 * @param info the info
	 */
	public void logFail(String info)
	{
		BaseTest.logger.log(LogStatus.FAIL,info);
	}
	
	/**
	 * Log pass.
	 *
	 * @param info the info
	 */
	public void logPass(String info)
	{
		BaseTest.logger.log(LogStatus.PASS, info);
	}
	
	public void logPass(String info, String step)
	{
		BaseTest.logger.log(LogStatus.PASS, step, info);
	}
	
	/**
	 * Log warning.
	 *
	 * @param info the info
	 */
	public void logWarning(String info)
	{
		BaseTest.logger.log(LogStatus.WARNING,info);
	}
	
	/**
	 * Log skip.
	 *
	 * @param info the info
	 */
	public void logSkip(String info)
	{
		BaseTest.logger.log(LogStatus.SKIP,info);
	}
	
	/**
	 * Log image.
	 *
	 * @param info the info
	 * @param image the image
	 */
	public void logImage(String info, String image)
	{
		image=BaseTest.logger.addScreenCapture(image);
		BaseTest.logger.log(LogStatus.INFO, info,image);
	}
	
}
