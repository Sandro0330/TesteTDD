package br.com.caelum.leilao.teste;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// parte 1: cenario
		Usuario sandro = new Usuario("Sandro");
		Usuario maria = new Usuario("Maria");
		Usuario uilson = new Usuario("Uilson");
		
		Leilao leilao = new Leilao("Playstation 4 Novo"); 

		leilao.propoe(new Lance(sandro, 700.0));
		leilao.propoe(new Lance(maria, 750.0));
		leilao.propoe(new Lance(uilson, 800.0));
				
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: validação
		double maiorEsperado = 800;
		double menorEsperado = 700;
		
		Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.00001);
	}
}
