package truco;

public class Carta {
	private String palo;
	private int numero;
	public Carta(String palo, int i) {
		this.palo = palo;
		this.numero = i;
	}	
	public static Carta anchoDeBasto() {
		return with("basto", 1);
	}
	public static Carta with (String palo, int numero) {
		return new Carta(palo, numero);
	}
    public static Carta basto(int i) {
        return with("basto", i);
    }
	public static Carta oro(int i) {
		return with("oro", i);
	}
	public static Carta copa(int i) {
		return with("copa", i);
	}
	public static Carta espada(int i) {
		return with("espada", i);
	}

	public boolean equals (Object otraCarta) {
		
		if(Carta.class.isInstance(otraCarta)){
			Carta otra = Carta.class.cast(otraCarta);	
			if (this.palo == otra.palo && this.numero == otra.numero) {
				return true;
			}
		}
		return false;
	}

}
