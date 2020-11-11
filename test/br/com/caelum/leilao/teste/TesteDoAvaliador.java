package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.builder.CriadorDeLeilao;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

public class TesteDoAvaliador {
	private Avaliador leiloeiro;
	private Usuario sandro;
	private Usuario maria;
	private Usuario uilson;
	
	@Before
	public void criaAvaliador() {
		this.leiloeiro = new Avaliador();
		// parte 1: cenario
		this.sandro = new Usuario("Sandro");
		this.maria = new Usuario("Maria");
		this.uilson = new Usuario("Uilson");
		
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		Leilao leilao = new Leilao("Playstation 4 Novo"); 

		leilao.propoe(new Lance(sandro, 250.0));
		leilao.propoe(new Lance(maria, 300.0));
		leilao.propoe(new Lance(uilson, 400.0));
				
		// parte 2: ação
		//criaAvaliador(); a anotação @Before substitui esse trecho de código
		leiloeiro.avalia(leilao);
		
		//parte 3: validação
		
		assertEquals(400.0, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(250.0, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void deveEntenderComApenasUmLance() {
		
		 Leilao leilao = new Leilao("Computador");
		 
		 leilao.propoe(new Lance(maria, 1000.0));
		 
		 //criaAvaliador(); a anotação @Before substitui esse trecho de código
		 leiloeiro.avalia(leilao);
		 
		 assertEquals(1000.0, leiloeiro.getMaiorLance(), 0.0001);
		 assertEquals(1000.0, leiloeiro.getMenorLance(), 0.0001);
		 
	}	
	
	
	@Test
	public void deveEncontrarOsTresMaioresLances() {
		
		Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
				.lance(maria, 300.0)
				.lance(uilson, 400.0)
				.lance(maria, 500.0)		
				.lance(uilson, 600.0)
				.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> maiores = leiloeiro.getTresMaiores();
		assertEquals(3, maiores.size());
		assertEquals(600.0, maiores.get(0).getValor(), 0.0001);
		assertEquals(500.0, maiores.get(1).getValor(), 0.0001);
		assertEquals(400.0, maiores.get(2).getValor(), 0.0001);
		
	}	
	
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        //criaAvaliador();  a anotação @Before substitui esse trecho de código
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
	
}
