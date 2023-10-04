package numeros;

public class Entero extends Numero {

  public Entero( int aValue ) {
    type = Entero;
    value = aValue;
  }

  public Numero multipliedBy( Numero aMultiplier ) {
    return aMultiplier.multiplicarAEntero( this );
    
//    if( aMultiplier.type == Entero) {
//      return multiplicarAEntero( aMultiplier );
//    } else {  //fraccion
//      return multiplicarAFraccion( aMultiplier );
//    }
  }

  public Numero multiplicarAEntero( Entero anEnteroMultiplier ) {
    return new Entero( value * anEnteroMultiplier.value );
  }

  public Numero multiplicarAFraccion( Fraccion aFraccionMultiplier ) {
    return new Fraccion(value *aFraccionMultiplier.numerator,aFraccionMultiplier.denominator);
  }
  
  
}
