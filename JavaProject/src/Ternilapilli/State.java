package Ternilapilli;

import java.util.ArrayList;
import java.util.List;

public abstract class State {
	
	protected TerniLapilli currentGame;
	protected List<State> states;
	
	public State( TerniLapilli game ) {
		currentGame = game;
	}

	public abstract void putXAt( Position position );
	public abstract void putOAt( Position position );
	public abstract void slideXFrom( Position aPosition, Position anotherPosition );
	public abstract void slideOFrom( Position aPosition, Position anotherPosition );
	public abstract boolean isValid();
	public abstract boolean isPlayingX();
	public abstract boolean isPlayingO();
	
	public State selectState() {
		states = new ArrayList<State>( List.of( new XPut( currentGame ), new OPut( currentGame ), new XSlide( currentGame ), new OSlide( currentGame ), new Over( currentGame ) ) );
		return states.stream().filter( ( each ) -> each.isValid() ).findFirst().get();
	}
	
}