package futbol;

public class jugador {
    void jugar ()
    {
        correrLaPelota();
    }
    void correrLaPelota()
    {
        System.out.println("Corriendo la pelota");
    }
    void jugarCon(jugador unJugador){
        unJugador.jugar();
        jugar();
    }
}

class Defensor extends jugador {
    void jugar(){
        protestar();
        super.jugar();
        quitarPelota();
    }
    void protestar(){
        System.out.println("Protestando");
    }
    void quitarPelota(){
        System.out.println("Quitando la pelota");
    }
}

class Delantero extends jugador {
    void jugar(){
        super.jugar();
        patearPelota();
    }
    void patearPelota(){
        System.out.println("Pateando la pelota");
    }
}

class Arquero extends jugador {
    void jugar(){
        atajarPelota();
    }
    void atajarPelota(){
        System.out.println("Atajando la pelota");
    }
}


class Main {
    public static void main(String[] args) {
        new Arquero().jugarCon(new Delantero());
    }
}
