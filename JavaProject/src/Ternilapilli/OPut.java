package Ternilapilli;

public class OPut extends State {
	
	public OPut( TerniLapilli game ) {
		super( game );
	}

	
	public void putXAt( Position position ) {
		throw new RuntimeException( TerniLapilli.notXTurnErrorMessage );
	}
	
	public void putOAt( Position position ) {
		currentGame.modifyOPosition( position );
		currentGame.modifyTurn( selectState() );
	}
	
	
	public void slideXFrom( Position aPosition, Position anotherPosition ) {
		throw new RuntimeException( TerniLapilli.notXTurnErrorMessage );
	}
	
	public void slideOFrom( Position aPosition, Position anotherPosition ) {	
		throw new RuntimeException( TerniLapilli.canNotSlideBeforePutThreePiecesErrorMessage );
	}
	
	
	public boolean isValid() {
		return !currentGame.isPlayingO() && currentGame.isPlayingX() && !currentGame.allPiecesOnBoard() && !currentGame.isGameOver();
	}

	public boolean isPlayingX() {
		return false;
	}

	public boolean isPlayingO() {
		return true;
	}

}