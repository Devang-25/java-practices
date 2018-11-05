package com.preety.chessboard.domain;

public enum Color {
	W("W"), // white
	B("B"); // black

	private String text;

	Color(final String text) {
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