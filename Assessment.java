package ccim.iar.ui.screen;

import java.awt.AWTException;
import java.sql.Driver;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import ccim.iar.ui.screen.util;

public class Assessment {

	public static final String SUMMARY = "Summary";
	public static final String DETAIL = "Detail";
	public static final String OUTCOMES = "Outcomes";

	private static final Logger log = LogManager.getLogger(Assessment.class);

	public void view(WebDriver driver, int index, String detail) {

		// start on person details screen

		//String action = "Assessment view (" + detail + ")"; // this is giving me an ugly popup 
					//util.logActionStart(driver, action);

		util.moveToAssessmentsFrame(driver);

					//log.debug("Assessment Search Results " + driver.getPageSource());
	
					// driver.findElement(By.id("SearchResults")); // table

					// find the assessment on the left menu
					// searchResultsBody.getText()); //SearchFilter
//		WebElement searchResultsRow = driver.findElement(By.id("row" + index));
					//log.debug("Viewing Person Search Results = " + searchResultsRow.getText());
//		searchResultsRow.click();
		log.info("Viewing searchResultsRow" );
		
		util.waitThnkTime(util.thinkTime);

					//log.debug("before click... " + driver.getPageSource());
		log.info("Viewing "+detail);

		WebElement openassessment = driver.findElement(By.linkText(detail));
		
		Actions clickit = new Actions(driver);
		clickit.doubleClick(openassessment);
		clickit.perform();
		
// enter the assessment from there
//___________________________________________________________________________________________________________________________________________
		//searchResultsRow.findElement(By.linkText(detail)).click(); //click on either summary, detail or outcomes defined in run as details
		log.info("Entered "+detail);
		
		
		//long startTime = System.currentTimeMillis();
		

		// Wait for the page to load, timeout after 10 seconds
		// (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		
		new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {

				d.switchTo().defaultContent(); // you are now outside frames
				d.switchTo().frame("ConcertoContext");
				d.switchTo().frame("application-content");
				d.switchTo().frame("cdv-document"); // -extra
				log.debug("waiting...  " + d.getTitle());
				return d.findElement(By.cssSelector("#dropdownlist > select"))
						.isDisplayed();}});
		log.debug("found dropdownlist");
		
		int i;
		try {
		for (i = 2; i < 20; i ++)
		{
			log.info("i = "+i);
			if(driver.findElement(By.cssSelector("#dropdownlist > select > option:nth-child(2)")).isDisplayed())
			{
				log.info("start loop with i = "+i);
				WebElement ele;
				log.info("start with the " +i +"th option.");
				ele = driver.findElement(By.cssSelector("#dropdownlist> select > option:nth-child("+i+")"));
				util.waitThnkTime(1);
				log.info("found option "+ i);
				ele.click();
				log.info("in option "+ i);
			}
			log.info("done with i = "+i);
			}
		log.info("exit the loop");                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
	
		}
		catch (Exception ex) {
			log.error("Exception: " + ex.getMessage());
			log.error(ex);}
		
			

				//return d.findElement(By.className("document-loaded"))
				//return driver.findElement(By.tagName("div"))
					//	.isDisplayed(); // .findElement(By.id("viewer-container")).
		//option.sendKeys(value);
		
		//
		
		//element(By.cssContainingText("option", "BeaverBox Testing")).click();
		
		//log.info(action + " duration (sec): " + (System.currentTimeMillis() - startTime) / 1000);


		//log.info(" viewing assessment");
		//WebElement d4 = driver.findElement(By.cssSelector("#dropdownlist > select > option:nth-child(3)"));
		//log.info("found option2");
		//d4.click();
		//log.info("found option2");
		//log.info("found option2");
		//log.info("found option2");
		//util.waitThnkTime(util.thinkTime);

		/*driver.switchTo().defaultContent(); // you are now outside frames
					//WebElement result1[];
					//result1 = driver.switchTo().frame("ConcertoContext");
					//if ()
		log.debug("before click... " + driver.getPageSource());
		driver.switchTo().defaultContent();
		driver.switchTo().frame("ConcertoContext");
		log.info("in frame 'ConcertoContext'");
		driver.switchTo().frame("application-content");
		log.info("in frame 'application-content'");
		//driver.findElement(By.id("yui_3_17_2_1_1496333449578_322"));
		log.info("foundyui_3_17_2_1_1496333449578_322");
		driver.switchTo().frame("cdv-document");
		*log.info("in frame 'cdv-document'");
		driver.findElement(By.id("cdv-document"));
		log.info("found cdv-document");
		//driver.findElement(By.id("doclist")).sendKeys("selenium");
		//log.info("find 'doclist'");
		//driver.findElement(By.tagName("div"));	
		//log.info("found div'");
		
		driver.findElement(By.cssSelector("#dropdownlist > select > option:nth-child(3)")).isDisplayed();
		log.info("found option3");
		driver.findElement(By.xpath("//*[@id=\"dropdownlist\"]/select"));////#doclist > select
		log.info("found #dropdownlist > select > option:nth-child(2)");
		//*[@id="dropdownlist"]/select/option[2]
		//#doclist > select > option:nth-child(1)<option value="core">Top</option>
		//WebElement d2 = driver.findElement(By.xpath("//td[contains(., 'dropdownlist')"));
		//*[@id=\"dropdownlist\"]/select/option[2]"));
		log.info("found option2");
		
		util.waitThnkTime(1);
		log.info("in option 2");
		WebElement d2 = driver.findElement(By.xpath("//td[contains(@onchange, 'goToSection(this.options[this.selectedIndex])')"));
		 
		WebElement d5 = driver.findElement(By.cssSelector("#dropdownlist > select > option:nth-child(2)"));
		log.info("found option3");
		log.info("in option 3");
		
		/*WebElement d4 = driver.findElement(By.cssSelector("#doclist > select > option:nth-child(4)"));
		log.info("found option4");
		d4.click();
		util.waitThnkTime(1);
		log.info("in option 4");
		
		int i = 0;
		log.info(i+1);
		
		WebElement option[]= new WebElement[3];
		log.info("created empty option list");
		
		for(i=0;i <3; i++);{
			option[i] = driver.findElement(By.cssSelector("#doclist > select > option:nth-child(i+1)"));
			util.waitThnkTime(1);
			log.info("found option "+ i );
			option[i].click();
			util.waitThnkTime(1);
			log.info("in option "+ i);};
		*/
		
		//WebElement d1 = driver.findElement(By.cssSelector("#doclist > select > option:nth-child(1)"));
		//d1.click();

		//util.waitThnkTime(3);
		
		//WebElement d2 = driver.findElement(By.cssSelector("#doclist > select > option:nth-child(2)"));
		//d2.click();
		//*[@id="doclist"]/select
	
	}
		
		//		".input:option);
