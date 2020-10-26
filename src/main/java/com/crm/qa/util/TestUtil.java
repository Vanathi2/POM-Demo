package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



import org.apache.commons.exec.OS;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {
	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 30;
	static HSSFWorkbook book;
	static HSSFSheet sheet;

	public static String TESTDATA_SHEET_PATH = "C:\\Users\\smile\\eclipse-workspace\\FREECRMProject\\src\\main\\java\\com\\crm\\qa\\testdata\\CRMTestData.xls";
	public static String SCREENSHOT_PATH = "C:\\Users\\smile\\eclipse-workspace\\FREECRMProject\\Screenshots\\";

	public static void switchToMainPanelFrame() {
		driver.switchTo().frame("mainpanel");

	}

	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = (HSSFWorkbook) WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		sheet = book.getSheetAt(0);
		Object[][] data = null;
		if (sheet != null && sheet.getRow(0) != null) {
			data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		}

		// System.out.println(sheet.getLastRowNum()+"------------"+sheet.getRow(0).getLastCellNum());
		if (data != null) {
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {

					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
					// System.out.println(data[i][k]);
				}
			}

		}

		return data;
	}
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	//	String currentDir = System.getProperty("user.dir");
	//	FileHandler.copy(scrFile, new File(currentDir + System.currentTimeMillis()+".png"));
		
		FileHandler.copy(scrFile, new File(SCREENSHOT_PATH+System.currentTimeMillis()+".png"));
		
		
		
	}
	
	public static void clickable(WebDriver driver, WebElement locator, int timeout) {
		
		new WebDriverWait(driver, timeout).ignoring(ElementNotInteractableException.class)
		.until(ExpectedConditions.elementToBeClickable(locator));
		
		
		
	}

}
