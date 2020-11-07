
package br.com.caelum.leilao.teste;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class LeilaoTeste {

	@Test
	public void deveReceberUmLance() {
		Leilao leilao = new Leilao("Notebook Acer");
		assertEquals(0,  leilao.getLances().size());
		
		leilao.propoe(new Lance(new Usuario("Steve Jobs"), 2000));
		
		assertEquals(1,  leilao.getLances().size());
		assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.00001);		
	}
	
	 @Test
	 public void deveReceberVariosLances() {
		 Leilao leilao = new Leilao("Notebook Acer");
		 leilao.propoe(new Lance(new Usuario("Sandro Aparecido"), 2000.0));
		 leilao.propoe(new Lance(new Usuario("Regina Silva"), 3000.0));
		 
		 assertEquals(2, leilao.getLances().size());
		 assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
		 assertEquals(3000.0, leilao.getLances().get(1).getValor(), 0.0001);
		 	 
	 }
	 
	 @Test
	 public void naoDeveAceitarDoislancesSeguidoDoMesmoUsuario() {
		 Leilao leilao = new Leilao("Notebook Acer");
		 Usuario sandro = new Usuario("Sandro Aparecido");
		 
		 leilao.propoe(new Lance(sandro, 2000.0));
		 leilao.propoe(new Lance(sandro, 3000.0));
		 
		 assertEquals(1, leilao.getLances().size());
		 assertEquals(2000.0, leilao.getLances().get(0).getValor(), 0.0001);
		 
	 }
	 
	 @Test
	 public void naoDeveAceitarmaisDoQue5LancesDeUmmesmoUsuario() {
		 Leilao leilao = new Leilao("Notebook Acer");
		 Usuario sandro = new Usuario("Sandro Aparecido");
		 Usuario regina = new Usuario("Regina Castro");
		 
		 leilao.propoe(new Lance(sandro, 2000.0));
		 leilao.propoe(new Lance(regina, 3000.0));
		 
		 leilao.propoe(new Lance(sandro, 4000.0));
		 leilao.propoe(new Lance(regina, 5000.0));
		 
		 leilao.propoe(new Lance(sandro, 6000.0));
		 leilao.propoe(new Lance(regina, 7000.0));
		 
		 leilao.propoe(new Lance(sandro, 8000.0));
		 leilao.propoe(new Lance(regina, 9000.0));
		 
		 leilao.propoe(new Lance(sandro, 10000.0));
		 leilao.propoe(new Lance(regina, 11000.0));
		 
		 //deve ser ignorado
		 leilao.propoe(new Lance(regina, 12000.0));
		 
		 assertEquals(10, leilao.getLances().size());
		 assertEquals(11000.0, leilao.getLances().get(leilao.getLances().size() -1).getValor(), 0.00001);
		 
	 }
	 
	 
	 
	 
	 
	 
}
