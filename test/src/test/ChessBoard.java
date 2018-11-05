package test;

public class ChessBoard {
	boolean[][] boardStatus;
	int ROWS = 8;
	int COLS = 8;


	ChessBoard(Player p1, Player p2) {
		boardStatus = new boolean[ROWS][COLS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (i > 1 && i < 6) {
					continue;
				}
				boardStatus[i][j] = true; // initial setup, occupied positions
			}
		}

	}
	
	class Player{
		String name;
		String email;
		long phone;
		int age;
		PlayerType type;

	}

	class Position {
		int x;
		int y;

		Position(int i, int j) {
			x = i;
			y = j;
		}
	}

	enum Direction {
		Left, Right, Up, Down, LeftUp, RightUp, LeftDown, RightDown;
	}

	enum PlayerType {
		White, Black;
	}

	abstract class Piece {
		Position position;

		Piece(Position pos) {
			position = pos;
		}

		void move(Direction dx, int stepx, Direction dy, int stepy) {
			if (dx == Direction.Left) {
				position.x -= stepx;
			} else if (dx == Direction.Right) {
				position.x += stepx;
			} 
			
			if (dy == Direction.Up) {
				position.y += stepy;
			} else if (dy == Direction.Down) {
				position.y -= stepy;
			} 
		}
	}

	class King extends Piece {
		Position position;
		int MAX_STEPX = 1;
		int MAX_STEPY = 1;

		King(Position pos) {
			super(pos);
		}

	}
	class Queen extends Piece {
		Position position;
		int MAX_STEPX = 7;
		int MAX_STEPY = 7;

		Queen(Position pos) {
			super(pos);
		}

	}
	class Horse extends Piece {
		Position position1;
		Position position2;
		int MAX_STEPX = 7;
		int MAX_STEPY = 7;

		Horse(Position pos) {
			super(pos);
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
