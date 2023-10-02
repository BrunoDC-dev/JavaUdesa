package booleans;

public class True extends Boolean {
  public Boolean yy( Boolean v ) {      return v;       }
  public Boolean oo( Boolean v ) {      return this;    }
  public Boolean not() {                return False(); }
}

