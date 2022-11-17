import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {

	private WebDriver driver;

	public DSL(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void escrever(By by, String texto){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}

	public void escrever(String id_campo, String texto){
		escrever(By.id(id_campo), texto);
	}
	
	public String obterValorCampo(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public void clicarRadioButton(String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}
	
	public boolean isRadioMarcado(String id_campo) {
		return driver.findElement(By.id(id_campo)).isSelected();
	}
	
	public void selecionarCombo(String id_campo, String texto_visivel) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		combo.selectByVisibleText(texto_visivel);
	}
	
	public void deselecionarCombo(String id ,String ... valores) {
		for(String valor: valores) {
			WebElement element = driver.findElement(By.id(id));
			Select combo = new Select(element);
			combo.deselectByVisibleText(valor);
		}
	}
	
	public void selecionarCheckBox(String id_campo) {
		driver.findElement(By.id(id_campo)).click();
	}
	
	public List<String> obterValoresComboBox(String id_campo) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public String obterValorComboBox(String id_campo) {
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public int obterQuantidadeOpcoesCombo(String id_campo){
		WebElement element = driver.findElement(By.id(id_campo));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public String obterValueElemento(String id_campo) {
		return driver.findElement(By.id(id_campo)).getAttribute("value");
	}
	
	public boolean verificarOpcaoCombo(String id, String texto){
		WebElement element = driver.findElement(By.id(id));
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
		return driver.findElement(By.id(id_campo)).isSelected();
	}
	
	public void clicarBotao(String id_campo) {
		driver.findElement(By.id(id_campo)).click();;
	}
	
	public void clicarLink(String link) {
		driver.findElement(By.linkText(link)).click();
	}
	
	public String obterTexto(By by) {
		 return driver.findElement(by).getText();
	}
	
	public String obterTexto(String id_campo) {
		 return obterTexto(By.id(id_campo));
	}
	
	public String alertaObterTexto(){
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}
	
	public void alertaEscrever(String texto) {
		Alert alert = driver.switchTo().alert();
		alert.sendKeys(texto);
		alert.accept();
	}
	
	public String alertaObterTextoEAceita(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
		
	}
	
	public String alertaObterTextoENega(){
		Alert alert = driver.switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
	}
	
	public void entrarFrame(String id_campo) {
		driver.switchTo().frame(id_campo);
	}
	
	public void sairFrame(){
		driver.switchTo().defaultContent();
	}
	
	public void trocarJanela(String id_campo) {
		driver.switchTo().window(id_campo);
	}
}
