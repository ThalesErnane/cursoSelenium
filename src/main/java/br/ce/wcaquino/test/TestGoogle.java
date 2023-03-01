package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DriverFactory;

public class TestGoogle extends BaseTest {

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	
	@Test
	public void teste() {
	//	System.setProperty("webdriver.gecko.driver", "C://Users//Thales Ernane//Downloads//geckodriver//geckodriver.exe");
	 // System.setProperty("webdriver.edge.driver", "C://Users//Thales Ernane//Downloads//geckodriver//Edge//msedgedriver.exe");
		//  WebDriver driver = new FirefoxDriver(); 
		// WebDriver driver = new EdgeDriver();
		// driver.manage().window().setPosition(new Point(100, 100)); // não tem suporte para firefox
		DriverFactory.getDriver().manage().window().setSize(new Dimension(1200, 765));
		DriverFactory.getDriver().manage().window().maximize();
		DriverFactory.getDriver().get("https://www.google.com");
		System.out.println(DriverFactory.getDriver().getTitle());
		Assert.assertEquals("Google", DriverFactory.getDriver().getTitle());
		DriverFactory.getDriver().quit(); // fecha todas as abas do browser 
		// driver.close(); // fecha uma aba
	}
}
