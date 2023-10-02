package booleans;

public class False extends Boolean {
  public Boolean yy( Boolean v ) {      return this;    }
  public Boolean oo( Boolean v ) {      return v;       }
  public Boolean not() {                return True();  }
}