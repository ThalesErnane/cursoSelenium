package br.ce.wcaquino.core;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	public void escrever(By by, String texto){
		DriverFactory.getDriver().findElement(by).clear();
		DriverFactory.getDriver().findElement(by).sendKeys(texto);
	}

	public void escrever(String id_campo, String texto){
		escrever(By.id(id_campo), texto);
	}
	
	public String obterValorCampo(String id_campo) {
		return DriverFactory.getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicarRadioButton(By by) {
		DriverFactory.getDriver().findElement(by).click();
	}
	
	public void clicarRadioButton(String id_campo) {
		clicarRadioButton(By.id(id_campo));
	}
	
	public boolean isRadioMarcado(String id_campo) {
		return DriverFactory.getDriver().findElement(By.id(id_campo)).isSelected();
	}
	
	public void selecionarCombo(String id_campo, String texto_visivel) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		combo.selectByVisibleText(texto_visivel);
	}
	
	public void deselecionarCombo(String id ,String ... valores) {
		for(String valor: valores) {
			WebElement element = DriverFactory.getDriver().findElement(By.id(id));
			Select combo = new Select(element);
			combo.deselectByVisibleText(valor);
		}
	}
	
	public void selecionarCheckBox(String id_campo) {
		DriverFactory.getDriver().findElement(By.id(id_campo)).click();
	}
	
	public List<String> obterValoresComboBox(String id_campo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public String obterValorComboBox(String id_campo) {
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public int obterQuantidadeOpcoesCombo(String id_campo){
		WebElement element = DriverFactory.getDriver().findElement(By.id(id_campo));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public String obterValueElemento(String id_campo) {
		return DriverFactory.getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public boolean verificarOpcaoCombo(String id, String texto){
		WebElement element = DriverFactory.getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(texto)){
				return true;
			}
		}
		return false;
	}
	
	public boolean isCheckMarcado(String id_campo) {
		return DriverFactory.getDriver().findElement(By.id(id_campo)).isSelected();
	}
	
	
	public void clicarBotao(String id_campo) {
		DriverFactory.getDriver().findElement(By.id(id_campo)).click();;
	}
	
	public void clicarLink(String link) {
		DriverFactory.getDriver().findElement(By.linkText(link)).click();
	}
	
	
	public String obterTexto(By by) {
		 return DriverFactory.getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id_campo) {
		 return obterTexto(By.id(id_campo));
	}
	
	public String alertaObterTexto(){
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public void alertaEscrever(String texto) {
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		alert.sendKeys(texto);
		alert.accept();
	}
	
	public String alertaObterTextoEAceita(){
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
		
	}
	
	public String alertaObterTextoENega(){
		Alert alert = DriverFactory.getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}
	
	public void entrarFrame(String id_campo) {
		DriverFactory.getDriver().switchTo().frame(id_campo);
	}
	
	public void sairFrame(){
		DriverFactory.getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id_campo) {
		DriverFactory.getDriver().switchTo().window(id_campo);
	}
	
	public void fecharPopUp() {
		DriverFactory.getDriver().close(); 
	}
	
	public Object executarJS(String cmd, Object... params) {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getDriver();
		return js.executeScript(cmd, params);
	}
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
		//procurar coluna do registro
		WebElement tabela = DriverFactory.getDriver().findElement(By.xpath("//*[@id='elementosForm:tableUsuarios']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		celula.findElement(By.xpath(".//input")).click();
		
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}
	
	public void esperaImplicita(int segundos) {
		DriverFactory.getDriver().manage().timeouts().implicitlyWait(segundos, TimeUnit.SECONDS);
	}
}
