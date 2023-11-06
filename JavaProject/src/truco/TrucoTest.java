package truco;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrucoTest {
	Ronda ronda = new Ronda();
	ArrayList<Carta> cartasDeMano; 
	ArrayList<Carta> cartasDePie;

	@BeforeEach
	public void setUp (){
		ronda = new Ronda();
		cartasDeMano = new ArrayList<Carta>(Arrays.asList(Carta.basto(1), Carta.basto(7), Carta.oro(1)));
		cartasDePie = new ArrayList<Carta>(Arrays.asList(Carta.copa(1), Carta.copa(7), Carta.espada(1)));
		ronda.with(cartasDeMano, cartasDePie);
	}
	@Test
	public void test01SiempreEmpiezaJugandoLaMano() {
		Ronda ronda = new Ronda();
		ronda.setTurno("mano");

		assertTrue(ronda.turnoMano());
		assertFalse(ronda.turnoPie());
	}
	
	@Test
	public void test02EnElPrimerEnfrentamientoLuegoDeLaManoJuegaElPie() {
		ronda.setTurno("mano");
		ronda.manoJuega(Carta.basto(1));
		
		assertFalse(ronda.turnoMano());
		assertTrue(ronda.turnoPie());		
	}
	
	@Test
	public void test03() {
		ronda.setTurno("mano");
		ronda.manoJuega(Carta.basto(1));
		
			assertThrowsLike(()->ronda.manoJuega(Carta.basto(7)), ronda.noEsTurnoErrorDescription);
			assertFalse(ronda.turnoMano());
			assertTrue(ronda.turnoPie());	
			//asertar que la carta jugada es efectivamente el anchoDeBasto
			
		
		
	
	}
	@Test public void test04(){
		ronda.setTurno("mano");
		assertThrowsLike(()->ronda.pi eJuega(ronda.getCartasDePieDisponibles().get(0)), ronda.noEsTurnoErrorDescription);
	} 
	private void assertThrowsLike (Executable executable , String message) {
		assertEquals(message, assertThrows( Error.class, executable ).getMessage());
	}
}




