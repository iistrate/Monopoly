/*
 * Author: Jay Bingley, Ioan George Istrate
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class World {
	// arraylist for tile objects
	private static ArrayList<Tile> tilelist = new ArrayList<Tile>();
	private static ArrayList<Player> playerlist = new ArrayList<Player>();
	//dimensions
	int bigTileSizeW = 85, bigTileSizeH = 84;
	int smallTileW = 52, smallTileH = 52;
	int centerTileWidth = 470;
	int centerTileHeight = 435; /* we're missing 35 pixels :) */
	//
	int imageSizeW = 640;
	int imageSizeH = 640;
	//
	int spriteRightPos = imageSizeW - bigTileSizeW;
	int spriteTopPos = imageSizeH - bigTileSizeH;
	
	public World() { // all tiles need to be added here
		
		// add four corners		
		addtile(bigTileSizeW, bigTileSizeH, 0, 0, 0, 0, false); // free parking
		addtile(bigTileSizeW, bigTileSizeH, 10, 10, spriteRightPos, spriteTopPos, false); // go
		addtile(bigTileSizeW, bigTileSizeH, 10, 0, 0, spriteTopPos, false); // jail
		addtile(bigTileSizeW, bigTileSizeH, 0, 10, spriteRightPos, 0, false); // go to jail

		// add top and bottom row of board tiles
		int tempx = bigTileSizeW;

		for (int i = 0; i < 9; i++) {
			addtile(smallTileW, bigTileSizeH, i + 1, 0, tempx, 0, true); // top properties
			addtile(smallTileW, bigTileSizeH, i + 1, 10, tempx, spriteTopPos, true); // bottom properties
			tempx = tempx + smallTileW;
		}

		// add left and right side of tiles
		int tempy = bigTileSizeH;
		for (int i = 0; i < 9; i++) {
			addtile(bigTileSizeW, smallTileH, 0, i + 1, 0, tempy, true); // left properties
			addtile(bigTileSizeW, smallTileH, 10, i + 1, spriteRightPos, tempy, true); // right properties
			tempy = tempy + smallTileH;
		}

		// add center tile
		addtile(centerTileWidth, centerTileHeight, 1, 1, bigTileSizeW, bigTileSizeH, false);

		// Player stuff
		addplayer(38, 52, 10, 0, 0, 0);
		addplayer(38, 52, 9, 0, 38, 0);
	}

	// load property information into tilelist.
