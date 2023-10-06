package pharma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class Pharmacy {

  private List<Drug> drugs = new ArrayList<>();
  private Map<String, String> replacements = new HashMap<>();


  public List<String> medication(String symptom) {
    //list = map (\ nameD drug -> healsD drug symptom) drugs
    List<String> list = drugs.stream()
            .filter(drug -> drug.heals(symptom))
            .map(Drug::name)
            .collect(Collectors.toList());
    //list = list ++ map (\key drug -> key `elem` list) replacements 
    list.addAll(replacements.keySet().stream()
            .filter(list::contains)
            .map(replacements::get)
            .collect(Collectors.toList()));

    return list;
}


  public void register( Drug heal ) {
    if (drugExists(heal.name())) {
        throw new RuntimeException("Droga ya registrada");
    } else {
        drugs.add(heal);
    }
  }

  public List<String> safeMedicationFor( String sypnthom, String prevalence ) {

//candidateNames = map name drugs
  List<String> candidateNames = drugs.stream()
      .map(Drug::name)
      .collect(Collectors.toList());

// list = map nameD (filter (\drug -> not (aggravateD drug prevalence) &&  (healsD sypnthom)) drugs )
    List<String> list = drugs.stream()
    .filter( (drug) -> !drug.aggravate( prevalence ) && drug.heals( sypnthom ))
    .map( (drug) -> drug.name() )
    .collect( Collectors.toList() );
  // list = list ++ map (\key drug -> getKeyD key `elem` candidateNames && not (aggravateD (nameD drug) prevelence)) replacements
list.addAll(replacements.entrySet().stream()
    .filter(entry -> candidateNames.contains(entry.getKey()))
    .filter(entry -> !drugNamed(entry.getValue()).aggravate(prevalence))
    .map(Map.Entry::getValue)
    .collect(Collectors.toList()));

    return list;
  }

  public void replacement(String s1, String s2) {
    if (!drugExists(s1)) {
        throw new IllegalStateException("No se puede reemplazar: " + s1);
    }

    if (!drugExists(s2)) {
        throw new RuntimeException(s2 + " no puede ser reemplazo");
    }

    replacements.put(s1, s2);
}

private boolean drugExists(String name) {
    //any (map (\drug -> nameD drug == name) drugs)
    return drugs.stream().anyMatch(drug -> drug.name().equals(name));
}

  public Drug drugNamed( String x ) {
    //drug = head( map (\drug -> nameD drug == x) drugs)
    return drugs.stream()
    .filter(drug -> drug.name().equals(x))
    .findFirst()
    .orElseThrow(NoSuchElementException::new);
  }
  
}
  

