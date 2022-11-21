import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.DSL;
import junit.framework.Assert;

public class TesteAjax {
	
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=a41a2");
		dsl = new DSL(driver);
	}

	@After
	public void finalizar() {
		// driver.quit();
	}
	
	@Test
	public void testeAjax() {
		dsl.escrever("j_idt343:name", "Texto a ser digitado!");
		dsl.clicarBotao("j_idt343:j_idt347");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt343:display"), "Texto a ser digitado!"));
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("")));
		Assert.assertEquals("Texto a ser digitado!", dsl.obterTexto("j_idt343:display"));
	}
}
