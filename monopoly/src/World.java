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
		if (plrPosX == 10 && plrPosY == 10)
			currentsquare = 1;
		if (plrPosX == 9 && plrPosY == 10)
			currentsquare = 2;
		if (plrPosX == 8 && plrPosY == 10)
			currentsquare = 3;
		if (plrPosX == 7 && plrPosY == 10)
			currentsquare = 4;
		if (plrPosX == 6 && plrPosY == 10)
			currentsquare = 5;
		if (plrPosX == 5 && plrPosY == 10)
			currentsquare = 6;
		if (plrPosX == 4 && plrPosY == 10)
			currentsquare = 7;
		if (plrPosX == 3 && plrPosY == 10)
			currentsquare = 8;
		if (plrPosX == 2 && plrPosY == 10)
			currentsquare = 9;
		if (plrPosX == 1 && plrPosY == 10)
			currentsquare = 10;
		if (plrPosX == 0 && plrPosY == 10)
			currentsquare = 11;
		if (plrPosX == 0 && plrPosY == 9)
			currentsquare = 12;
		if (plrPosX == 0 && plrPosY == 8)
			currentsquare = 13;
		if (plrPosX == 0 && plrPosY == 7)
			currentsquare = 14;
		if (plrPosX == 0 && plrPosY == 6)
			currentsquare = 15;
		if (plrPosX == 0 && plrPosY == 5)
			currentsquare = 16;
		if (plrPosX == 0 && plrPosY == 4)
			currentsquare = 17;
		if (plrPosX == 0 && plrPosY == 3)
			currentsquare = 18;
		if (plrPosX == 0 && plrPosY == 2)
			currentsquare = 19;
		if (plrPosX == 0 && plrPosY == 1)
			currentsquare = 20;
		if (plrPosX == 0 && plrPosY == 0)
			currentsquare = 21;
		if (plrPosX == 1 && plrPosY == 0)
			currentsquare = 22;
		if (plrPosX == 2 && plrPosY == 0)
			currentsquare = 23;
		if (plrPosX == 3 && plrPosY == 0)
			currentsquare = 24;
		if (plrPosX == 4 && plrPosY == 0)
			currentsquare = 25;
		if (plrPosX == 5 && plrPosY == 0)
			currentsquare = 26;
		if (plrPosX == 6 && plrPosY == 0)
			currentsquare = 27;
		if (plrPosX == 7 && plrPosY == 0)
			currentsquare = 28;
		if (plrPosX == 8 && plrPosY == 0)
			currentsquare = 29;
		if (plrPosX == 9 && plrPosY == 0)
			currentsquare = 30;
		if (plrPosX == 10 && plrPosY == 0)
			currentsquare = 31;
		if (plrPosX == 10 && plrPosY == 1)
			currentsquare = 32;
		if (plrPosX == 10 && plrPosY == 2)
			currentsquare = 33;
		if (plrPosX == 10 && plrPosY == 3)
			currentsquare = 34;
		if (plrPosX == 10 && plrPosY == 4)
			currentsquare = 35;
		if (plrPosX == 10 && plrPosY == 5)
			currentsquare = 36;
		if (plrPosX == 10 && plrPosY == 6)
			currentsquare = 37;
		if (plrPosX == 10 && plrPosY == 7)
			currentsquare = 38;
		if (plrPosX == 10 && plrPosY == 8)
			currentsquare = 39;
		if (plrPosX == 10 && plrPosY == 9)
			currentsquare = 40;
		// we know where we are, where are we going?
		gotosquare = currentsquare + dice;
		
		if (gotosquare > 40) // translate numbers above 40
			gotosquare = gotosquare - 40;
		
		// go to needed square on board
		switch (gotosquare) {
		case 1:
			tmpPlayer.setX(10);
			tmpPlayer.setY(10);
			break;
		case 2:
			tmpPlayer.setX(9);
			tmpPlayer.setY(10);
			break;
		case 3:
			tmpPlayer.setX(8);
			tmpPlayer.setY(10);
			break;
		case 4:
			tmpPlayer.setX(7);
			tmpPlayer.setY(10);
			break;
		case 5:
			tmpPlayer.setX(6);
			tmpPlayer.setY(10);
			break;
		case 6:
			tmpPlayer.setX(5);
			tmpPlayer.setY(10);
			break;
		case 7:
			tmpPlayer.setX(4);
			tmpPlayer.setY(10);
			break;
		case 8:
			tmpPlayer.setX(3);
			tmpPlayer.setY(10);
			break;
		case 9:
			tmpPlayer.setX(2);
			tmpPlayer.setY(10);
			break;
		case 10:
			tmpPlayer.setX(1);
			tmpPlayer.setY(10);
			break;
		case 11:
			tmpPlayer.setX(0);
			tmpPlayer.setY(10);
			break;
		case 12:
			tmpPlayer.setX(0);
			tmpPlayer.setY(9);
			break;
		case 13:
			tmpPlayer.setX(0);
			tmpPlayer.setY(8);
			break;
		case 14:
			tmpPlayer.setX(0);
			tmpPlayer.setY(7);
			break;
		case 15:
			tmpPlayer.setX(0);
			tmpPlayer.setY(6);
			break;
		case 16:
			tmpPlayer.setX(0);
			tmpPlayer.setY(5);
			break;
		case 17:
			tmpPlayer.setX(0);
			tmpPlayer.setY(4);
			break;
		case 18:
			tmpPlayer.setX(0);
			tmpPlayer.setY(3);
			break;
		case 19:
			tmpPlayer.setX(0);
			tmpPlayer.setY(2);
			break;
		case 20:
			tmpPlayer.setX(0);
			tmpPlayer.setY(1);
			break;
		case 21:
			tmpPlayer.setX(0);
			tmpPlayer.setY(0);
			break;
		case 22:
			tmpPlayer.setX(1);
			tmpPlayer.setY(0);
			break;
		case 23:
			tmpPlayer.setX(2);
			tmpPlayer.setY(0);
			break;
		case 24:
			tmpPlayer.setX(3);
			tmpPlayer.setY(0);
			break;
		case 25:
			tmpPlayer.setX(4);
			tmpPlayer.setY(0);
			break;
		case 26:
			tmpPlayer.setX(5);
			tmpPlayer.setY(0);
			break;
		case 27:
			tmpPlayer.setX(6);
			tmpPlayer.setY(0);
			break;
		case 28:
			tmpPlayer.setX(7);
			tmpPlayer.setY(0);
			break;
		case 29:
			tmpPlayer.setX(8);
			tmpPlayer.setY(0);
			break;
		case 30:
			tmpPlayer.setX(9);
			tmpPlayer.setY(0);
			break;
		case 31:
			tmpPlayer.setX(10);
			tmpPlayer.setY(0);
			break;
		case 32:
			tmpPlayer.setX(10);
			tmpPlayer.setY(1);
			break;
		case 33:
			tmpPlayer.setX(10);
			tmpPlayer.setY(2);
			break;
		case 34:
			tmpPlayer.setX(10);
			tmpPlayer.setY(3);
			break;
		case 35:
			tmpPlayer.setX(10);
			tmpPlayer.setY(4);
			break;
		case 36:
			tmpPlayer.setX(10);
			tmpPlayer.setY(5);
			break;
		case 37:
			tmpPlayer.setX(10);
			tmpPlayer.setY(6);
			break;
		case 38:
			tmpPlayer.setX(10);
			tmpPlayer.setY(7);
			break;
		case 39:
			tmpPlayer.setX(10);
			tmpPlayer.setY(8);
			break;
		case 40:
			tmpPlayer.setX(10);
			tmpPlayer.setY(9);
			break;
		}
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