package truco;

import java.util.ArrayList;

public class Ronda {
	
	public static String noEsTurnoErrorDescription = "No es turno";
	private ArrayList<Carta> cartasDeManoDisponibles;
	private ArrayList<Carta> cartasDePieDisponibles;
	private ArrayList<Carta> cartasDeManoJugadas;
	private ArrayList<Carta> cartasDePieJugadas;
	private String turno;

	public Ronda(ArrayList<Carta> cartasDeManoDisponibles, ArrayList<Carta> cartasDePieDisponibles,
							ArrayList<Carta> cartasDeManoJugadas, ArrayList<Carta> cartasDePieJugadas ) {
	}	
	public Ronda() {	}
	
	public Ronda with(ArrayList<Carta> cartasDeMano, ArrayList<Carta> cartasDePie ) {
		cartasDeManoDisponibles = new ArrayList<Carta> (cartasDeMano);
		cartasDePieDisponibles = cartasDePie;
		cartasDeManoJugadas = new ArrayList<Carta>();
		cartasDePieJugadas = new ArrayList<Carta>();

		return new Ronda(cartasDeManoDisponibles, cartasDePieDisponibles, cartasDeManoJugadas, cartasDePieJugadas );
		
	}
	
	public void setTurno(String turno) {
		this.turno = turno;
	}

	public boolean turnoMano() {
		return getTurno()== "mano" ;
	}

	public boolean turnoPie() {
		return getTurno()== "pie";
	}

	public void manoJuega(Carta anchoDeBasto) {
		if(turno == "mano") {
			setTurno("pie");
		}
		else {
			throw new Error (noEsTurnoErrorDescription);
		}
	}

	
//Accesors

private String getTurno() {
	return turno;
	
}
public ArrayList<Carta> getCartasDeManoDisponibles() {
	return cartasDeManoDisponibles;
}
public ArrayList<Carta> getCartasDePieDisponibles() {
	return cartasDePieDisponibles;
}
public void pieJuega (Carta carta){
	
}
}