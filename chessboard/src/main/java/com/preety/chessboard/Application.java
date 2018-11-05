package com.preety.chessboard;


import com.preety.chessboard.domain.Piece;
import com.preety.chessboard.domain.Position;
import com.preety.chessboard.domain.Board;
import com.preety.chessboard.domain.Color;
import com.preety.chessboard.domain.Horse;
import com.preety.chessboard.domain.Pawn;
import com.preety.chessboard.domain.Queen;

public class Application {

	public static void main(String[] args) {
		
		
		Board board= new Board();
		
		String [][] boardMat= {
				{"WR", "WH", "WB", "WQ", "WK", "WB", "WH", "WR"},
				{"WP","WP","WP","WP","WP","WP","WP","WP"},
				{"--","--","--","--","--","--","--","--"},
				{"--","--","--","--","--","--","--","--"},
				{"--","--","--","--","--","--","--","--"},
				{"--","--","--","--","--","--","--","--"},
				{"BP","BP","BP","BP","BP","BP","BP","BP"},
				{"BR", "BH", "BB", "BQ", "BK", "BB", "BH", "BR"}
		};
		
		board.initialSetUp(boardMat);
		Piece pc= new Horse(Color.B);
		Position start= new Position(3,3);
		Position end= new Position(5,5);
		String validStr= pc.move(start, end);
		System.out.println("WP 12 22 ["+ validStr + "]");
		
		 
	}

}
