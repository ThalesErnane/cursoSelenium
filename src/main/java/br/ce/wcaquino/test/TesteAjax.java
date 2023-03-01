package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TesteAjax extends BaseTest {

	private DSL dsl;

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml?jfwid=0d9a1");
		dsl = new DSL();
	}

	@Test
	public void testeAjax() {
		dsl.escrever("j_idt343:name", "Teste");
		dsl.clicarBotao("j_idt343:j_idt347");
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt343:display"), "Teste"));
		Assert.assertEquals("Teste", dsl.obterTexto("j_idt343:display"));
	}
}
