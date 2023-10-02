package pharma;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;

public class PharmacyTest {
  
  @Test public void testNewPharmacy() {
    assertTrue( new Pharmacy().medication( "fever" ).isEmpty() );
  }
  
  @Test public void testPharmacyT1() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    
    assertFalse( pharma.medication( "fever" ).isEmpty() );  
    assertTrue( pharma.medication( "fever" ).contains( "paracetamol" ) );  
  }

  @Test public void testPharmacyT3() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    
    assertTrue( pharma.medication( "headache" ).contains( "paracetamol" ) );  
  }
  
  @Test public void testPharmacyT2() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    pharma.register( new Drug( "ibuprofeno" ).relieves( "headache" ).collateral( "stomachache") );

    assertTrue( pharma.medication( "fever" ).contains( "paracetamol" ) );  
  }
  
  @Test public void testPharmacyT4() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    pharma.register( new Drug( "ibuprofeno" ).relieves( "headache" ).collateral( "stomachache") );

    assertTrue( pharma.medication( "headache" ).contains( "paracetamol" ) );  
    assertTrue( pharma.medication( "headache" ).contains( "ibuprofeno" ) );    
  }
  
  @Test public void testPharmacyT5() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    pharma.register( new Drug( "ibuprofeno" ).relieves( "headache" ).collateral( "stomachache") );

    assertFalse( pharma.safeMedicationFor( "headache", "stomachache" ).contains( "ibuprofeno" ) );
  }
    
  
  @Test public void testPharmacyT6() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    pharma.register( new Drug( "clonacho" ).relieves( "anxiety" ).collateral( "testophobia", "insomnia") );


    assertFalse( pharma.medication( "headache" ).contains( "clonacho" ) );  
    pharma.replacement( "paracetamol", "clonacho" );
    assertTrue( pharma.medication( "headache" ).contains( "clonacho" ) );  
    
  }

  @Test public void testPharmacyT6b() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    pharma.register( new Drug( "clonacho" ).relieves( "anxiety" ).collateral( "testophobia", "insomnia") );


    assertFalse( pharma.safeMedicationFor( "headache", "fever" ).contains( "clonacho" ) );  
    pharma.replacement( "paracetamol", "clonacho" );
    assertTrue( pharma.safeMedicationFor( "headache", "fever" ).contains( "clonacho" ) );  
    
  }

  @Test public void testPharmacyT7() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    try {
      pharma.register( new Drug( "paracetamol" ).relieves( "headache") );
      fail( "Ouch" );
    } catch ( Exception e ) {
      assertEquals( "Droga ya registrada", e.getMessage() );
    }
  }

  @Test public void testPharmacyT8() {
    Pharmacy pharma = new Pharmacy();
    try {
      pharma.replacement( "paracetamol", "clonacho" );;
      fail( "Ouch" );
    } catch ( Exception e ) {
      assertEquals( "No se puede reemplazar: paracetamol", e.getMessage() );
    }
  }

  @Test public void testPharmacyT9() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    try {
      pharma.replacement( "paracetamol", "clonacho" );
      fail( "Ouch" );
    } catch ( Exception e ) {
      assertEquals( "clonacho no puede ser reemplazo", e.getMessage() );
    }

  }
  
  @Test public void testPharmacyT10() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache") );
    pharma.register( new Drug( "ibuprofeno" ).relieves( "headache" ).collateral( "stomachache") );
    pharma.register( new Drug( "clonacho" ).relieves( "anxiety" ).collateral( "testophobia", "insomnia") );
    
    pharma.replacement( "paracetamol", "clonacho" );
    
    assertEquals( 3, pharma.medication( "headache" ).size() );  
    assertEquals( 2, pharma.safeMedicationFor( "headache", "stomachache" ).size() );  
  }

  @Test public void testPharmacyT11() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache" ).collateral( "heartburn" ) );
    pharma.register( new Drug( "ibuprofeno" ).relieves( "headache" ).collateral( "stomachache") );
    pharma.register( new Drug( "clonacho" ).relieves( "anxiety" ).collateral( "testophobia", "insomnia") );
    
    pharma.replacement( "paracetamol", "clonacho" );
    
    assertEquals( 2, pharma.medication( "fever" ).size() );  
    assertEquals( 1, pharma.safeMedicationFor( "fever", "heartburn" ).size() );  
  }

  @Test public void testPharmacyT13() {
    Pharmacy pharma = new Pharmacy();
    pharma.register( new Drug( "paracetamol" ).relieves( "fever", "headache" ).collateral( "heartburn" ) );
    pharma.register( new Drug( "ibuprofeno" ).relieves( "headache" ).collateral( "stomachache") );
    pharma.register( new Drug( "clonacho" ).relieves( "anxiety" ).collateral( "testophobia", "insomnia") );
    
    pharma.replacement( "paracetamol", "clonacho" );
    
    assertEquals( 2, pharma.medication( "fever" ).size() );  
    assertEquals( 1, pharma.safeMedicationFor( "fever", "insomnia" ).size() );  
  }
  
}
