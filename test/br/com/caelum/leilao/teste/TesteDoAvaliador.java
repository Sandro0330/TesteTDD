package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

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

		leilao.propoe(new Lance(sandro, 250.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(uilson, 400.0));
				
		// parte 2: ação
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		//parte 3: validação
		
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderComApenasUmLance() {
		 Usuario joao = new Usuario("João");
		 Leilao leilao = new Leilao("Computador");
		 
		 leilao.propoe(new Lance(joao, 1000.0));
		 
		 Avaliador leiloeiro = new Avaliador();
		 leiloeiro.avalia(leilao);
		 
		 assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.0001);
		 assertEquals(1000.0, leiloeiro.getMenorLance(), 0.0001);
		 
	}	
	
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		Usuario sandro = new Usuario("Sandro");
		Usuario maria = new Usuario("Maria");
		Leilao leilao = new Leilao("Notebbok");
		
		leilao.propoe(new Lance(sandro, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(sandro, 300.0 ));
		leilao.propoe(new Lance(maria, 400.0 ));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(400.0, maiores.get(0).getValor(), 0.0001);
		assertEquals(300.0, maiores.get(1).getValor(), 0.0001);
		assertEquals(200.0, maiores.get(2).getValor(), 0.0001);
		
	}	
	
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
	
}
