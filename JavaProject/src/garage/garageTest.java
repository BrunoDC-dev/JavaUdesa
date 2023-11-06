package garage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
public class garageTest {
    private Garage garage;
    @BeforeEach public void setUp() {
        garage = new Garage();
    }

    @Test public void testCarInitializesWith0Cars() {
      asssertGarageStatus(0, 0, 0, 0, 0, 0);
    }
    @Test public void testaddInivitedCar (){
        garage.addInivitedCar(2,"1234");
        asssertGarageStatus(1, 150, 2, 0, 0, 1);
    }
    @Test public void testAddMemberCar (){
        garage.addMemberCar(3, "122");
        asssertGarageStatus(1, 100, 3, 1, 0, 0);
    }
    @Test public void testAddReferenceCar (){
        garage.addReferenceCar(2 , "111");	
        asssertGarageStatus(1, 170, 2, 0, 1, 0);
    }

    @Test public void testAddCasualCarWith0People (){
        assertThrowsLike(() -> garage.addInivitedCar(0,"123"), "There can't be less than 1 person in a car");
    }
    @Test public void testAddMemberCarWith0People (){
        assertThrowsLike(() -> garage.addMemberCar(0, "1234"), "There can't be less than 1 person in a car");
    }
    @Test public void testAddReferenceCarWith0People (){
        assertThrowsLike(() -> garage.addReferenceCar(0, "232"), "There can't be less than 1 person in a car");
    }
    @Test public void testTryToAddTheSameCarTwice (){
        garage.addInivitedCar(2, "123");
        assertThrowsLike(() -> garage.addInivitedCar(2, "123"), "Car Already Exists");
    }
    @Test public void testTryToAddTheSameMemberCarTwice (){
        garage.addMemberCar(2, "123");
        assertThrowsLike(() -> garage.addMemberCar(2, "123"), "Car Already Exists");
    }
    @Test public void testTryToAddTheSameReferenceCarTwice (){
        garage.addReferenceCar(2, "123");
        assertThrowsLike(() -> garage.addReferenceCar(2, "123"), "Car Already Exists");
    }

    @Test public void testaddCasualAndMemberCar (){
        garage.addInivitedCar(2, "123").addMemberCar(3, "1234");
        asssertGarageStatus(2, 250, 5, 1, 0, 1);
    }
    @Test public void testaddCasualAndReferenceCar (){
        garage.addInivitedCar(2, "123").addReferenceCar(3, "1234");
        asssertGarageStatus(2, 380, 5, 0, 1, 1);
    }
    @Test public void testaddMemberAndReferenceCar (){
        garage.addMemberCar(2, "123").addReferenceCar(3, "1234");
        asssertGarageStatus(2, 330, 5, 1, 1, 0);
    }   
    @Test public void  TestcarLeavesAffectingTheFee (){
        garage.addInivitedCar(2, "123").addMemberCar(2, "1234");	
        asssertGarageStatus(2, 250, 4, 1, 0, 1);
        garage.carLeaves("123");
        asssertGarageStatus(1, 100, 2, 1, 0, 0);
    }


    private void assertThrowsLike (Executable executable , String message) {
		assertEquals(message, assertThrows( RuntimeException.class, executable ).getMessage());
	}
    private void asssertGarageStatus (int numberOfCars , int Fee , int numberOfPeople , 
    int numberOfCarMembers , int numberOfReferenceCars , int numberOfCarInvited){
         assertEquals(numberOfCars, garage.getNumberOfCars());
        assertEquals(Fee, garage.getFee());
        assertEquals(numberOfPeople, garage.getNumberOfPeople());
        assertEquals(numberOfCarMembers, garage.getCarMemebers());
        assertEquals(numberOfReferenceCars, garage.getReferenceCars());
        assertEquals(numberOfCarInvited, garage.getInvitedCar());
    }
}
