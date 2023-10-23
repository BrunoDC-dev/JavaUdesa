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
  public Numero addedTo( Numero aNumero ) {
    return aNumero.sumarFraccion( this );
  }
  public Numero sumarEntero( Entero anEntero ) {
    return new Fraccion( numerator + anEntero.value * denominator, denominator ) ;
  }
  public Numero sumarFraccion ( Fraccion aFraccion ) {
    return new Fraccion( numerator * aFraccion.denominator + denominator * aFraccion.numerator,
        denominator * aFraccion.denominator );
  }
  public Numero substractedBy ( Numero aNumero ) {
    return aNumero.restarFraccion( this );
  }
  public Numero restarEntero ( Entero anEntero ) {
      return new Fraccion( anEntero.value*denominator -numerator, denominator);
  }
  public Numero restarFraccion (Fraccion aFraccion) {
    int newNumerator = ( denominator * aFraccion.numerator )- ( numerator * aFraccion.denominator ) ;
    int newDenominator = denominator * aFraccion.denominator;
    return with( newNumerator, newDenominator );
  }


  public Numero multipliedBy( Numero aMultiplier ) {
    return aMultiplier.multiplicarAFraccion( this );
  }    
  
  public Numero multiplicarAEntero( Entero anEnteroMultiplier ) {
    return new Fraccion(numerator*anEnteroMultiplier.value, denominator);
  }

  public Numero multiplicarAFraccion( Fraccion aFraccionMultiplier ) {
    return new Fraccion( numerator * aFraccionMultiplier.numerator,
        denominator * aFraccionMultiplier.denominator );
  }
  

  public Numero dividedBy( Numero aDivisor ) {
    if (aDivisor.isZero()) {
      throw new RuntimeException(CanNotDivideByZero);
  }
    return aDivisor.dividirFraccion( this );
  }
  public Numero dividirEntero( Entero anEntero ) {
    return new Fraccion( denominator,numerator * anEntero.value );
  }
  public Numero dividirFraccion( Fraccion aFraccion ) {
    return new Fraccion( denominator* aFraccion.numerator, numerator * aFraccion.denominator );
  }
  public Numero negated () {
    return new Fraccion( -numerator, denominator );
  }
  public boolean isNegative() {
    return numerator < 0;
  }
  public String toString() {
    return "" + numerator + "/" + denominator;
  }
  public boolean equals (Object anObject){
    if (Numero.class.isInstance(anObject)) {
      Numero aNumero = (Numero) anObject;
      return aNumero.equalsToFraccion(this);
    }
    return false;
  }
  public boolean equalsToEntero (Entero anEntero) {
    return numerator / denominator == anEntero.value;
  }
  public boolean equalsToFraccion (Fraccion aFraccion) {
    return numerator / denominator == aFraccion.numerator/ aFraccion.denominator;
  }
  public int  hashCode() {
    return Double.hashCode( (double) numerator / (double) denominator );
  }
}
