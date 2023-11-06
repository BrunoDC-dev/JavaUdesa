package Ternilapilli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class TerniLapilliTest {
	@Test public void test01NewEmptyGame() {
		TerniLapilli game = new TerniLapilli ();

		assertTrue( game.getXs().isEmpty() );
		assertTrue( game.getOs().isEmpty() );
	}
	
	
	@Test public void test02NewGameWithXPosition() {
		TerniLapilli game = new TerniLapilli ();
		game.putXAt( new Position( 1, 1 ) );
		
		assertEquals( 1, game.getXs().size() );
		assertTrue( game.getXs().contains( new Position( 1, 1 ) ) );
		assertTrue( game.getOs().isEmpty() );
	}
	
	
	@Test public void test03NewGameWithXOPosition() {
		TerniLapilli game = gameWith1XAnd1O( 1,1, 2,2 );
		
		assertEquals( 1, game.getXs().size() );
		assertTrue( game.getXs().contains( new Position( 1, 1 ) ) );
		assertEquals( 1, game.getOs().size() );
		assertTrue( game.getOs().contains( new Position( 2, 2 ) ) );
	}
	
	
	@Test public void test04XTryToPutAnotherPositionBeforeO() {
		TerniLapilli game = new TerniLapilli ();
		game.putXAt( new Position( 1, 1 ) );
		
		assertThrowsLike(()->game.putXAt( new Position( 2, 2 ) ), 
				TerniLapilli.notXTurnErrorMessage);
		
		assertEquals( 1, game.getXs().size() );
		assertTrue( game.getXs().contains( new Position( 1, 1 ) ) );
		assertTrue( game.getOs().isEmpty() );
	}
	
	
	@Test public void test05OTryToPutAnotherPositionBeforeX() {
		TerniLapilli game = gameWith1XAnd1O( 1,1, 2,2 );
		
		assertThrowsLike(()->game.putOAt( new Position( 3, 3 ) ), 
				TerniLapilli.notOTurnErrorMessage);
		
		assertEquals( 1, game.getXs().size() );
		assertTrue( game.getXs().contains( new Position( 1, 1 ) ) );
		assertEquals( 1, game.getOs().size() );
		assertTrue( game.getOs().contains( new Position( 2, 2 ) ) );
	}
	
	
	@Test public void test06XTryToPutAnotherPositionAtAPositionTakenByX() {
		TerniLapilli game = gameWith1XAnd1O( 1,1, 2,2 );
		
		assertThrowsLike(()->game.putXAt( new Position( 1, 1 ) ), 
				TerniLapilli.positionTakenErrorMessage);
		
		assertEquals( 1, game.getXs().size() );
		assertTrue( game.getXs().contains( new Position( 1, 1 ) ) );
		assertEquals( 1, game.getOs().size() );
		assertTrue( game.getOs().contains( new Position( 2, 2 ) ) );
	}

	
	@Test public void test07XTryToPutAnotherPositionAtAPositionTakenByO() {
		TerniLapilli game = gameWith1XAnd1O( 1,1, 2,2 );
		
		assertThrowsLike(()->game.putXAt( new Position( 2, 2 ) ), 
				TerniLapilli.positionTakenErrorMessage);
		
		assertEquals( 1, game.getXs().size() );
		assertTrue( game.getXs().contains( new Position( 1, 1 ) ) );
		assertEquals( 1, game.getOs().size() );
		assertTrue( game.getOs().contains( new Position( 2, 2 ) ) );
	}
	
	
	@Test public void test08OTryToPutAnotherPositionAtAPositionTakenByX() {
		TerniLapilli game = new TerniLapilli ();
		game.putXAt( new Position( 1, 1 ) );

		assertThrowsLike(()->game.putOAt( new Position( 1, 1 ) ), 
				TerniLapilli.positionTakenErrorMessage);
		
		assertEquals( 1, game.getXs().size() );
		assertTrue( game.getXs().contains( new Position( 1, 1 ) ) );
		assertTrue( game.getOs().isEmpty() );
	}
	
	
	@Test public void test09OTryToPutAnotherPositionAtAPositionTakenByO() {
		TerniLapilli game = gameWith2XAnd1O( 1,1, 2,2, 3,3 );
		
		assertThrowsLike(()->game.putOAt( new Position( 2, 2 ) ), 
				TerniLapilli.positionTakenErrorMessage);

		assertEquals( 2, game.getXs().size() );
		assertTrue( game.getXs().contains( new Position( 1, 1 ) ) );
		assertTrue( game.getXs().contains( new Position( 3, 3 ) ) );
		assertEquals( 1, game.getOs().size() );
		assertTrue( game.getOs().contains( new Position( 2, 2 ) ) );
	}
	
	
	@Test public void test10NoneWins() {
		TerniLapilli game = gameWith2XAnd2O( 1,1, 2,2, 3,3, 1,2 );
		
		assertFalse( game.XHasWon() );
		assertFalse( game.OHasWon() );
	}
	
	
	@Test public void test11XWinsWithRow() {
		TerniLapilli game = gameWith3XAnd2O( 1,1, 2,2, 1,2, 2,3, 1,3 );
		
		assertTrue( game.XHasWon() );
		assertFalse( game.OHasWon() );
	}
	
	
	@Test public void test12OWinsWithRow() {
		TerniLapilli game = gameWith3XAnd3O( 2,2, 1,1, 2,3, 1,2, 3,3, 1,3 );
		
		assertFalse( game.XHasWon() );
		assertTrue( game.OHasWon() );
	}
	
	
	@Test public void test13XWinsWithColumn() {
		TerniLapilli game = gameWith3XAnd2O( 1,1, 2,2, 2,1, 2,3, 3,1 );

		assertTrue( game.XHasWon() );
		assertFalse( game.OHasWon() );
	}
	
	
	@Test public void test14OWinsWithColumn() {
		TerniLapilli game = gameWith3XAnd3O( 2,2, 1,1, 2,3, 2,1, 3,3, 3,1 );
		
		assertFalse( game.XHasWon() );
		assertTrue( game.OHasWon() );
	}
	
	
	@Test public void test15XWinsWithDownDiagonal() {
		TerniLapilli game = gameWith3XAnd2O( 1,1, 3,1, 2,2, 2,3, 3,3 );
		
		assertTrue( game.XHasWon() );
		assertFalse( game.OHasWon() );
	}
	
	
	@Test public void test16XWinsWithUpDiagonal() {
		TerniLapilli game = gameWith3XAnd2O( 1,3, 1,1, 2,2, 1,2, 3,1 );
		
		assertTrue( game.XHasWon() );
		assertFalse( game.OHasWon() );
	}
	
	
	@Test public void test17OWinsWithDownDiagonal() {
		TerniLapilli game = gameWith3XAnd3O( 3,1, 1,1, 2,3, 2,2, 1,3, 3,3 );
		
		assertFalse( game.XHasWon() );
		assertTrue( game.OHasWon() );
	}
	
	
	@Test public void test18OWinsWithUpDiagonal() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 1,3, 1,2, 2,2, 3,3, 3,1 );
		
		assertFalse( game.XHasWon() );
		assertTrue( game.OHasWon() );
	}
	
	
	@Test public void test19OTriesToPlayAfterXWon() {
		TerniLapilli game = gameWith3XAnd2O( 2,1, 1,1, 2,2, 3,1, 2,3 );
		
		assertThrowsLike(()->game.putOAt(new Position(3,3)), 
						TerniLapilli.canNotPlayWhenGameIsOverErrorMessage);
		
		assertEquals (3,game.getXs().size());
		assertTrue(game.getOs().contains(new Position(1,1)) );
		assertTrue(game.getOs().contains(new Position(3,1)) );
		assertEquals(2, game.getOs().size());
	}
	
	
	@Test public void test20XTriesToPutMorePiecesThanThree() {
		TerniLapilli game = gameWith3XAnd3O( 2,1, 1,1, 1,2, 3,1, 2,3, 3,2 );

		assertThrowsLike(()->game.putXAt(new Position(3,3)), 
						TerniLapilli.eachPlayerOnlyHasThreePiecesErrorMessage);
		
		assertEquals (3,game.getXs().size());
		assertTrue(game.getXs().contains(new Position(2,1)) );
		assertTrue(game.getXs().contains(new Position(1,2)) );
		assertTrue(game.getXs().contains(new Position(2,3)) );
		assertEquals(3, game.getOs().size());
	}
	
	
	@Test public void test21XSlidesFromOnePositionToAnother() {
		TerniLapilli game = gameWith3XAnd3O( 2,1, 1,1, 1,2, 3,1, 2,3, 3,2 );

		game.slideXfrom(2, 1, 2, 2 );
		
		assertEquals (3,game.getXs().size());
		assertFalse(game.getXs().contains(new Position(2,1)) );
		assertTrue(game.getXs().contains(new Position(2,2)) );
		assertTrue(game.getXs().contains(new Position(1,2)) );
		assertTrue(game.getXs().contains(new Position(2,3)) );
		assertEquals(3, game.getOs().size());
	}
	
	
	@Test public void test22XAndOSlidesFromOnePositionToAnother() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 2,1, 2,2, 3,1, 2,3, 1,2 );
		
		game.slideXfrom( 2, 2, 1, 3 );
		game.slideOfrom( 3, 1, 3, 2 );
		
		assertEquals (3,game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1,3)) );
		assertFalse(game.getXs().contains(new Position(2,2)) );
		assertTrue(game.getOs().contains(new Position(3,2)) );
		assertFalse(game.getOs().contains(new Position(3,1)) );
		
		assertEquals(3, game.getOs().size());
	}
	
	
	@Test public void test23XTriesToMoveToAnTakenPositionByX() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 2,1, 2,2, 3,1, 2,3, 1,2 );
		
		game.slideXfrom( 2, 2, 1, 3 );
		game.slideOfrom( 3, 1, 3, 2 );
		
		assertThrowsLike(()->game.slideXfrom( 2, 3, 1, 3 ), 
				TerniLapilli.positionTakenErrorMessage);
		
		assertEquals (3,game.getXs().size());
		assertTrue(game.getXs().contains(new Position(2,3)) );
		assertTrue(game.getXs().contains(new Position(1,3)) );
	}
	
	
	@Test public void test24OTakesAValueThatXJustLeft() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 2,1, 2,2, 3,1, 2,3, 1,2 );
		
		game.slideXfrom( 2, 2, 1, 3 );
		game.slideOfrom( 3, 1, 2, 2 );

		assertEquals (3,game.getXs().size());
		assertFalse(game.getXs().contains(new Position(2,2)) );
		assertTrue(game.getOs().contains(new Position(2,2)) );
		assertEquals (3,game.getOs().size());
	}
	
	
	@Test public void test25OCanNotTakeAnXPosition() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 2,1, 2,2, 3,1, 2,3, 1,2 );

		game.slideXfrom(2, 2, 1, 3 );
		game.slideOfrom(3, 1, 3, 2 );
		game.slideXfrom(1, 3, 2, 2 );

		assertThrowsLike(()->game.slideOfrom(3, 2, 2, 2), 
				TerniLapilli.positionTakenErrorMessage);

		assertEquals (3,game.getOs().size());
		assertTrue(game.getOs().contains(new Position(3,2)) );
		assertFalse(game.getOs().contains(new Position(2,2)) );
		assertTrue(game.getXs().contains(new Position(2,2)) );
	}
	
	
	@Test public void test26OSlideAnOPositionToAPositionTakenByO() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 2,1, 2,2, 3,1, 2,3, 1,2 );

		game.slideXfrom(2, 2, 1, 3 );
		game.slideOfrom(3, 1, 3, 2 );
		game.slideXfrom(1, 3, 2, 2 );

		assertThrowsLike(()->game.slideOfrom(3, 2, 2, 1), 
				TerniLapilli.positionTakenErrorMessage);

		assertEquals (3,game.getOs().size());
		assertTrue(game.getOs().contains(new Position(3,2)) );
		assertTrue(game.getOs().contains(new Position(2,1)) );
		assertTrue(game.getOs().contains(new Position(1,2)) );
	}
	
	
	@Test public void test27XWinsSliding() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 2,1, 2,2, 3,1, 2,3, 1,2 );

		game.slideXfrom(2, 3, 3, 3 );

		assertTrue( game.XHasWon() );
		assertFalse( game.OHasWon() );	
	}
	
	
	@Test public void test28OWinsSliding() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 1,2, 2,1, 2,2, 3,2, 3,1 );

		game.slideXfrom(3, 2, 3, 3 );
		game.slideOfrom(3, 1, 3, 2 );

		assertFalse( game.XHasWon() );
		assertTrue( game.OHasWon() );
	}
	
	
	@Test public void test29XWinsAndOWantsToPlay() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 2,1, 2,2, 3,1, 2,3, 1,2 );

		game.slideXfrom(2, 3, 3, 3 );

		assertThrowsLike(()->game.slideOfrom(1, 2, 1, 3), 
				TerniLapilli.canNotPlayWhenGameIsOverErrorMessage);
		
		assertTrue( game.XHasWon() );
		assertFalse( game.OHasWon() );
		assertEquals (3,game.getOs().size());
		assertTrue(game.getOs().contains(new Position(1,2)) );
		assertFalse(game.getOs().contains(new Position(1,3)) );
	}
	
	
	@Test public void test30OTriesToSlideAfterWon() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 1,2, 2,1, 2,2, 3,2, 3,1 );
		
		game.slideXfrom(3, 2, 3, 3 );
		game.slideOfrom(3, 1, 3, 2 );

		assertThrowsLike(()->game.slideOfrom(3, 3, 2, 3), 
				TerniLapilli.canNotPlayWhenGameIsOverErrorMessage);
		
		assertFalse( game.XHasWon() );
		assertTrue( game.OHasWon() );
		assertTrue(game.getXs().contains(new Position(3,3)) );
		assertFalse(game.getXs().contains(new Position(2,3)) );
	}
	
	
	@Test public void test31XTriesToSlideADistanceGreaterThanOne() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 1,2, 3,2, 2,1, 3,3, 3,1 );

		assertThrowsLike(()->game.slideXfrom(3, 3, 1, 3), 
				TerniLapilli.positionIlegalErrorMessage);
		
		assertEquals (3,game.getXs().size());
		assertTrue(game.getXs().contains(new Position(1,1)) );
		assertTrue(game.getXs().contains(new Position(3,2)) );
		assertTrue(game.getXs().contains(new Position(3,3)) );	
	}
	
	
	@Test public void test32OTriesToSlideADistanceGreaterThanOne() {
		TerniLapilli game = gameWith3XAnd3O( 1,1, 1,2, 3,2, 2,1, 3,3, 3,1 );

		game.slideXfrom(3, 3, 2, 3);
		
		assertThrowsLike(()->game.slideOfrom (1, 2, 3, 3), 
				TerniLapilli.positionIlegalErrorMessage);
	}
	
	
	@Test public void test33OTriesToPutMorePiecesThanThree() {
		TerniLapilli game = gameWith3XAnd3O( 2,1, 1,1, 1,2, 3,1, 2,3, 3,2 );
		
		game.slideXfrom(1, 2, 1, 3);
		
		assertThrowsLike(()->game.putOAt(new Position(3,3)), 
						TerniLapilli.eachPlayerOnlyHasThreePiecesErrorMessage);
		
		assertEquals (3,game.getXs().size());
		assertTrue(game.getOs().contains(new Position(1,1)) );
		assertTrue(game.getOs().contains(new Position(3,1)) );
		assertTrue(game.getOs().contains(new Position(3,2)) );
		assertEquals(3, game.getOs().size());
	}
	
	
	@Test public void test34XTriesToSlideBeforePutThreePieces() {
		TerniLapilli game = gameWith2XAnd2O( 2,1, 1,1, 1,2, 3,1 );		
				
		assertThrowsLike(()->game.slideXfrom(1, 2, 1, 3), 
						TerniLapilli.canNotSlideBeforePutThreePiecesErrorMessage);
		
		assertEquals (2,game.getXs().size());
		assertTrue(game.getXs().contains(new Position(2,1)) );
		assertTrue(game.getXs().contains(new Position(1,2)) );
		assertFalse(game.getXs().contains(new Position(1,3)) );
		assertEquals(2, game.getOs().size());
	}
	
	
	@Test public void test35OTriesToSlideBeforePutThreePieces() {
		TerniLapilli game = gameWith3XAnd2O( 1,1, 2,1, 3,1, 1,2, 3,3 );
				
		assertThrowsLike(()->game.slideOfrom(1, 2, 1, 3), 
						TerniLapilli.canNotSlideBeforePutThreePiecesErrorMessage);
		
		assertEquals (2,game.getOs().size());
		assertTrue(game.getOs().contains(new Position(2,1)) );
		assertTrue(game.getOs().contains(new Position(1,2)) );
		assertFalse(game.getOs().contains(new Position(1,3)) );
		assertEquals(3, game.getXs().size());
	}
	
	
	
	
	private void assertThrowsLike (Executable executable, String message) {
		assertEquals (message, assertThrows(RuntimeException.class, executable).getMessage() );
	}

	private TerniLapilli gameWith1XAnd1O( int first1X, int first2X, int first1O, int first2O ) {
		TerniLapilli game = new TerniLapilli ();
		game.putXAt( new Position( first1X, first2X ) );
		game.putOAt( new Position( first1O, first2O ) );
		return game;
	}
	
	private TerniLapilli gameWith2XAnd1O( int first1X, int first2X, int first1O, int first2O, int second1X, int second2X ) {
		TerniLapilli game = new TerniLapilli ();
		game.putXAt( new Position( first1X, first2X ) );
		game.putOAt( new Position( first1O, first2O ) );
		game.putXAt( new Position( second1X, second2X ) );
		return game;
	}
	
	private TerniLapilli gameWith2XAnd2O( int first1X, int first2X, int first1O, int first2O, int second1X, int second2X, int second1O, int second2O ) {
		TerniLapilli game = new TerniLapilli ();
		game.putXAt( new Position( first1X, first2X ) );
		game.putOAt( new Position( first1O, first2O ) );
		game.putXAt( new Position( second1X, second2X ) );
		game.putOAt( new Position( second1O, second2O ) );
		return game;
	}
	
	private TerniLapilli gameWith3XAnd2O( int first1X, int first2X, int first1O, int first2O, int second1X, int second2X, int second1O, int second2O, int third1X, int third2X ) {
		TerniLapilli game = new TerniLapilli ();
		game.putXAt( new Position( first1X, first2X ) );
		game.putOAt( new Position( first1O, first2O ) );
		game.putXAt( new Position( second1X, second2X ) );
		game.putOAt( new Position( second1O, second2O ) );
		game.putXAt( new Position( third1X, third2X ) );
		return game;
	}
	
	private TerniLapilli gameWith3XAnd3O( int first1X, int first2X, int first1O, int first2O, int second1X, int second2X, int second1O, int second2O, int third1X, int third2X, int third1O, int third2O ) {
		TerniLapilli game = new TerniLapilli ();
		game.putXAt( new Position( first1X, first2X ) );
		game.putOAt( new Position( first1O, first2O ) );
		game.putXAt( new Position( second1X, second2X ) );
		game.putOAt( new Position( second1O, second2O ) );
		game.putXAt( new Position( third1X, third2X ) );
		game.putOAt( new Position( third1O, third2O ) );
		return game;
	}
	
}