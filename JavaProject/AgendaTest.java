package agenda;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.DayOfWeek;

class AgendaTest {

	private static LocalDate domingo25deJunio = LocalDate.of(2023, 6, 25);
	private static LocalDate jueves29deJunio = LocalDate.of(2023, 6, 29);
	private static LocalDate viernes30deJunio = LocalDate.of(2023, 6, 30);
	private static LocalDate sabado10deJunio = LocalDate.of(2023, 6, 10);

	@Test void test01RegisterSpecificHoliday() {
		assertTrue( agendaWithRegisteredTodayHoliday().isHoliday( LocalDate.now() ) );
	}
	
	@Test void test02RegisterWeeklyHoliday() {
		assertTrue( agendaWithSundayHoliday().isHoliday( domingo25deJunio ));
	}
	
	@Test void test03RegisterPeriodHolidayRegisterStartDate() {
		assertTrue( agendaWithRegisteredPeriodHoliday().isHoliday( domingo25deJunio ));
	}
	
	@Test void test04RegisterPeriodHolidayRegistersEndDate() {
		assertTrue( agendaWithRegisteredPeriodHoliday().isHoliday( viernes30deJunio ));
	}
	
	@Test void test05RegisterPeriodHolidayRegisterDateInBetween() {
		assertTrue( agendaWithRegisteredPeriodHoliday().isHoliday( jueves29deJunio ));
	}
	
	@Test void test06IsHolidayReturnsFalseIfHolidayIsNotRegistered() {
		assertFalse( new Agenda().isHoliday( LocalDate.now() ));
	}
	
	@Test void test07IsHolidayReturnsFalseIfWrongDateIsAskedForSpecificHoliday() {
		assertFalse( new Agenda().registerSpecificHoliday( domingo25deJunio ).isHoliday( jueves29deJunio ));
	}
	
	@Test void test08IsHolidayReturnsFalseIfWrongDateIsAskedForWeeklyHoliday() {
		assertFalse( agendaWithSundayHoliday().isHoliday( jueves29deJunio ) );
	}
	
	@Test void test09IsHolidayReturnsFalseIfWrongDateIsAskedForPeriodHoliday() {
		assertFalse( agendaWithRegisteredPeriodHoliday().isHoliday( sabado10deJunio ));
	}
	
	@Test void test10RegisteringAnExistentSpecificHolidayDoesNotThrowError() {
		assertTrue( agendaWithRegisteredTodayHoliday().registerSpecificHoliday( LocalDate.now() ).isHoliday( LocalDate.now() ));
	}
	
	@Test void test11RegisteringAnExistentASpecificHolidayWeeklyHolidayDoesNotThrowError() {
		assertTrue( agendaWithSundayHoliday().registerSpecificHoliday( domingo25deJunio ).isHoliday( domingo25deJunio ) );
	}
	
	@Test void test12RegisteringAnExistentWeeklyHolidayDoesNotThrowError() {
		assertTrue( agendaWithSundayHoliday().registerWeeklyHoliday( DayOfWeek.SUNDAY ).isHoliday( domingo25deJunio ) );
	}

	@Test void test13RegisteringAnExistentPeriodHolidayOnAWeeklyHolidayDoesNotThrowError() {
		assertTrue( agendaWithSundayHoliday().registerPeriodHoliday( domingo25deJunio, viernes30deJunio).isHoliday( domingo25deJunio ) );
	}
	
	@Test void test14RegisteringAnExistentPeriodHolidayTwiceDoesNotThrowError() {
		assertTrue( agendaWithRegisteredPeriodHoliday().registerPeriodHoliday( domingo25deJunio, viernes30deJunio).isHoliday( domingo25deJunio ) );
	}
	
	@Test void test15RegisteringASpecificHolidayOnAnExistentPeriodHolidayDoesNotThrowError() {
		assertTrue( agendaWithRegisteredPeriodHoliday().registerSpecificHoliday( domingo25deJunio ).isHoliday( domingo25deJunio ) );
	}
	
	private Agenda agendaWithRegisteredTodayHoliday() {
		return new Agenda().registerSpecificHoliday( LocalDate.now());
	}
	
	private Agenda agendaWithSundayHoliday() {
		return new Agenda().registerWeeklyHoliday( DayOfWeek.SUNDAY );
	}
	
	private Agenda agendaWithRegisteredPeriodHoliday() {
		return new Agenda().registerPeriodHoliday( domingo25deJunio, viernes30deJunio);
	}		
}
