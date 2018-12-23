import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class LudoApp {
	private Ludo ludo;

	LudoApp(Ludo ludo) {
		this.ludo = ludo;
	}

	public void start(List<String> playerNames) {
		ludo.initialize(playerNames);

		System.out.println("playing game");

	}

	public Player play() {
		return ludo.play();
	}

	public static void main(String[] args) {

		System.out.println("Enter names separated by space");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			String line = br.readLine();
			List<String> names = Arrays.asList(line.split(" "));
			int boardSize = 100;
			Ludo ludo = null;
			System.out.println("Enter option number: 1. Basic ludo, 2. SnakeLadder");
			int maxtrials=3;
			while(maxtrials >0) {
				String option = br.readLine();
				if (option.equals("1")) {
					ludo = new BasicLudo();
				} else if (option.equals("2")) {
					ludo = new SnakeLadder(boardSize);
				} else {
					System.out.println("Invalid input. Enter again");
				}
				maxtrials--;
			}
			if(ludo !=null) {
				LudoApp app = new LudoApp(ludo);
				app.start(names);
				Player winner = app.play();
				System.out.println(winner + " won the game");
			} else {
				System.out.println("All trials over.");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
