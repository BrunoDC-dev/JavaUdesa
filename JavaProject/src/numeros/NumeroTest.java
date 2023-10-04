package numeros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NumeroTest {
	private Numero zero;
	private Numero one;
	private Numero two;
	private Numero three;
	private Numero four;
	private Numero five;
	private Numero eight;
	private Numero negativeOne;
	private Numero negativeTwo;
	private Numero oneHalf;
	private Numero oneFifth;
	private Numero twoFifth;
	private Numero twoTwentyfifth;
	private Numero fiveHalfs;
	private Numero sixFifths;
	private Numero negativeOneHalf;

	@BeforeEach public void setUp() {
		zero = Numero.with( 0 );
		one = Numero.with( 1 );
		two = Numero.with( 2 );
		three = Numero.with( 3 );
		four = Numero.with( 4 );
		five = Numero.with( 5 );
		eight = Numero.with( 8 );
		oneFifth = Numero.with( 1, 5 );
		oneHalf = Numero.with( 1, 2 );
		twoFifth = Numero.with( 2, 5 );
		twoTwentyfifth = Numero.with( 2, 25 );
		fiveHalfs = Numero.with( 5, 2 );
		sixFifths = Numero.with( 6, 5 );
		negativeOne = Numero.with( -1 );
		negativeTwo = Numero.with( -2 );
		negativeOneHalf = Numero.with( -1, 2 );
	}

	@Test public void test01isCeroReturnsTrueWhenAskToZero() {
		assertTrue( zero.isZero() );
	}

	@Test public void test02isCeroReturnsFalseWhenAskToOthersButZero() {
		assertFalse( one.isZero() );
	}

	@Test public void test03isOneReturnsTrueWhenAskToOne() {
		assertTrue( one.isOne() );
	}

	@Test public void test04isOneReturnsFalseWhenAskToOtherThanOne() {
		assertFalse( zero.isOne() );
	}

	@Test public void test05EnteroAddsWithEnteroCorrectly() {
		assertEquals( two, one.addedTo( one ) );
	}

	@Test public void test06EnteroMultipliesWithEnteroCorrectly() {
		assertEquals( four, two.multipliedBy( two ) );
	}

	@Test public void test07EnteroDividesEnteroCorrectly() {
		assertEquals( one, two.dividedBy( two ) );
	}

	@Test public void test08FraccionAddsWithFraccionCorrectly() {
		// La suma de fracciones es:
		// a/b + c/d = (a.d + c.b) / (b.d)
		// TODAVIA NO SE ESTA TESTEANDO LA REDUCCION DE FRACCIONES NO SE PREOCUPEN!!
		assertEquals( Numero.with( 7, 10 ), oneFifth.addedTo( oneHalf ) );
	}

	@Test public void test09FraccionMultipliesWithFraccionCorrectly() {
		// La multiplicacion de fracciones es:
		// (a/b) * (c/d) = (a.c) / (b.d)
		assertEquals( twoTwentyfifth, oneFifth.multipliedBy( twoFifth ) );
	}

	@Test public void test10FraccionDividesFraccionCorrectly() {
		// La division de fracciones es:
		// (a/b) / (c/d) = (a.d) / (b.c)
		assertEquals( fiveHalfs, oneHalf.dividedBy( oneFifth ) );
	}

	@Test public void test11EnteroAddsFraccionCorrectly() {
		assertEquals( sixFifths, one.addedTo( oneFifth ) );
	}

	@Test public void test12FraccionAddsEnteroCorrectly() {
		assertEquals( sixFifths, oneFifth.addedTo( one ) );
	}

	@Test public void test13EnteroMultipliesFraccionCorrectly() {
		assertEquals( twoFifth, two.multipliedBy( oneFifth ) );
	}

	@Test public void test14FraccionMultipliesEnteroCorrectly() {
		assertEquals( twoFifth, oneFifth.multipliedBy( two ) );
	}

	@Test public void test15EnteroDividesFraccionCorrectly() {
		assertEquals( fiveHalfs, one.dividedBy( twoFifth ) );
	}

	@Test public void test16FraccionDividesEnteroCorrectly() {
		assertEquals( twoTwentyfifth, twoFifth.dividedBy( five ) );
	}

	@Test public void test17AFraccionCanBeEqualToAnEntero() {
		assertEquals( two, four.dividedBy( two ) );
	}

	@Test public void test18AparentFraccionesAreEqual() {
		assertEquals( oneHalf, two.dividedBy( four ) );
	}

	@Test public void test19AddingFraccionesCanReturnAnEntero() {
		assertEquals( one, oneHalf.addedTo( oneHalf ) );
	}

	@Test public void test20MultiplyingFraccionesCanReturnAnEntero() {
		assertEquals( one, twoFifth.multipliedBy( fiveHalfs ) );
	}

	@Test public void test21DividingFraccionesCanReturnAnEntero() {
		assertEquals( one, oneHalf.dividedBy( oneHalf ) );
	}

	@Test public void test22DividingEnterosCanReturnAFraccion() {
		assertEquals( oneHalf, two.dividedBy( four ) );
	}

	@Test public void test23CanNotDivideEnteroByZero() {
		assertEquals( Numero.CanNotDivideByZero,
					  assertThrows( RuntimeException.class, () -> one.dividedBy( zero ) ).getMessage() );
	}

	@Test public void test24CanNotDivideFraccionByZero() {
		assertEquals( Numero.CanNotDivideByZero,
					  assertThrows( RuntimeException.class, () -> oneHalf.dividedBy( zero ) ).getMessage() );
	}

	@Test public void test25AFraccionCanNotBeZero() {
		assertFalse( oneHalf.isZero() );
	}

	@Test public void test26AFraccionCanNotBeOne() {
		assertFalse( oneHalf.isOne() );
	}

	@Test public void test27EnteroSubstractsEnteroCorrectly() {
		assertEquals( one, two.substractedBy( one ) );
	}

	@Test public void test28FraccionSubstractsFraccionCorrectly() {
		assertEquals( oneFifth, twoFifth.substractedBy( oneFifth ) );
	}

	@Test public void test29EnteroSubstractsFraccionCorrectly() {
		assertEquals( oneHalf, one.substractedBy( oneHalf ) );
	}

	@Test public void test30FraccionSubstractsEnteroCorrectly() {

		assertEquals( oneFifth, sixFifths.substractedBy( one ) );
	}

	@Test public void test31SubstractingFraccionesCanReturnAnEntero() {
		assertEquals( one, Numero.with( 3, 2 ).substractedBy( oneHalf ) );
	}

	@Test public void test32SubstractingSameEnterosReturnsZero() {
		assertEquals( zero, one.substractedBy( one ) );
	}

	@Test public void test33SubstractingSameFraccionesReturnsZero() {
		assertEquals( zero, oneHalf.substractedBy( oneHalf ) );
	}

	@Test public void test34SubstractingAHigherValueToANumberReturnsANegativeNumber() {
		assertEquals( Numero.with( -3, 2 ), one.substractedBy( fiveHalfs ) );
	}

	@Test public void test35NegationOfEnteroIsCorrect() {
		assertEquals( two.negated(), negativeTwo );
	}

	@Test public void test36NegationOfFraccionIsCorrect() {
		assertEquals( oneHalf.negated(), negativeOneHalf );
	}

	@Test public void test37SignIsCorrectlyAssignedToFractionWithTwoNegatives() {
		assertEquals( oneHalf, Numero.with( -1, -2 ) );
	}

	@Test public void test38SignIsCorrectlyAssignedToFractionWithNegativeDivisor() {
		assertEquals( oneHalf.negated(), negativeOneHalf );
	}
}