//	public void setupProperties(){
//		try {
//			File file = new File("assets/properties.txt");
//			Scanner scanner = new Scanner(file);
//			
//			ArrayList<Tile> tmptilelist = new ArrayList<Tile>();
//			
//			while(scanner.hasNextLine()){
//				String line = scanner.nextLine();
//				String[] details = line.split(" ");
//				
//				String name = details[0];
//				int position = Integer.parseInt(details[1]);
//				int price = Integer.parseInt(details[2]);
//				int rent = Integer.parseInt(details[3]);
//				String group = details[4];
//				
//				for (int i = 0; i < tilelist.size(); i++){
//					Tile tmptile = tilelist.get(i);
///*					if (tmptile.getBoardSquare() == position){
//						tmptile.setName(name);
//						tmptile.setSquarePrice(price);
//						tmptile.setSquareRent(rent);
//						tmptile.setSuqareGroup(group);
//						tilelist.set(i, tmptile);
//					}*/
//				}		
//			}			
//		}catch (FileNotFoundException e) {         
//            e.printStackTrace();
//        }		
//	}
	
	// push a new player object on the playerlist
	public void addplayer(int w, int h, int x, int y, int sX, int sY) {
		playerlist.add(new Player(w, h, x, y, sX, sY));
	}

	// push a new tile object on tilelist
	public void addtile(int w, int h, int x, int y, int sX, int sY, boolean canBeBought) {
		tilelist.add(new Tile(w, h, x, y, sX, sY, canBeBought));

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

	public boolean isplayerinjail(int inplayer) {
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

	public void playerleavejail(int inplayer) {
		inplayer = inplayer - 1;
		Player tmpPlayer = null;
		// get correct player from list
		for (int i = 0; i < playerlist.size(); i++) {
			if (i == inplayer) {
				tmpPlayer = playerlist.get(i);
				tmpPlayer.setM_binjail(false);
				playerlist.set(i, tmpPlayer);
			}
		}

	}
	
	public void printPlayerposition(){
		
	}
	
	// Puts all players on go
	public void resetPlayers(){
		Player tmpPlayer = null;
		for (int i = 0; i < playerlist.size(); i++) {
			tmpPlayer = playerlist.get(i);
			tmpPlayer.setX(10);
			tmpPlayer.setY(10);
		}
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

		// translate X and Y to a square from 1 -> 40 starting at GO (10,10)
		// ending at BoardWalk (10,9)
		int tmpcnt = 1;
		for (int i = 10; i >= 0; i--) {
			if (plrPosX == i && plrPosY == 10) // 1 - 11
				currentsquare = tmpcnt;
			else if (plrPosX == 0 && plrPosY == i) // 11 - 21
				currentsquare = 10 + tmpcnt;
			else if (plrPosX == i && plrPosY == 0) // 21 - 31
				currentsquare = 21 + i;
			else if (plrPosX == 10 && plrPosY == i) // 31 - 1
				currentsquare = 31 + i;
			else
				System.out.println("");
			tmpcnt++;
		}

		// we know where we are, where are we going?
		gotosquare = currentsquare + dice;
		if (gotosquare > 40) { // translate numbers above 40
			gotosquare = gotosquare - 40;
			tmpPlayer.setM_iplayercash(500); // $500 for passing go
		}

		// check if player landed on tax / jail / etc.
		if (gotosquare == 5) // -$200 income tax
			tmpPlayer.setM_iplayercash(-200);
		if (gotosquare == 39) // -$200 super tax
			tmpPlayer.setM_iplayercash(-200);
		if (gotosquare == 31) {
			gotosquare = 11;
			tmpPlayer.setM_binjail(true);
		}

		// go to needed square on board
		if (gotosquare < 12) {
			tmpPlayer.setX(11 - gotosquare);
			tmpPlayer.setY(10);
		} else if (gotosquare < 22 && gotosquare > 11) {
			tmpPlayer.setX(0);
			tmpPlayer.setY(21 - gotosquare);
		} else if (gotosquare < 32 && gotosquare > 21) {
			tmpPlayer.setX(gotosquare - 21);
			tmpPlayer.setY(0);
		} else if (gotosquare < 41 && gotosquare > 31) {
			tmpPlayer.setX(10);
			tmpPlayer.setY(gotosquare - 31);
		} else
			System.out.println("");

		// insert player back into playerlist
		for (int i = 0; i < playerlist.size(); i++) {
			if (i == plr2move) {
				playerlist.set(i, tmpPlayer);
			}
		}
	}

//	//the dimensions in this method aren't pixel perfect, need a little tweaking.
//	public int getboardsqaure(int plrPosX, int plrPosY) {
//		int square = 0;
//		int tmpX = spriteRightPos;
//		int tmpY = spriteTopPos;
//		//bottom line top of carx y=757
//		//bottom row 1-11
//		if (plrPosY > 757)
//			square = 1;
//		// top
//		if (plrPosY < 140)
//			square = 31;
//		// left side
//		if (plrPosY > 140 && plrPosX < 116 && plrPosY < 781)
//			square = 11;
//		if (plrPosY < 781 && plrPosX > 781 && plrPosY > 140)
//			square = 41;
//
//		if (plrPosY < 140 || plrPosY > 757) {
//			while (tmpX > 115) {
//				// bottom row
//				if (plrPosX < tmpX && plrPosY > 757)
//					square++;
//				//top
//				if (plrPosX < tmpX && plrPosY < 140)
//					square--;
//				tmpX = tmpX - smallTileW;
//			}
//		}
//		
//		if(plrPosY > 140 && plrPosY < 781){
//			while (tmpY < 782 && tmpY > 140){
//				//right side
//				if (plrPosY < tmpY && plrPosX > 757)
//					square--;
//				//left side
//				if (plrPosY < tmpY && plrPosX < 140)
//					square++;
//				tmpY = tmpY - 67; //smallTileW
//			}
//		}
//		return square;
//	}
	
	public Tile getTile(int x, int y) {
		Tile fTile = new Tile();
		for (int i = 0; i < tilelist.size(); i++) {
			if (tilelist.get(i).getX() == x && tilelist.get(i).getY() == y) {
				fTile = tilelist.get(i);
			}
		}
		return fTile;
	}

	
}