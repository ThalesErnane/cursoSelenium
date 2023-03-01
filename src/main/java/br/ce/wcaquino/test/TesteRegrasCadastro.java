package br.ce.wcaquino.test;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.CampoTreinamentoPage;

@RunWith(Parameterized.class)
public class TesteRegrasCadastro extends BaseTest {
	
	// Testes Parametrizáveis
	
	private DSL dsl;
	private CampoTreinamentoPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String [] esportes;
	@Parameter(value=5)
	public String msg;
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
		page = new CampoTreinamentoPage();
	}

	@Parameters
	public static List<Object[]> getCollection(){
		return Arrays.asList(new Object[][] {
			 {"", "", "", Arrays.asList(), new String [] {}, "Nome eh obrigatorio"},
			 {"Thales", "", "", Arrays.asList(), new String [] {}, "Sobrenome eh obrigatorio"}, 
			 {"Thales", "Ernane", "", Arrays.asList(), new String [] {}, "Sexo eh obrigatorio"},
			 {"Thales", "Ernane", "Masculino", Arrays.asList("Carne", "Vegetariano"), new String [] {}, "Tem certeza que voce eh vegetariano?"},
			 {"Thales", "Ernane", "Masculino", Arrays.asList("Carne"), new String [] {"Karate", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void deveValidarRegras() {
		page.setNome(nome);
		page.setSobrenome(sobrenome);
		
		if(sexo.equals("Masculino")) {
			page.setSexoMasculino();
		} 	
		if(sexo.equals("Feminino")) {
			page.setSexoFeminino();
		}
		
		if(comidas.contains("Carne")) page.setComidaFavoritaCarne();
		if(comidas.contains("Frango")) page.setComidaFavoritaFrango();
		if(comidas.contains("Pizza")) page.setComidaFavoritaPizza();
		if(comidas.contains("Vegetariano")) page.setComidaFavoritaVegetariano();
		
		page.setEsporte(esportes);
		System.out.println(msg);
		page.cadastrar();
		Assert.assertEquals(msg, dsl.alertaObterTextoEAceita());
	}

}
