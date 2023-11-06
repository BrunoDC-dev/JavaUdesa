package Ternilapilli;

public class XPut extends State {
	
	public XPut( TerniLapilli game ) {
		super( game );
	}
	
	
	public void putXAt( Position position ) {
		currentGame.modifyXPosition( position );
		currentGame.modifyTurn( selectState() );
	}

	public void putOAt( Position position ) {
		throw new RuntimeException( TerniLapilli.notOTurnErrorMessage );
	}

	
	public void slideXFrom( Position aPosition, Position anotherPosition ) {
		throw new RuntimeException( TerniLapilli.canNotSlideBeforePutThreePiecesErrorMessage );
	}	
	
	public void slideOFrom( Position aPosition, Position anotherPosition ) {
		throw new RuntimeException( TerniLapilli.notOTurnErrorMessage );
	}
	
	
	public boolean isValid() {
		return !currentGame.isPlayingX() && currentGame.isPlayingO() && !currentGame.allPiecesOnBoard() && !currentGame.isGameOver();
	}
	
	public boolean isPlayingX() {
		return true;
	}

	public boolean isPlayingO() {
		return false;
	}

}