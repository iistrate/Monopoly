import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class World {
	// arraylist for tile objects
	private static ArrayList<Tile> tilelist = new ArrayList<Tile>();
	private static ArrayList<Player> playerlist = new ArrayList<Player>();

	public World(){		// all tiles need to be added here
		//add four corners
		addtile(115, 115, 0, 0, 0, 0); // free parking 
		addtile(115, 115, 30, 30, 781, 781 ); // go 
		addtile(115, 115, 30, 0, 0, 781); //jail
		addtile(115, 115, 0, 30, 781, 0); // go to jail
		
		// add top and bottom row of board tiles
		int tempx = 121;
		for (int i = 0; i < 9; i++){
			addtile(73, 115, i+1, 0, tempx, 0); //top properties
			addtile(73, 115, i+1, 30, tempx, 781); // bottom properties
			tempx = tempx + 73;
		}
	
		// add left and right side of tiles
		// SOMETHING IS CREATING A WHITE SPACE AT THE TOP OF THE SPRITE HELP!
		int tempy = 119;
		for (int i = 0; i < 9; i++){
			addtile(115, 73, 0, i+1, 0, tempy); // left properties
			addtile(115, 73, 30, i+1, 780, tempy); // right properties
			tempy = tempy + 73;
		}
		
		//add center tile
		addtile(657, 657, 1, 1, 121, 119);
		
		//Player stuff
		addplayer(38, 52, 0, 0, 0, 0 );
		addplayer(38, 52, 1, 0, 38, 0 );
	}

	//push a new player object on the playerlist
	public void addplayer(int w, int h, int x, int y, int sX, int sY) {
		playerlist.add(new Player(w, h, x, y, sX, sY));
	}

	
	// push a new tile object on tilelist
	public void addtile(int w, int h, int x, int y, int sX, int sY) {
		tilelist.add(new Tile(w, h, x, y, sX, sY));
	}
	// return the entire playerlist, good if another class needs the array
		public static ArrayList<Player> getPlayerlist() {
			return playerlist;
		}
	// return the entire arraylist, good if another class needs the array
	public static ArrayList<Tile> getTilelist() {
		return tilelist;
	}

	// to set the entire arraylist
	public static void setTilelist(ArrayList<Tile> tilelist) {
		World.tilelist = tilelist;
	}

	// Load tilelist from tilelist.dat
	@SuppressWarnings("unchecked")
	public void load() throws IOException, ClassNotFoundException {
		FileInputStream prein = new FileInputStream("tilelist.dat");
		ObjectInputStream in = new ObjectInputStream(prein);
		tilelist = (ArrayList<Tile>) in.readObject();
		in.close();
	}

	// save tile list to tilelist.dat
	public void save() throws IOException {
		FileOutputStream fos = new FileOutputStream("tilelist.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		try {
			oos.writeObject(tilelist);
		} catch (IOException e) {
			e.printStackTrace();
		}
		oos.close();
	}

}