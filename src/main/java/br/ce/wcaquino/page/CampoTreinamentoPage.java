package br.ce.wcaquino.page;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.BasePage;

public class CampoTreinamentoPage extends BasePage {
	
	// herda implicitamente do BasePage o DSL 
	// Pardão Page Object 

	public void setNome(String nome) {
		dsl.escrever("elementosForm:nome", nome);
	}

	public void setSobrenome(String nome) {
		dsl.escrever("elementosForm:sobrenome", nome);
	}

	public void setSexoMasculino() {
		dsl.clicarRadioButton("elementosForm:sexo:0");
	}

	public void setSexoFeminino() {
		dsl.clicarRadioButton("elementosForm:sexo:1");
	}

	public void setComidaFavoritaCarne() {
		dsl.selecionarCheckBox("elementosForm:comidaFavorita:0");
	}

	public void setComidaFavoritaFrango() {
		dsl.selecionarCheckBox("elementosForm:comidaFavorita:1");
	}

	public void setComidaFavoritaPizza() {
		dsl.selecionarCheckBox("elementosForm:comidaFavorita:2");
	}
	
	public void setComidaFavoritaVegetariano() {
		dsl.selecionarCheckBox("elementosForm:comidaFavorita:3");
	}

	public void setEscolaridade(String escolaridade) {
		dsl.selecionarCombo("elementosForm:escolaridade", escolaridade);
	}

	public void setEsporte(String ... valores) {
		for(String valor: valores) 
		dsl.selecionarCombo("elementosForm:esportes", valor);
	}

	public void setDesmarcarComboBox(String valores) {
		dsl.deselecionarCombo("elementosForm:esportes", valores);
	}
	
	public void setSugestoes(String texto) {
		dsl.escrever("elementosForm:sugestoes", texto);
	}
	
	public void cadastrar() {
		dsl.clicarBotao("elementosForm:cadastrar");
	}
	
	public void voltar() {
		dsl.clicarLink("Voltar");
	}

	public String obterResultadoCadastro() {
		// return dsl.obterTexto("resultado");
		return dsl.obterTexto(By.xpath(".//*[@id='resultado']/span"));
	}
	
	public String obterNomeCadastro() {
		// return dsl.obterTexto("descNome");
		return dsl.obterTexto(By.xpath(".//*[@id='descNome']/span"));
	}

	public String obterSobrenomeCadastro() {
		// return dsl.obterTexto("descSobrenome");
		return dsl.obterTexto(By.xpath(".//*[@id='descSobrenome']/span"));
	}

	public String obterSexoCadastro() {
		// return dsl.obterTexto("descSexo");
		return dsl.obterTexto(By.xpath(".//*[@id='descSexo']/span"));
	}

	public String obterComidaCadastro() {
		// return dsl.obterTexto("descComida");
		return dsl.obterTexto(By.xpath(".//*[@id='descComida']/span"));
	}

	public String obterEscolaridadeCadastro() {
		// return dsl.obterTexto("descEscolaridade");
		return dsl.obterTexto(By.xpath(".//*[@id='descEscolaridade']/span"));
	}

	public String obterEsporteCadastro() {
		// return dsl.obterTexto("descEsportes");
		return dsl.obterTexto(By.xpath(".//*[@id='descEsportes']/span"));
	}

}
