package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;

public class TestAlert extends BaseTest {

	private DSL dsl;

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@Test
	public void deveInteragirComAlertSimples() {
		DriverFactory.getDriver().findElement(By.id("alert")).click();

		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples", texto);
		DriverFactory.getDriver().findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}

	@Test
	public void deveInteragirComAlertConfirm() {
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
