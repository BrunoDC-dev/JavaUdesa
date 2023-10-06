package pharma;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;



import org.junit.jupiter.api.Test;

public class PharmacyTest {
  @Test public void testNewPharmacy() {
    assertTrue( PharmacyWithMedication().medication( "fever" ).isEmpty() );
  }
  
  @Test public void testPharmacyT1() {
    Pharmacy pharma = PharmacyWithMedication(getPARACETAMOL());
    assertFalse( pharma.medication( "fever" ).isEmpty() );  
    assertTrue( pharma.medication( "fever" ).contains( "paracetamol" ) );  
  }
  
  @Test public void testPharmacyT3() {
    assertTrue( PharmacyWithMedication(getPARACETAMOL()).medication( "headache" ).contains( "paracetamol" ) );  
  }
  
  @Test public void testPharmacyT2() {
    assertTrue( PharmacyWithMedication(getPARACETAMOL(), getIBUPROFENO()).medication( "fever" ).contains( "paracetamol" ) );  
  }
  
  @Test public void testPharmacyT4() {
    Pharmacy pharma = PharmacyWithMedication(getPARACETAMOL(), getIBUPROFENO());
    assertTrue( pharma.medication( "headache" ).contains( "paracetamol" ) );  
    assertTrue( pharma.medication( "headache" ).contains( "ibuprofeno" ) );    
  }
  
  @Test public void testPharmacyT5() {
    assertFalse( PharmacyWithMedication(getPARACETAMOL(),getIBUPROFENO()).safeMedicationFor( "headache", "stomachache" ).contains( "ibuprofeno" ) );
  }

  @Test public void testPharmacyT6() {
    Pharmacy pharma = PharmacyWithMedication(getPARACETAMOL(), getCLONACHO());
    assertFalse( pharma.medication( "headache" ).contains( "clonacho" ) );  
    pharma.replacement( "paracetamol", "clonacho" );
    assertTrue( pharma.medication( "headache" ).contains( "clonacho" ) );  
  }

  @Test public void testPharmacyT6b() {
    Pharmacy pharma = PharmacyWithMedication(getPARACETAMOL(), getCLONACHO());
    assertFalse( pharma.safeMedicationFor( "headache", "fever" ).contains( "clonacho" ) );  
    pharma.replacement( "paracetamol", "clonacho" );
    assertTrue( pharma.safeMedicationFor( "headache", "fever" ).contains( "clonacho" ) );  
  }

  @Test public void testPharmacyT7() {
        assertThrows(Exception.class, () -> PharmacyWithMedication(getPARACETAMOL()).register( getPARACETAMOL()),"Droga ya registrada");
      }

  @Test public void testPharmacyT8() { 
    assertEquals("No se puede reemplazar: paracetamol",assertThrows(Exception.class, () -> PharmacyWithMedication().replacement( "paracetamol", "clonacho" )).getMessage());
  }

  @Test public void testPharmacyT9() {
    assertThrows(Exception.class, () -> PharmacyWithMedication(getPARACETAMOL()).replacement( "paracetamol", "clonacho" ),"clonacho no puede ser reemplazo");
  }
  
  @Test public void testPharmacyT10() {
    Pharmacy pharma = PharmacyWithMedication(getPARACETAMOL(), getIBUPROFENO(), getCLONACHO());
    pharma.replacement( "paracetamol", "clonacho");
    testSize(pharma, "headache", "stomachache", 3, 2);
  }
  @Test public void testPharmacyT11() {
    Pharmacy pharma = PharmacyWithMedication(getPARACETAMOL().collateral("heartburn"), getIBUPROFENO(), getCLONACHO());
    pharma.replacement( "paracetamol", "clonacho");
    testSize(pharma,"fever", "heartburn", 2, 1);
  }
  @Test public void testPharmacyT13() {
    Pharmacy pharma = PharmacyWithMedication(getPARACETAMOL().collateral("heartburn"), getIBUPROFENO(), getCLONACHO());
    pharma.replacement( "paracetamol", "clonacho");
    testSize(pharma, "fever", "insomnia", 2, 1);
  }
  
  public Drug getPARACETAMOL() {
      return new Drug( "paracetamol" ).releaves( "fever", "headache");
  }
  public Drug getIBUPROFENO() {
      return new Drug( "ibuprofeno" ).releaves( "headache" ).collateral( "stomachache");
  }
  public Drug getCLONACHO() {
      return new Drug( "clonacho" ).releaves( "anxiety" ).collateral( "testophobia", "insomnia") ;
  }
  private Pharmacy PharmacyWithMedication(Drug... drugs) {
    Pharmacy pharma = new Pharmacy();
    Arrays.stream(drugs).forEach(pharma::register);
    return pharma;
  }
  private void testSize(Pharmacy pharma, String sypnthom,String prevalence , int medicationSize, int safeMedicationSize) {
    assertEquals( medicationSize, pharma.medication( sypnthom ).size() );  
    assertEquals( safeMedicationSize, pharma.safeMedicationFor( sypnthom, prevalence ).size() ); 
}
}
