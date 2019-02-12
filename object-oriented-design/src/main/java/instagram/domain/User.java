package instagram.domain;

import java.util.ArrayList;

public class User {
	private ArrayList<Integer> friends;
	private ArrayList<Integer> pictures;
	private int userID;
	private int machineID;
	private String information;
	private Server server = new Server();
	private Storage storage = new Storage();

	public User(int userID, int machineID) {
		this.userID = userID;
		this.machineID = machineID;
		pictures = new ArrayList<Integer>();
		friends = new ArrayList<Integer>();
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public int getID() {
		return userID;
	}

	public int getMachineID() {
		return machineID;
	}

	public void addFriend(int id) {
		friends.add(id);
	}

	public void addPicture(int id) {
		pictures.add(id);
	}

	public int[] getFriends() {
		int[] temp = new int[friends.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = friends.get(i);
		}
		return temp;
	}

	public int[] getPictures() {
		int[] temp = new int[pictures.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = pictures.get(i);
		}
		return temp;
	}

	public User lookUpFriend(int machineID, int ID) {
		for (Machine m : server.machines) {
			if (m.machineID == machineID) {
				for (User user : m.users) {
					if (user.userID == ID) {
						return user;
					}
				}
			}
		}
		return null;
	}

	public Picture lookUpPicture(int machineID, int ID) {
		for (StorageMachine m : storage.machines) {
			if (m.machineID == machineID) {
				for (Picture picture : m.pictures) {
					if (picture.getPictureID() == ID) {
						return picture;
					}
				}
			}
		}
		return null;
	}
}
