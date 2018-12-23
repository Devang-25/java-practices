
import java.util.Random;

public abstract class LudoGames implements Ludo {
	private Random random = new Random();
	public int rollDice() {
		return random.nextInt(6) + 1;
	}

}
