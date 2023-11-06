package Ternilapilli;

public class OSlide  extends State{

	public OSlide( TerniLapilli game ) {
		super( game );
	}

	
	public void putXAt( Position position ) {	
		throw new RuntimeException( TerniLapilli.eachPlayerOnlyHasThreePiecesErrorMessage );
	}

	public void putOAt( Position position ) {
		throw new RuntimeException( TerniLapilli.eachPlayerOnlyHasThreePiecesErrorMessage );
	}
	

	public void slideXFrom( Position aPosition, Position anotherPosition ) {
		throw new RuntimeException( TerniLapilli.notXTurnErrorMessage );
	}

	public void slideOFrom( Position aPosition, Position anotherPosition ) {
		currentGame.removeOPosition( aPosition );
		currentGame.modifyOPosition( anotherPosition );
		currentGame.modifyTurn( selectState() );
	}
	
	
	public boolean isValid() {
		return !currentGame.isPlayingO() && currentGame.allPiecesOnBoard() && !currentGame.isGameOver();
	}

	public boolean isPlayingX() {
		return false;
	}

	public boolean isPlayingO() {
		return true;
	}

}