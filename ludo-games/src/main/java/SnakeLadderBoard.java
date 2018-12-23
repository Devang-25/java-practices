import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SnakeLadderBoard implements Board {
	private int[] positions;
	private int size;
	private final int LADDERS = 6;
	private final int SNAKES = 6;
	private Random random;
	private List<BoardEntity> ladders;
	private List<BoardEntity> snakes;

	public SnakeLadderBoard(int size) {
		positions = new int[size];
		random = new Random();
		ladders = new ArrayList<BoardEntity>();
		snakes = new ArrayList<BoardEntity>();
	}

	// a hashmap keys ensures valid positions for snakes and ladders
	// start of two thing from a position is invalid
	// start of one and end of other from a position is invalid
	// end of two thing does not have any impact so its valid
	public void setup() {
		Map<Integer, Integer> startMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < LADDERS; i++) {

			int end = random.nextInt(90) + 10;
			int start = random.nextInt(end-9) + 2;
			while (startMap.put(start, end) != null || startMap.put(end, start) != null) {
				end = random.nextInt(90) + 10;
				start = random.nextInt(end-2) + 2;

			}
			ladders.add(new BoardEntity(start, end));
		}

		for (int i = 0; i < SNAKES; i++) {

			int start = random.nextInt(90) + 10;
			int end = random.nextInt(start-9) + 2;
			while (startMap.put(start, end) != null|| startMap.put(end, start) != null) {
				start = random.nextInt(90) + 10;
				end = random.nextInt(start -9) + 2;

			}
			snakes.add(new BoardEntity(start, end));
		}
		System.out.println("ladders: ");
		print(ladders);
		System.out.println("---------------");
		System.out.println("snakes: ");
		print(snakes);
	}
	
	public void print(List<BoardEntity> entities) {
		
		for(BoardEntity en: entities) {
			System.out.println(en.getStart() + ", " +en.getEnd());
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<BoardEntity> getLadders() {
		return ladders;
	}

	public void setLadders(List<BoardEntity> ladders) {
		this.ladders = ladders;
	}

	public List<BoardEntity> getSnakes() {
		return snakes;
	}

	public void setSnakes(List<BoardEntity> snakes) {
		this.snakes = snakes;
	}

}
