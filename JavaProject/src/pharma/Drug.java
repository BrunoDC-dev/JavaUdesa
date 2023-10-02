package pharma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Drug {

    private List<String> relieves;
    private String[] collateral;
    private String name;

    public Drug(String name) {
        this.name = name;
        relieves = new ArrayList<>();
        collateral = new String[]{};
    }

    public boolean heals(String symptom) {
        return relieves.contains(symptom);
    }

    public boolean aggravate(String prevalence) {
        return Arrays.asList(collateral).contains(prevalence);
    }

    public Drug relieves(String... symptoms) {
        relieves.addAll(Arrays.asList(symptoms));
        return this;
    }

    public Drug collateral(String... symptoms) {
        collateral = symptoms;
        return this;
    }

    public String name() {
        return name;
    }
}
