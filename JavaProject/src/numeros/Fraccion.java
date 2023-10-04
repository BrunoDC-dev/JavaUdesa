package numeros;

public class Fraccion extends Numero {

  public Fraccion( int aNumerator, int aDenominator ) {
    if (aNumerator == 0) {
        throw new IllegalArgumentException( "Una fraccion no puede ser cero" );
    }
    if (aDenominator == 1) {
        throw new IllegalArgumentException( "Una fraccion no puede tener denominador 1 porque sino es un entero" );
    }
    type = Fraccion;
    numerator = aNumerator;
    denominator = aDenominator;
  }
  
  public Numero multipliedBy( Numero aMultiplier ) {
    return aMultiplier.multiplicarAFraccion( this );
  }    
  
  public Numero multiplicarAEntero( Entero anEnteroMultiplier ) {
    return new Fraccion(numerator*anEnteroMultiplier.value, denominator);
  }

  public Numero multiplicarAFraccion( Fraccion aFraccionMultiplier ) {
    return Numero.with( numerator * aFraccionMultiplier.numerator,
        denominator * aFraccionMultiplier.denominator );
  }
  

}
