package br.ce.wcaquino.test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.wcaquino.core.BaseTest;
import br.ce.wcaquino.core.DSL;
import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.page.FramesEJanelasPage;

public class TesteFramesEJanelas extends BaseTest {

	private DSL dsl;
	private FramesEJanelasPage page;
	
	@Before
	public void inicializa() {
		DriverFactory.getDriver().get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL();
	}

	@Test
	public void deveInteragirComFrames() {
		page.setFrame();
		page.setFrameBotao();
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		page.setSairFrame();
		page.setNome(msg);
	}

	@Test
	public void deveInteragirComJanelas() {
		//page.popUpEasy
		dsl.clicarBotao("buttonPopUpEasy");
		dsl.trocarJanela("Popup");
		page.setTextArea("Deu certo?");
		dsl.fecharPopUp(); // fecha o PopUp
		dsl.trocarJanela(""); // retorna para janela anterior
		page.setTextArea("e agora?");
	}

	@Test
	public void deveInteragirCOmJanelasSemTitulo() {
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(DriverFactory.getDriver().getWindowHandle()); // id da janela principal
		System.out.println(DriverFactory.getDriver().getWindowHandles()); // ids da coleção das janelas
		dsl.trocarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[1]); // vai para o POP UP
		page.setTextArea("Deu certo?");
		dsl.trocarJanela((String) DriverFactory.getDriver().getWindowHandles().toArray()[0]); // retorna para o janela principal
		page.setTextArea("e agora?");
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = DriverFactory.getDriver().findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y); // eixo scroll (x=horizontal, y=vertical)
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
}
