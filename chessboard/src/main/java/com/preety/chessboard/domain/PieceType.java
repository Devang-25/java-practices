package com.preety.chessboard.domain;

public enum PieceType{
	R("R"), //Rook
	B("B"), //Bishop
	H("H"), //Horse
	K("K"), //King
	Q("Q"), // Queen
	P("P"); //Pawn
	
	private String text;

	PieceType(final String text) {
		this.text = text;
	}
	


	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	@Override
	public String toString() {
		return text;
	}
}