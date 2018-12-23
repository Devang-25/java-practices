import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeLadder extends LudoGames {

	private Random random = new Random();
	private SnakeLadderBoard board;
	private List<Player> players;
	private int boardSize;

	public SnakeLadder() {
		super();
		this.boardSize = 100;
	}

	public SnakeLadder(int boardSize) {
		super();
		this.boardSize = boardSize;
	}

	public void initialize(List<String> names) {
		this.board = new SnakeLadderBoard(boardSize);
		players = new ArrayList<Player>();
		int counter = 0;
		for (String name : names) {
			players.add(new Player(++counter, name, 1));
		}
		board.setup();
	}

	public Player play() {
		int maxvalue = 1;
		Player winner = null;
		while (maxvalue < 100) {
			for (Player p : players) {
				
				int pos = rollDice() + p.getPosition();
				for(BoardEntity en: board.getLadders()) {
					if(en.getStart()==pos) {
						pos= en.getEnd();
						System.out.println(p+ " moves from  " + en.getStart() +" to pos " + pos +" after climbing ladder");
						break;
					}
				}
				for(BoardEntity en: board.getSnakes()) {
					if(en.getStart()==pos) {
						pos= en.getEnd();
						System.out.println(p+ " moves to pos " + pos +" after snake bite");
						break;
					}
				}

				if (pos <= 100) {
					p.setPosition(pos);
					System.out.println(p + " after rolling");
					if (pos > maxvalue)
						maxvalue = pos;
					if (pos == 100) {
						winner = p;
						break;
					}
				}
			}
		}
		return winner;
	}

	public int rollDice() {
		return random.nextInt(6) + 1;
	}

}
