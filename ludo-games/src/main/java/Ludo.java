import java.util.List;

public interface Ludo {
	
	public int rollDice();// returns value of dice
	public void initialize(List<String> playerNames);
	public Player play(); // plays till have a winner, returns winner player

}
