package br.ce.wcaquino.test;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;

public class TesteCampoTreinamento extends BaseTest {

	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
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
		page.setSugestoes("teste");
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
		Assert.assertEquals("2o grau completo", dsl.obterValorComboBox("elementosForm:escolaridade"));
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
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		// js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = DriverFactory.getDriver().findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela(){
		dsl.clicarBotaoTabela("Escolaridade", "Mestrado", "Radio", "elementosForm:tableUsuarios");
	}
}
