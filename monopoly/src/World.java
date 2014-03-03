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

	public World() { // all tiles need to be added here
		// add four corners
		addtile(115, 115, 0, 0, 0, 0); // free parking
		addtile(115, 115, 10, 10, 781, 781); // go
		addtile(115, 115, 10, 0, 0, 781); // jail
		addtile(115, 115, 0, 10, 781, 0); // go to jail

		// add top and bottom row of board tiles
		int tempx = 121;
		for (int i = 0; i < 9; i++) {
			addtile(73, 115, i + 1, 0, tempx, 0); // top properties
			addtile(73, 115, i + 1, 10, tempx, 781); // bottom properties
			tempx = tempx + 73;
		}

		// add left and right side of tiles
		int tempy = 119;
		for (int i = 0; i < 9; i++) {
			addtile(115, 73, 0, i + 1, 0, tempy); // left properties
			addtile(115, 73, 10, i + 1, 780, tempy); // right properties
			tempy = tempy + 73;
		}

		// add center tile
		addtile(657, 622, 1, 1, 121, 119);

		// Player stuff
		addplayer(38, 52, 10, 0, 0, 0);
		// addplayer(38, 52, 9, 0, 38, 0 );
	}

	// push a new player object on the playerlist
	public void addplayer(int w, int h, int x, int y, int sX, int sY) {
		playerlist.add(new Player(w, h, x, y, sX, sY));
	}

	// push a new tile object on tilelist
	public void addtile(int w, int h, int x, int y, int sX, int sY) {
		tilelist.add(new Tile(w, h, x, y, sX, sY));
	}

	// return the entire playerlist, good if another class needs the array
	public ArrayList<Player> getPlayerlist() {
		return playerlist;
	}

	// return the entire arraylist, good if another class needs the array
	public ArrayList<Tile> getTilelist() {
		return tilelist;
	}

	// to set the entire arraylist
	public static void setTilelist(ArrayList<Tile> tilelist) {
		World.tilelist = tilelist;
	}

	public boolean isplayerinjail(int inplayer){
		inplayer = inplayer - 1;
		Player tmpPlayer = null;
		// get correct player from list
		for (int i = 0; i < playerlist.size(); i++) {
			if (i == inplayer) {
				tmpPlayer = playerlist.get(i);
			}
		}
		return tmpPlayer.isM_binjail();
	}
	
	public void movePlayer(int dice, int playerToMove) {
		int plr2move = playerToMove - 1;
		int plrPosX;
		int plrPosY;
		int currentsquare = 0;
		int gotosquare = 0;
		Player tmpPlayer = null;
		
		// get correct player from list
		for (int i = 0; i < playerlist.size(); i++) {
			if (i == plr2move) {
				tmpPlayer = playerlist.get(i);
			}
		}
		plrPosX = tmpPlayer.getX();
		plrPosY = tmpPlayer.getY();
		
		// translate X and Y to a square from 1 -> 40 starting at GO (10,10) ending at BoardWalk (10,9)
		int tmpcnt = 1;
		for (int i = 10; i >= 0; i--){
			if (plrPosX == i && plrPosY == 10) // 1 - 11
				currentsquare = tmpcnt;
			else if (plrPosX == 0 && plrPosY == i) // 11 - 21
				currentsquare = 10 + tmpcnt;	
			else if (plrPosX == i && plrPosY == 0) // 21 - 31
				currentsquare = 21 + i;	
			else if(plrPosX == i && plrPosY == 0) // 31 - 1
				currentsquare = 31 + i;	
			else
				System.out.println("");
			tmpcnt++;
		}
		
		// we know where we are, where are we going?
		gotosquare = currentsquare + dice;
		
		if (gotosquare > 40){ // translate numbers above 40
			gotosquare = gotosquare - 40;
			tmpPlayer.setM_iplayercash(500); //$500 for passing go
		}
		
		//check if player planded on tax / jail / etc.
		if (gotosquare == 5) // -$200 income tax
			tmpPlayer.setM_iplayercash(-200);
		if (gotosquare == 39) // -$200 super tax
			tmpPlayer.setM_iplayercash(-200);
		if (gotosquare == 31){
			gotosquare = 11;
			tmpPlayer.setM_binjail(true);
		}
		
		
		// go to needed square on board
		if (gotosquare < 12){
			tmpPlayer.setX(11 - gotosquare);
			tmpPlayer.setY(10);
		}
		else if (gotosquare < 22 && gotosquare > 11){
			tmpPlayer.setX(0);
			tmpPlayer.setY(21 - gotosquare);
		}
		else if (gotosquare < 32 && gotosquare > 21){
			tmpPlayer.setX(gotosquare - 21);
			tmpPlayer.setY(0);
		}
		else if (gotosquare < 41 && gotosquare > 31){
			tmpPlayer.setX(10);
			tmpPlayer.setY(gotosquare - 31);
		}
		else 
			System.out.println("");
		
		// insert player back into playerlist
		for (int i = 0; i < playerlist.size(); i++) {
			if (i == plr2move) {
				playerlist.set(i, tmpPlayer);
			}
		}
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