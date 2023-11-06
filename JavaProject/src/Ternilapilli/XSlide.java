package Ternilapilli;

public class XSlide  extends State{

	public XSlide( TerniLapilli game ) {
		super( game );
	}

	
	public void putXAt( Position position ) {	
		throw new RuntimeException( TerniLapilli.eachPlayerOnlyHasThreePiecesErrorMessage );
	}

	public void putOAt( Position position ) {
		throw new RuntimeException( TerniLapilli.eachPlayerOnlyHasThreePiecesErrorMessage );
	}

	
	public void slideXFrom( Position aPosition, Position anotherPosition ) {
		currentGame.removeXPosition( aPosition );
		currentGame.modifyXPosition( anotherPosition );;
		currentGame.modifyTurn( selectState() );
	}

	public void slideOFrom( Position aPosition, Position anotherPosition ) {
		throw new RuntimeException( TerniLapilli.notOTurnErrorMessage );
	}
	
	
	public boolean isValid() {
		return !currentGame.isPlayingX() && currentGame.allPiecesOnBoard() && !currentGame.isGameOver();
	}

	public boolean isPlayingX() {
		return true;
	}

	public boolean isPlayingO() {
		return false;
	}

}