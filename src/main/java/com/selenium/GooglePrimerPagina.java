package com.selenium;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.Keys.ENTER;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GooglePrimerPagina {

	private static final String URL = "https://www.google.com/";
	private static final String LINK_TEXT = "pruebas";
	private static final String CLASSNAME_LISTA = "g";

	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver", ".\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().fullscreen();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		int parametro = 6;

		try {

			driver.get(URL);
			driver.findElement(By.name("q")).sendKeys("pruebaz" + ENTER);
			WebElement sugerencia = wait.until(presenceOfElementLocated(By.partialLinkText("pruebas")));
			System.out.println(sugerencia.getText());
			assertEquals("pruebas", sugerencia.getText());
			driver.findElement(By.partialLinkText(LINK_TEXT)).click();

			List<WebElement> lista = wait.until(presenceOfAllElementsLocatedBy(By.className(CLASSNAME_LISTA)));
			for (WebElement webElement : lista) {
				System.out.println(webElement.getText());
			}
			System.out.println("======================================================================================");
			System.out.println("el numero de resultados es: " + lista.size());

			int cont = lista.size();
			if (cont > parametro) {
				System.out.println(
						"======================================================================================");
				System.out.println("el resultado de la lista es mayor que: " + parametro);
				wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className(CLASSNAME_LISTA), parametro));
			} else {
				System.out.println(
						"======================================================================================");
				System.out.println("el resultado de la lista es menor que: " + parametro);
			}

		} finally {
			driver.quit();
		}

	}
}
