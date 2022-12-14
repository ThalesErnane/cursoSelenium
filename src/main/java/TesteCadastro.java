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

import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import junit.framework.Assert;

public class TesteCadastro {

	private WebDriver driver;
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}

	@After
	public void finalizar() {
		// driver.quit();
		DriverFactory.killDriver();
	}

	@Test
	public void deveRealizarCadastroComSucesso() {
		page.setNome("Thales");
		page.setSobrenome("Ernane de Souza");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setEscolaridade("Superior");
		page.setEsporte("Natacao", "Corrida");
		page.cadastrar();

		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		Assert.assertEquals("Thales", page.obterNomeCadastro());
		Assert.assertEquals("Ernane de Souza", page.obterSobrenomeCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Carne", page.obterComidaCadastro());
		Assert.assertEquals("superior", page.obterEscolaridadeCadastro());
		Assert.assertEquals("Natacao Corrida", page.obterEsporteCadastro());
	}

	@Test
	public void deveValidarNomeObrigatorio() {
		page.cadastrar();
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void deveValidarSobreNomeObrigatorio() {
		page.setNome("Thales");
		page.cadastrar();
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void deveValidarSexoObrigatorio() {
		page.setNome("Thales");
		page.setSobrenome("Ernane de Souza");
		page.cadastrar();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void deveValidarComidaVegetariano() {
		page.setNome("Thales");
		page.setSobrenome("Ernane de Souza");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		page.setComidaFavoritaPizza();
		page.cadastrar();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}

	@Test
	public void deveValidarEsportistaIndeciso() {
		page.setNome("Thales");
		page.setSobrenome("Ernane de Souza");
		page.setSexoMasculino();
		page.setComidaFavoritaCarne();
		
		page.setEsporte("Karate", "O que eh esporte?");

		page.cadastrar();
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	}

}
