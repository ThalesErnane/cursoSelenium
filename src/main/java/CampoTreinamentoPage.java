import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
	
	// Pardão Page Object 

	private DSL dsl;

	public CampoTreinamentoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}

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
		return dsl.obterTexto("resultado");
	}

	public String obterNomeCadastro() {
		return dsl.obterTexto("descNome");
	}

	public String obterSobrenomeCadastro() {
		return dsl.obterTexto("descSobrenome");
	}

	public String obterSexoCadastro() {
		return dsl.obterTexto("descSexo");
	}

	public String obterComidaCadastro() {
		return dsl.obterTexto("descComida");
	}

	public String obterEscolaridadeCadastro() {
		return dsl.obterTexto("descEscolaridade");
	}

	public String obterEsporteCadastro() {
		return dsl.obterTexto("descEsportes");
	}

}
