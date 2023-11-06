package Ternilapilli;

public class Over extends State {

	public Over( TerniLapilli game ) {
		super( game );
	}
	

	public void putXAt( Position position ) {
		throw new RuntimeException ( TerniLapilli.canNotPlayWhenGameIsOverErrorMessage );
	}

	public void putOAt( Position position ) {
		throw new RuntimeException ( TerniLapilli.canNotPlayWhenGameIsOverErrorMessage );
	}
	

	public void slideXFrom( Position aPosition, Position anotherPosition ) {
		throw new RuntimeException ( TerniLapilli.canNotPlayWhenGameIsOverErrorMessage );
	}

	public void slideOFrom( Position aPosition, Position anotherPosition ) {
		throw new RuntimeException ( TerniLapilli.canNotPlayWhenGameIsOverErrorMessage );
	}
	
	
	public boolean isValid() {
		return currentGame.isGameOver();
	}

	public boolean isPlayingX() {
		return false;
	}

	public boolean isPlayingO() {
		return false;
	}

}