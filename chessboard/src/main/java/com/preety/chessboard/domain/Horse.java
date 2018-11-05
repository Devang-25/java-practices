package com.preety.chessboard.domain;

public class Horse extends Piece {
	private Color color;

	public Horse(Color color) {
		super(color, PieceType.H);
		this.color = color;
	}

	@Override
	public String move( Position start, Position end) {
		int row = end.getRow();
		int col = end.getCol();
		int srow=start.getRow();
		int scol= start.getCol();
		if(Math.abs(row-srow) >2 || Math.abs(col-scol) >2) return "Invalid";
		if(Math.abs(row-srow) <1 || Math.abs(col-scol) <1) return "Invalid";
		if(Math.abs(row-srow) ==2 && Math.abs(col-scol) !=1) return "Invalid";
		if(Math.abs(col-scol) ==2 && Math.abs(row-srow) !=1) return "Invalid";
		
		if (Board.isCellOccupied(row, col)) {
			Color endPiece = Board.getCellPieceColor(row, col);
			if (endPiece != color) {
				Board.setCellPieceColor(row, col, color);
				Board.setCellPiece(row, col, PieceType.Q);	
			} else {
				return "Invalid";
			}
			
		} else {
			Board.setCellPieceColor(row, col, color);
			Board.setCellPiece(row, col, PieceType.Q);
		}
		return "Valid";
	}

}
