import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import br.ce.wcaquino.core.DSL;
import junit.framework.Assert;

public class TestePrimeFaces {
	
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=14a49");
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=d741c");
		dsl = new DSL(driver);
	}

	@After
	public void finalizar() {
		// driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioPrimeFaces() {
		// css esta escondendo o radio
		dsl.clicarRadioButton(By.xpath("//input[@id='j_idt344:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:0"));
	}
	
	@Test
	public void deveInteragirComRadioPrimeFacesAPartirDeUmTexo() {
		// css esta escondendo o radio
		 dsl.clicarRadioButton(By.xpath("//label[.='Option3']/..//span"));
		 Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:2"));
	}
	
	
	@Test
	public void deveInteragirComComboPrimeFaces() {
		dsl.selecionarComboPrime("j_idt343:option_input", "j_idt343:option_2");
		Assert.assertEquals("Option2", dsl.obterTexto("j_idt343:option_label"));
	}
	
}
