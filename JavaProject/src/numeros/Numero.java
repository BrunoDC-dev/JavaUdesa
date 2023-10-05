package numeros;

public abstract class Numero {
    static public String CanNotDivideByZero = "No se puede dividir por cero!!!!!!";

    public static String Entero = "Entero";
    public static String Fraccion = "Fraccion";
    public String type;
    public int value;
    public int numerator;
    public int denominator;

    public static Numero with( int aValue ) {
        return new Entero( aValue );
    }

    public static Numero with( int aDividend, int aDivisor ) {
        if (aDivisor == 0) {
            throw new IllegalArgumentException( CanNotDivideByZero );
        }

        if (aDividend == 0) {
            return with( 0 );
        }

        if (aDivisor < 0) {
            return with( -aDividend, -aDivisor );
        }

        int greatestCommonDivisor = Numero.greatestCommonDivisor( aDividend, aDivisor );
        int numerator = aDividend / greatestCommonDivisor;
        int denominator = aDivisor / greatestCommonDivisor;

        if (denominator == 1) {
            return with( numerator );
        }

        return new Fraccion( numerator, denominator );
    }
    public abstract Numero substractedBy( Numero aMultiplier );
    public abstract Numero restarEntero(Entero anEntero);
    public abstract Numero restarFraccion(Fraccion aFraccion);
    
    public abstract Numero multipliedBy( Numero aMultiplier );
    public abstract Numero multiplicarAEntero( Entero anEnteroMultiplier );
    public abstract Numero multiplicarAFraccion( Fraccion aFraccionMultiplier );

    public abstract Numero addedTo (Numero aNumeroAdder);
    public abstract Numero sumarEntero (Entero anEnteroAdder);
    public abstract Numero sumarFraccion (Fraccion aFraccionAdder);

    public abstract Numero dividedBy(Numero aDivisor) ;
    public abstract Numero dividirFraccion(Fraccion aFraccionDivisor);
    public abstract Numero dividirEntero(Entero anEnteroDivisor);
    

    public Numero greatestCommonDivisorWith( int anEntero ) {
        if (type == Entero) {
            return new Entero( greatestCommonDivisor( value, anEntero ) );
        }

        throw new UnsupportedOperationException( "Tipo de número no soportado" );
    }

    public abstract Numero negated();
    public abstract boolean isNegative();
    public abstract boolean isOne() ;
    public abstract boolean isZero() ;
    public abstract String toString() ;
    public abstract boolean equals( Object anObject );
    public abstract boolean equalsToEntero( Entero anEntero );
    public abstract boolean equalsToFraccion( Fraccion aFraccion );
    public abstract int hashCode();

    // accessors
    public int denominator() {  return denominator; }
    public int value() {        return value;       }
    public int numerator() {    return numerator;   }

    public static int greatestCommonDivisor( int a, int b ) {
        int temp;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
