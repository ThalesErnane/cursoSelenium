import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestGoogle {
	
	private WebDriver driver;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void finalizar() {
		driver.quit();
	}
	
	@Test
	public void teste() {
	//	System.setProperty("webdriver.gecko.driver", "C://Users//Thales Ernane//Downloads//geckodriver//geckodriver.exe");
	 // System.setProperty("webdriver.edge.driver", "C://Users//Thales Ernane//Downloads//geckodriver//Edge//msedgedriver.exe");
		//  WebDriver driver = new FirefoxDriver(); 
		// WebDriver driver = new EdgeDriver();
		// driver.manage().window().setPosition(new Point(100, 100)); // não tem suporte para firefox
		 driver.manage().window().setSize(new Dimension(1200, 765));
		 driver.manage().window().maximize();
		 driver.get("https://www.google.com");
		System.out.println(driver.getTitle());
		Assert.assertEquals("Google", driver.getTitle());
		driver.quit();
	}
}
