package com.selenium;



import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.windows.WindowsDriver;

public class CalculatorTest {

	private static WindowsDriver<WebElement> CalculatorSession = null;
	private static WebElement CalculatorResult = null;

	@BeforeClass
	public static void setup() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("app", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
		try {
			CalculatorSession = new WindowsDriver<WebElement>(new URL("http://127.0.0.1:4723"), capabilities);
			CalculatorSession.manage().timeouts().implicitlyWait(2, SECONDS);

			CalculatorResult = CalculatorSession.findElementByAccessibilityId("CalculatorResults");
			assertNotNull(CalculatorResult);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@Before
	public void Clear() {
		CalculatorSession.findElementByName("Clear").click();
		Assert.assertEquals("0", _GetCalculatorResultText());
	}

	@AfterClass
	public static void TearDown() {
		CalculatorResult = null;

		if (CalculatorResult != null) {
			CalculatorSession.quit();
		}
		CalculatorResult = null;

	}

	@Test
	public void Addition() {
		CalculatorSession.findElementByName("One").click();
		CalculatorSession.findElementByName("Plus").click();

		CalculatorSession.findElementByName("Seven").click();
		CalculatorSession.findElementByName("Equals").click();

		assertEquals("8", _GetCalculatorResultText());
	}

	@Test
	public void Combination() {
		CalculatorSession.findElementByName("Seven").click();
		CalculatorSession.findElementByName("Multiply by").click();
		CalculatorSession.findElementByName("Nine").click();
		CalculatorSession.findElementByName("Plus").click();
		CalculatorSession.findElementByName("One").click();
		CalculatorSession.findElementByName("Equals").click();
		CalculatorSession.findElementByName("Divide by").click();
		CalculatorSession.findElementByName("Eight").click();
		CalculatorSession.findElementByName("Equals").click();
		assertEquals("8", _GetCalculatorResultText());

	}

	@Test
	public void Division() {
		CalculatorSession.findElementByName("Eight").click();
		CalculatorSession.findElementByName("Eight").click();
		CalculatorSession.findElementByName("Divide by").click();
		CalculatorSession.findElementByName("One").click();
		CalculatorSession.findElementByName("Equals").click();
		assertEquals("8", _GetCalculatorResultText());
	}

	@Test
	public void Multiplicacion() {
		CalculatorSession.findElementByName("Nine").click();
		CalculatorSession.findElementByName("Multiply by").click();
		CalculatorSession.findElementByName("Nine").click();
		CalculatorSession.findElementByName("Equals").click();
		assertEquals("81", _GetCalculatorResultText());

	}

	@Test
	public void Subtraction() {
		CalculatorSession.findElementByName("Nine").click();
		CalculatorSession.findElementByName("Minus").click();
		CalculatorSession.findElementByName("One").click();
		CalculatorSession.findElementByName("Equals").click();
		assertEquals("8", _GetCalculatorResultText());

	}

	protected String _GetCalculatorResultText() {
		return CalculatorResult.getText().replace("Display is", "").trim();

	}

}