//				value("core")).isDisplayed();
		
		/*List<WebElement> optionlist =driver.findElements(By.className("option value"));
		int i;
		
		
		
		}
				
		
		
		
		
		
		
		*/
		
		//log.debug("  ... " + driver.getPageSource());

//
		//WebElement assessmentWindowlet = driver.findElements(By.tagName("div")).get(1);
		
		//log.debug("assessmentWindowlet= " + assessmentWindowlet.getText());
		
		//Dimension dim = assessmentWindowlet.getSize();
		//log.debug("dim.getHeight= " + dim.getHeight());
		//til.scrollDown(driver, assessmentWindowlet, dim.getHeight(), 2);
		
		// paging up / down
		
	//	util.paging(driver,"PAGE_DOWN", 5);
		//util.paging(assessmentWindowlet, util.PAGE_UP, 3, 1);
		
	//	util.waitThnkTime(util.thinkTime)
	//	;
		//util.waitThnkTime(util.thinkTime);
	//	util.paging(driver,"PAGE_UP",5);
	//}

	public void backToAssessmentListing(WebDriver driver) {

		// start on assessment details screen
		// click on Assessment Listing link to get back to person details screeen

		String action = "Assessment backToAssessmentListing";
		util.logActionStart(driver, action);

		driver.switchTo().defaultContent(); // you are now outside frames
		driver.switchTo().frame("ConcertoContext");

		driver.findElement(By.linkText("Assessment Listing")).click();

		long startTime = System.currentTimeMillis();

		// Wait for the page to load, timeout after 10 seconds
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				log.debug("waiting... " + d.getTitle());
				d.switchTo().defaultContent(); // you are now outside frames
				d.switchTo().frame("ConcertoContext");
				d.switchTo().frame("application-content");
				d.switchTo().frame("cdv-document");
				return d.findElement(By.className("WindowletTitle")).getText().contains("Person Demographics");
			}
		});

		log.info(action + " duration (sec): " + (System.currentTimeMillis() - startTime) / 1000);

		driver.switchTo().defaultContent(); // you are now outside frames

		log.info(action + " done, now on page " + driver.getTitle());

	}
}
