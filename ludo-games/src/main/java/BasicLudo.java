import java.util.List;
import java.util.Random;

public class BasicLudo extends LudoGames {
	private Random random = new Random();

	public int rollDice() {
		int total = 0;
		int count = 0;
		int val = random.nextInt(6) + 1;

		while (val == 6) {
			total += val;
			count++;
			if (count % 3 == 0) {
				count = 0;
				total = 0;
			}
			val = random.nextInt(6) + 1;
		}
		total += val;
		return total;
	}

	public void initialize(List<String> playerNames) {
		// TODO Auto-generated method stub

	}

	public Player play() {
		// TODO Auto-generated method stub
		return null;
	}

}
