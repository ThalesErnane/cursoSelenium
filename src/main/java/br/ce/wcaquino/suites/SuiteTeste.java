package br.ce.wcaquino.suites;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.core.DriverFactory;
import br.ce.wcaquino.test.TesteCadastro;
import br.ce.wcaquino.test.TesteRegrasCadastro;

@RunWith(Suite.class)
@SuiteClasses({
	TesteCadastro.class,
	TesteRegrasCadastro.class
	// TesteCampoTreinamento.class
})
public class SuiteTeste {

	// trabalhando com SUITE
	// fecha o browser após a execução de todos os testes 
	@AfterClass
	public static void finalizaTudo() {
		DriverFactory.killDriver();
	}
	
}
