import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class TesteCampoTreinamento {

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
	}

	@Test
	public void testeTextField() {
		page.setNome("Texto a ser digitado");
		Assert.assertEquals("Texto a ser digitado", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void testeTextFieldDuplo() {
		page.setNome("Texto a ser digitado");
		Assert.assertEquals("Texto a ser digitado", dsl.obterValorCampo("elementosForm:nome"));
		
		page.setNome("Segundo Texto a ser digitado");
		Assert.assertEquals("Segundo Texto a ser digitado", dsl.obterValorCampo("elementosForm:nome"));
	}

	@Test
	public void deveInteragirComTextArea() {
		page.setSugestoes("Teste");
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
	}

	@Test
	public void deveInteragirComRadioButton() {
		page.setSexoMasculino();
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}

	@Test
	public void deveInteragirComCheckBox() {
		page.setComidaFavoritaCarne();
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:0"));
	}

	@Test
	public void deveInteragirComComboBox() {
		page.setEscolaridade("2o grau completo");
		Assert.assertEquals("", dsl.obterValorComboBox("elementosForm:escolaridade"));
	}

	@Test
	public void deveVerificarValoresCombo(){
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	
	@Test
	public void deveVerificarValoresComboMultiplo(){
		page.setEsporte("Natacao", "Corrida", "O que eh esporte?");

		List<String> opcoesMarcadas = dsl.obterValoresComboBox("elementosForm:esportes");
		Assert.assertEquals(3, opcoesMarcadas.size());
		
		page.setDesmarcarComboBox("Corrida");
		opcoesMarcadas = dsl.obterValoresComboBox("elementosForm:esportes");
		Assert.assertEquals(2, opcoesMarcadas.size());
		Assert.assertTrue(opcoesMarcadas.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}

	@Test
	public void deveInteragirComLinks() {
		page.voltar();
		Assert.assertEquals("Voltou!", page.obterResultadoCadastro());
	}

	@Test
	public void deveInteragirComTextosNaPagina() {
		// Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo
		// de Treinamento"));
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));

		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		
	}
}
