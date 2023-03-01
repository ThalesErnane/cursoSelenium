package br.ce.wcaquino.page;
import org.openqa.selenium.By;

import br.ce.wcaquino.core.BasePage;

public class FramesEJanelasPage extends BasePage {
	
	public void setFrame() {
		dsl.entrarFrame("frame1"); 
	}
	
	public void setFrameBotao() {
		dsl.clicarBotao("frameButton");
	}

	public void setSairFrame() {
		dsl.sairFrame();
	}

	public void setNome(String msg) {
		dsl.escrever("elementosForm:nome", msg);
	}

	public void setTextArea(String msg) {
		dsl.escrever(By.tagName("textarea"), msg);
	}
}
