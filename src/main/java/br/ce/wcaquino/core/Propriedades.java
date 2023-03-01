package br.ce.wcaquino.core;

public class Propriedades {

	public static boolean FECHAR_BROWSER = false; // se for true fecha o browser a cada teste 
	
	public static Browsers browser = Browsers.CHROME; // padrão será 
	
	public enum Browsers {
		CHROME, 
		FIREFOX, 
		EDGE
	}
	
}
