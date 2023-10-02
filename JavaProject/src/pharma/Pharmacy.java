package pharma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class Pharmacy {
  
  private List<Drug> drugs = new ArrayList();
  private Map<String, String> replacements = new HashMap();

  public List<String> medication( String sypnthom ) {
    List<String> list = new ArrayList<>();
    for (Drug drug : drugs) {
        if (drug.heals(sypnthom)) {
            list.add(drug.name());
        }
    }
    
    Set<String> keys = replacements.keySet();
    for (String key : keys) {
        if (list.contains(key)) {
            list.add(replacements.get(key));
        }
    }
    return list;
  }

  public void register( Drug heal ) {
    boolean drugExists = false;
    for (int i = 0; i < drugs.size(); i++) {
        if (drugs.get(i).name().equals(heal.name())) {
            drugExists = true;
            break;
        }
    }

    if (drugExists) {
        throw new RuntimeException( "Droga ya registrada" );
    } else {
        drugs.add(heal);
    }
  }

  public List<String> safeMedicationFor( String sypnthom, String prevalence ) {
    List<Drug> candidates = new ArrayList<>();
    for (int i = 0; i < drugs.size(); i++) {
        Drug drug = drugs.get(i);
        if (drug.heals(sypnthom)) {
            candidates.add(drug);
        }
    }

    List<String> candidateNames = new ArrayList<>();
    for (int i = candidates.size() - 1; i >= 0; i--) {
        Drug drug = candidates.get(i);
        candidateNames.add(drug.name());
    }
    
    // list = map nameD (filter (\drug -> not (aggravateD drug prevalence) ) candidates )
    List list = candidates.stream()
        .filter( (drug) -> !drug.aggravate( prevalence ) )
        .map( (drug) -> drug.name() )
        .collect( Collectors.toList() );
    
    for (Map.Entry<String, String> entry : replacements.entrySet()) {
      String key = entry.getKey();
      String value = entry.getValue();
      if (candidateNames.contains(key) && !drugNamed(value).aggravate(prevalence)) {
          list.add(value);
      }
    }
    return list;
  }

  public void replacement( String s1, String s2  ) {
    if ( !drugs.stream().anyMatch( (any) -> any.name().equals( s1 ) ) ) 
      throw new RuntimeException( "No se puede reemplazar: " + s1 );
    
    if ( !drugs.stream().anyMatch( (any) -> any.name().equals( s2 ) ) ) 
      throw new RuntimeException( s2 + " no puede ser reemplazo" );
    
    replacements.put( s1, s2 );
    
  }

  public Drug drugNamed( String x ) {
    for (Drug drug : drugs) {
      if (drug.name().equals(x)) {
        return drug;
      }
    }
    throw new NoSuchElementException(); 
  }
  
  
}
  

