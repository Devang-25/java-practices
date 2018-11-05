package com.preety.chessboard.domain;

public abstract class Piece {
	private Color color; 
	private PieceType piece;

	public Piece(Color color, PieceType piece) {
		super();
		this.color = color;
		this.piece = piece;
	}
	

	public Color getColor() {
		return color;
	}


	public void setColor(Color color) {
		this.color = color;
	}


	public PieceType getPiece() {
		return piece;
	}


	public void setPiece(PieceType piece) {
		this.piece = piece;
	}


	public abstract String move(Position start, Position end); 
//	{
//		System.out.println("move from " + start + " to end " + end);
//	}

}


