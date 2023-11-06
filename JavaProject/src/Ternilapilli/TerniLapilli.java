package Ternilapilli;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.HashSet;

public class TerniLapilli {
	
	public static final String O = "O";
	public static final String X = "X";
	
	public static  String positionIlegalErrorMessage = "Movement ilegal";
	public static  String eachPlayerOnlyHasThreePiecesErrorMessage = "Each player only has three pieces";
	public static  String canNotSlideBeforePutThreePiecesErrorMessage = "Each player must put three pieces before slide";
	public static String canNotPlayWhenGameIsOverErrorMessage = "Can not play when game is over";
	public static String positionTakenErrorMessage = "Position Taken";
	public static String notOTurnErrorMessage = "Not O´s turn";
	public static String notXTurnErrorMessage = "Not X´s turn";
	
	private Set <Position> xs;
	private Set <Position> os;
	
	private State state;
	
	
	public TerniLapilli() {
		xs = new HashSet <>();
		os = new HashSet <>();
		state = new XPut ( this );
	}
	
	
	
	public void putXAt( Position position ) {
		if ( getXs().contains( position ) || getOs().contains( position ) ){
			throw new RuntimeException( positionTakenErrorMessage );
		}
		state.putXAt( position );
	}
	
	
	public void putOAt( Position position ) {
		if ( getXs().contains( position ) || getOs().contains( position ) ){
			throw new RuntimeException( positionTakenErrorMessage );
		}
		state.putOAt( position );
	}


	
	public void slideXFrom( Position aPosition, Position anotherPosition ) {
		if ( getXs().contains( anotherPosition ) || getOs().contains( anotherPosition ) ){
			throw new RuntimeException(TerniLapilli.positionTakenErrorMessage);
		}
		if( !moveLegalFrom( aPosition, anotherPosition ) ) {
			throw new RuntimeException( TerniLapilli.positionIlegalErrorMessage );
		}
		state.slideXFrom( aPosition, anotherPosition );
	}
	
	
	public void slideXfrom ( int i, int j, int k, int l ) {
		slideXFrom( new Position( i, j ), new Position( k, l ) );
	}

	
	public void slideOFrom( Position aPosition, Position anotherPosition ) {
		if ( getXs().contains( anotherPosition ) || getOs().contains( anotherPosition ) ){
			throw new RuntimeException( TerniLapilli.positionTakenErrorMessage );
		}
		if( !moveLegalFrom( aPosition, anotherPosition ) ) {
			throw new RuntimeException( TerniLapilli.positionIlegalErrorMessage );
		}
		state.slideOFrom( aPosition, anotherPosition );
	}
	
	
	public void slideOfrom ( int i, int j, int k, int l ) {
		slideOFrom( new Position( i, j ), new Position( k, l ) );
	}
	
	
	public boolean moveLegalFrom( Position aPosition, Position anotherPosition ) {
		return Math.abs( aPosition.getRow() - anotherPosition.getRow() ) < 2
				&& Math.abs( aPosition.getColumn() - anotherPosition.getColumn() ) < 2
				&& anotherPosition.inBetween ( new Position( 1,1 ), new Position( 3,3 ) );
	}

	
	
	public boolean allPiecesOnBoard() {
		return xs.size() == 3 && os.size() == 3;
	}

	
	public boolean isGameOver() {
		return XHasWon() || OHasWon();
	}
	
	
	public boolean hasWon( Set<Position> positions ) {
		return hasCompletedRow( positions ) ||
			   hasCompletedColumn( positions ) ||
			   hasDownDiagonal( positions ) ||
			   hasUpDiagonal ( positions );
	}
	
	
	
	private boolean hasCompletedRow( Set<Position> pieces ) {
	    return IntStream.rangeClosed( 1, 3 )
	            		.anyMatch( iteradorRow -> pieces.stream()
	            										.filter( p -> p.getRow() == iteradorRow )
        												.count() == 3 );
	}
	
	
	private boolean hasCompletedColumn( Set<Position> pieces ) {
	    return IntStream.rangeClosed( 1, 3 )
	            		.anyMatch( iteradorColumn -> pieces.stream()
            											  .filter( p -> p.getColumn() == iteradorColumn )
            											  .count() == 3 );
	}

	
	private boolean hasDownDiagonal( Set<Position> positions ) {
	    return IntStream.rangeClosed( 1, 3 )
	            		.allMatch( n -> positions.contains( new Position( n, n ) ) );
	}

	
	private boolean hasUpDiagonal( Set<Position> positions ) {
	    return IntStream.rangeClosed( 1, 3 )
	            		.allMatch( n -> positions.contains( new Position( n, 4 - n ) ) );
	}
	
	

	public Boolean XHasWon() {
		return hasWon( xs );
	}
	
	
	public Boolean OHasWon() {
		return hasWon( os );
	}
	
	
	public Boolean isPlayingX() {
		return state.isPlayingX();
	}
	
	
	public Boolean isPlayingO() {
		return state.isPlayingO();
	}
	
	
	
//	Accesors
	
	public Set <Position> getXs() {
		return xs;
	}

	
	public Set <Position>  getOs() {
		return os;
	}
	

	public void modifyTurn( State newTurn ) {
		state = newTurn;
	}

	
	public void modifyXPosition ( Position position ) {
		xs.add( position );
	}
	
	
	public void modifyOPosition ( Position position ) {
		os.add( position );
	}
	
	
	public void removeXPosition ( Position position ) {
		xs.remove( position );
	}
	
	
	public void removeOPosition ( Position position ) {
		os.remove( position );
	}
	
}