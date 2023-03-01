package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TestePrime extends BaseTest {
	
	private DSL dsl;

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=1b4df");
		dsl = new DSL();
	}

	@Test // quando css esconde o radio 
	public void deveInteragirComRadioPrime() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=d9226");
		 // subir um nivel da div etc (/..)
		 // descer um nivel da div - span etc (//) 
		dsl.clicarRadioButton(By.xpath("//input[@id='j_idt344:console:0']/../..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:0"));
		
		dsl.clicarRadioButton(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt344:console:1"));
	}
	
	@Test
	public void deveInteragirComSelectPrime() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=1b4df");
		dsl.clicarRadioButton(By.id("j_idt343:option_label")); 
		dsl.clicarRadioButton(By.xpath("j_idt343:option_label"));
	}
	
	
}
