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

public class TesteSincronismo extends BaseTest {
	
	private DSL dsl;

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		Thread.sleep(5000);
		dsl.escrever("novoCampo", "Deu certo?");
		Assert.assertEquals("Deu certo?", dsl.obterValorCampo("novoCampo"));
	}
	
	@Test
	public void deveUtilizarEsperaImplicita() throws InterruptedException {
		dsl.esperaImplicita(5);
		dsl.clicarBotao("buttonDelay");
		dsl.escrever("novoCampo", "Deu certo?");
		Assert.assertEquals("Deu certo?", dsl.obterValorCampo("novoCampo"));
		dsl.esperaImplicita(0);
	}
	
	@Test
	public void deveUtilizarEsperaExplicita() throws InterruptedException {
		dsl.clicarBotao("buttonDelay");
		WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escrever("novoCampo", "Deu certo?");
		Assert.assertEquals("Deu certo?", dsl.obterValorCampo("novoCampo"));
	}

}
