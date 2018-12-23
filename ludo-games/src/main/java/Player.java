
public class Player {
	private int id;
	private String name;
	private int position;
	public Player(int id, String name, int position) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", position=" + position + "]";
	}
	
	
	
	
}
