package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;

public class TesteCadastro extends BaseTest {

	private DSL dsl;
	private CampoTreinamentoPage page;

	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		page = new CampoTreinamentoPage();
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
