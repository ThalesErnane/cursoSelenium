import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class TesteFramesEJanelas {

	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new ChromeDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finalizar() {
		driver.quit();
	}

	@Test
	public void deveInteragirComFrames() {
		dsl.entrarFrame("frame1"); 
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		dsl.sairFrame();
		dsl.escrever("elementosForm:nome", msg);
	}

	@Test
	public void deveInteragirComJanelas() {
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		driver.close(); // fecha o PopUp
		dsl.trocarJanela(""); // retorna para janela anterior
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}

	@Test
	public void deveInteragirCOmJanelasSemTitulo() {
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(driver.getWindowHandle()); // id da janela principal
		System.out.println(driver.getWindowHandles()); // ids da coleção das janelas
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]); // vai para o POP UP
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]); // retorna para o janela principal
		dsl.escrever(By.tagName("textarea"), "e agora?");
	}
}
