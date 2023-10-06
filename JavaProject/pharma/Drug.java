package pharma;

import java.util.Arrays;
import java.util.List;

public class Drug {

  private List<String> relieves;
  private String[] collateral = new String[] {};
  private String name;
 
  public Drug( String name ) {
    this.name = name;
  }

  public boolean heals(String symptom) {
    return relieves.contains(symptom);
  }
  
public boolean aggravate(String prevalence) {
    return Arrays.asList(collateral).contains(prevalence);
}


  public Drug releaves( String ... symptoms ) {
    relieves = Arrays.asList( symptoms );
    return this;
  }
  
  public Drug collateral( String ... symptoms ) {
    collateral = symptoms;
    return this;
  }

  public String name() { return name; }
  
}
