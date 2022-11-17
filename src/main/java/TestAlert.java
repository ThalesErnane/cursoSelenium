import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class TestAlert {
	private WebDriver driver;
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finalizar() {
		driver.quit();
	}

	@Test
	public void deveInteragirComAlertSimples() {
		driver.findElement(By.id("alert")).click();

		String texto = dsl.alertaObterTextoEAceita(); 
		Assert.assertEquals("Alert Simples", texto);
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}

	@Test
	public void deveInteragirComAlertConfirm(){
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
	}
	
	@Test
	public void deveInteragirComAlertNegado() {
		dsl.clicarBotao("confirm");
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		Assert.assertEquals("Negado", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void deveInteragirComPrompt() {
		dsl.clicarBotao("prompt");
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		dsl.alertaEscrever("10");
		Assert.assertEquals("Era 10?", dsl.alertaObterTextoEAceita());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
	}
}
