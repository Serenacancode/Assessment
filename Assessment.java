package ccim.iar.ui.screen;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assessment {

	public static final String SUMMARY = "Summary";
	public static final String DETAIL = "Detail";
	public static final String OUTCOMES = "Outcomes";

	private static final Logger log = LogManager.getLogger(Assessment.class);

	public void view(WebDriver driver, int index, String detail) {

		// start on person details screen

		String action = "Assessment view (" + detail + ")";
		util.logActionStart(driver, action);

		util.moveToAssessmentsFrame(driver);

		// log.debug("Assessment Search Results " + driver.getPageSource());
		// //SearchFilter

		// driver.findElement(By.id("SearchResults")); // table

		// log.debug("Assessment Search Results " +
		// searchResultsBody.getText()); //SearchFilter
		WebElement searchResultsRow = driver.findElement(By.id("row" + index));
		// log.debug("Person Search Results = " + searchResultsRow.getText());
		// //SearchFilter

		// scroll to the row
		Actions actions = new Actions(driver);
		actions.moveToElement(searchResultsRow);
		actions.perform();

		util.waitThnkTime(util.thinkTime);

		/*
		 * List <WebElement> axRowColumns =
		 * driver.findElements(By.className("yui-dt-liner")); //assessment list
		 * columns
		 * 
		 * WebElement link = null; Iterator <WebElement> it =
		 * axRowColumns.iterator(); while (it.hasNext()) { link = it.next();
		 * //log.debug("ax = " + link.getText()); if
		 * (link.getText().contains(detail)) break; }
		 * 
		 * if (link != null) { link.findElement(By.tagName("a")).click(); }
		 */

		// log.debug("before click... " + driver.getPageSource());

		searchResultsRow.findElement(By.linkText(detail)).click();
		long startTime = System.currentTimeMillis();

		// Wait for the page to load, timeout after 10 seconds
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				d.switchTo().defaultContent(); // you are now outside frames
				d.switchTo().frame("ConcertoContext");
				d.switchTo().frame("application-content");
				d.switchTo().frame("cdv-document"); // -extra
				log.debug("waiting...  " + d.getTitle());

				//return d.findElement(By.className("document-loaded"))
				return d.findElement(By.tagName("div"))
						.isDisplayed(); // .findElement(By.id("viewer-container")).

			}
		});

		log.info(action + " duration (sec): " + (System.currentTimeMillis() - startTime) / 1000);

		driver.switchTo().defaultContent(); // you are now outside frames

		// log.debug("on new page ... " + driver.getPageSource());

		log.info(action + " done, now on page " + driver.getTitle());

		log.info(action + " ... viewing assessment");
		
		driver.switchTo().defaultContent(); // you are now outside frames
		driver.switchTo().frame("ConcertoContext");
		driver.switchTo().frame("application-content");
		driver.switchTo().frame("cdv-document");
		//log.debug("application-content ... " + driver.getPageSource());

		//driver.findElement(By.name("div")).g
		WebElement assessmentWindowlet = driver.findElements(By.tagName("div")).get(1);
		
		//log.debug("assessmentWindowlet= " + assessmentWindowlet.getText());
		
		//Dimension dim = assessmentWindowlet.getSize();
		//log.debug("dim.getHeight= " + dim.getHeight());
		// util.scrollDown(driver, assessmentWindowlet, dim.getHeight(), 2);
		
		// paging up / down
		util.paging(assessmentWindowlet, util.PAGE_DOWN, 5, 1);
		util.paging(assessmentWindowlet, util.PAGE_UP, 3, 1);
		
		util.waitThnkTime(util.thinkTime);
		//util.waitThnkTime(util.thinkTime);

	}

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
