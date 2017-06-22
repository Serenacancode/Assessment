package ccim.iar.ui.screen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ccim.iar.ui.main.run;
import ccim.iar.ui.main.userinput;
import ccim.iar.ui.screen.util;

public class Assessment {

	private static final Logger log = LogManager.getLogger(Assessment.class);
	public static final String SUMMARY = "Summary";
	public static final String DETAIL = "Detail";
	public static final String OUTCOMES = "Outcomes";
	public static Boolean uploadsuccess = false;
	public static String Transaction_id;
	public static String err_id= null; 
	public static String aid = null; //the one from searching "review history"

	
	
	public static void view(WebDriver driver, int index, String detail) throws Exception {
		log.info("staring assessment.view");
		util.moveToAssessmentsFrame(driver);
		//log.debug("before click... " + driver.getPageSource());
		log.info("Viewing searchResultsRow" );

		WebElement openassessment = driver.findElement(By.linkText(detail));
		Actions clickit = new Actions(driver);
		clickit.doubleClick(openassessment);
		clickit.perform();
		log.info("Viewing "+detail);
		log.info("Entered "+detail); 
					String pmapfilename2 = "Iframe__0__2__.html";
			        log.info("... getting to : " + pmapfilename2);
			        if (PageMap.gotoFrame(driver, "Iframe__0__2__.html", "2")) { 
			            log.debug("Done. Success"); 
			        } else {log.debug("Done. gotoFrame unsuccess");                
			        } 
		//check if the page is correct
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				log.info("checking if its loaded");
				log.debug("waiting... " + driver.getTitle());
				//driver.switchTo().defaultContent(); //  
				//driver.switchTo().frame("ConcertoContext"); 
				//driver.switchTo().frame("application-content"); 
				return driver.findElement(By.id("doctitle")).isDisplayed();
			}});
		}
        
	
	
	public static void viewall(WebDriver driver, Boolean bool) {
		if (bool) {// Wait for the page to load, timeout after 10 seconds
			new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					driver.switchTo().defaultContent(); // 
					driver.switchTo().frame("ConcertoContext");
					driver.switchTo().frame("application-content");
					driver.switchTo().frame("cdv-document"); // -extra
						log.debug("waiting...  " + driver.getTitle());
				return driver.findElement(By.cssSelector("#dropdownlist > select"))
					.isDisplayed();}});
						log.debug("found dropdownlist");	
			int i;
			try {
				for (i = 2; i < 20; i ++)
				{
						log.info("i = "+i);
				if(driver.findElement(By.cssSelector("#dropdownlist > select > option:nth-child(2)")).isDisplayed())
				{
					WebElement ele;
					ele = driver.findElement(By.cssSelector("#dropdownlist> select > option:nth-child("+i+")"));
						log.info("found option "+ i);
					ele.click();
				}
			}
			log.info("exit the loop");}
		catch (Exception ex) {
			log.error("Exception: " + ex.getMessage());
			log.error(ex);}
	
		}}

	
	/*public static String checkid(WebDriver driver, Boolean bool) {
		String testedassessmentid = null;
		if (bool)
		{ 
			driver.switchTo().defaultContent(); // 
			driver.switchTo().frame("ConcertoContext");
			driver.switchTo().frame("application-content");	
						
		WebElement e= driver.findElement(By.id("ygtvc8"));   
		Actions ToolTip1 = new Actions(driver); 
	    ToolTip1.clickAndHold(e).perform(); 
	    util.waitThnkTime(2);   	
		WebElement u = driver.findElement(By.className("yui3-widget-bd")); 
		
	    util.waitThnkTime(5); 
	    String screener = u.getText();
	    log.info(screener); 
	    String[] arr = screener.split("\\r\\n|\\n|\\r");     
	    int i = 0;
	    for (String s: arr) {
	        if (i == 9) {
	        	testedassessmentid = s;
	        	System.out.println(s);
	        	i ++;}
	        else i ++;
	      } 
	    util.waitThnkTime(3);
	    ToolTip1.release(); }
		return testedassessmentid;	
	}*/
	 
	public static void printpage (WebDriver driver, Boolean bool) throws Exception {
		try {
 			if (bool) {

 				driver.switchTo().defaultContent(); // 
 				driver.switchTo().frame("ConcertoContext");
 				driver.switchTo().frame("application-content");	
				Set<String> beforePopup = driver.getWindowHandles();
		log.info(beforePopup);
		driver.findElement(By.id("button0")).click();
		
			log.info("clicked print - get- Message from webpage");
			new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().accept();
					log.info("printpage - pressed ENTER");
			util.waitThnkTime(2);
	        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() 
	        {
	            public Boolean apply(WebDriver driver) 
	            {
	            	Set<String>afterPopup = driver.getWindowHandles();
	    		    afterPopup.removeAll(beforePopup);
	    		   if(afterPopup.size() == 1) 
	    		   {
	    		      driver.switchTo().window((String)afterPopup.toArray()[0]); 
	    		   }
	            	return (afterPopup.size() == 1);
	            }
	        }
	        );
	        log.debug("Current url: " + driver.getCurrentUrl()); 
	        WebElement openpprint = driver.findElement(By.id("overridelink"));
	        		Actions clickit = new Actions(driver);
	        		clickit.doubleClick(openpprint);
	        		clickit.perform();
			//driver.navigate().to("javascript:document.getElementById('overridelink').click()");
	        		driver.manage().window().maximize();
			util.waitThnkTime(10);
			 driver.close();
			driver.switchTo().window((String)beforePopup.toArray()[0]);	
		 }}
		catch (Exception e) {
				//if (e.Contains(" Modal dialog present: This printout may contain personal information or personal health information and must be protect") )
				log.info("in the printing phase"+ driver.getTitle()+ driver.getCurrentUrl()); 
				log.error(e);			}
		

	
	}

	 
	public static void backToAssessmentListing(WebDriver driver) {
		// start on assessment details screen, click on Assessment Listing link to get back to person details screen

		String action = "Assessment backToAssessmentListing";
		util.logActionStart(driver, action);

		driver.switchTo().defaultContent(); // you are now outside frames
		driver.switchTo().frame("ConcertoContext");
		WebElement openassessment = driver.findElement(By.linkText("Assessment Listing"));
		Actions clickit = new Actions(driver);
		clickit.doubleClick(openassessment);
		clickit.perform();

		long startTime = System.currentTimeMillis();

		// Wait for the page to load, timeout after 10 seconds
		/*String pmapfilename2 = "Iframe__2__2__2__2__.html";
	    //  PageMap pageMap = new PageMap(true,true,true,userinput.pagesource_folder);
	    //  pageMap.getPageMap(driver);
		log.info("... getting to : " + pmapfilename2);
      if (PageMap.gotoFrame(driver, "Iframe__2__2__2__2__.html", null)) { 
          System.out.println(driver.findElement(By.tagName("Body")).getAttribute("outerHTML")); 
          log.debug("Done. Success"); 
      } else {log.debug("Done. gotoFrame unsuccess");                
      } 
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				log.info("assessment.backToAssessmentListing");
				log.debug("waiting... " + driver.getTitle());
				driver.switchTo().defaultContent(); //  
				driver.switchTo().frame("ConcertoContext"); 
				driver.switchTo().frame("application-content"); 
				driver.switchTo().frame("cdv-document"); 
				return driver.findElement(By.className("WindowletTitle")).getText().contains("Person Demographics");
			}
		});*/

		log.info(action + " duration (sec): " + (System.currentTimeMillis() - startTime) / 1000);

		driver.switchTo().defaultContent(); // you are now outside frames

		log.info(action + " done, now on page " + driver.getTitle());}

	

}
