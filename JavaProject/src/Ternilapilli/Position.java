package Ternilapilli;

import java.util.Objects;

public class Position {
	
	private int row;
	private int column;

	
	public Position(int i, int j) {
		row = i;
		column = j;
	}
	
	
	public boolean equals (Object obj) {
		Position position = (Position) obj;
		return row == position.row && column == position.column;
	}
	
	
	public int hashCode() {
		return Objects.hash( row, column); 
	}

	
	public boolean inBetween(Position aPosition, Position anotherPosition) {
		return getRow() >= aPosition.getRow() && getRow() <= anotherPosition.getRow()
				&& getColumn() >= aPosition.getColumn() && getColumn() <= anotherPosition.getColumn();
	}
	
	
// Accesors
	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

}
