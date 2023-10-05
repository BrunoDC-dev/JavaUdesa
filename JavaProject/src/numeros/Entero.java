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

  public Numero addedTo( Numero aNumero ) {
    return aNumero.sumarEntero( this );
  }
  public Numero sumarEntero( Entero anEntero ) {
    return new Entero( value + anEntero.value );
  }
  public Numero sumarFraccion ( Fraccion aFraccion ) {
    return new Fraccion( value * aFraccion.denominator + aFraccion.numerator,
        aFraccion.denominator );
  }
  public Numero substractedBy ( Numero aNumero ) {
    return aNumero.restarEntero( this );
  }
  public Numero restarEntero ( Entero anEntero ) {
    return new Entero( anEntero.value - value);
  }
  public Numero restarFraccion (Fraccion aFraccion) {
    return new Fraccion( aFraccion.numerator - value * aFraccion.denominator,
        aFraccion.denominator );
  }
  public Numero multiplicarAEntero( Entero anEnteroMultiplier ) {
    return new Entero( value * anEnteroMultiplier.value );
  }

  public Numero multiplicarAFraccion( Fraccion aFraccionMultiplier ) {
    return new Fraccion(value *aFraccionMultiplier.numerator,aFraccionMultiplier.denominator);
  }
  public Numero dividedBy ( Numero aDivisor ) {
    if (aDivisor.isZero()) {
      throw new RuntimeException(CanNotDivideByZero);
    }
    return aDivisor.dividirEntero( this );
  }
  public Numero dividirEntero ( Entero anEntero ) {
    return new Entero( anEntero.value / value );
  }
  public Numero dividirFraccion ( Fraccion aFraccion ) {
    return new Fraccion( aFraccion.numerator, aFraccion.denominator*value );
  }
}
