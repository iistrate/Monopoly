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
	int bigTileSizeW = 85, bigTileSizeH =83;
	int smallTileSideW = 85, smallTileSideH = 52;
	int smallTileTopW = 52, smallTileTopH = 83;
	int centerTileWidth = 468;
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
		addtile(bigTileSizeW, bigTileSizeH, 10, 0, spriteRightPos, 0, false); // jail
		addtile(bigTileSizeW, bigTileSizeH, 0, 10, 0, spriteTopPos, false); // go to jail

		// add top and bottom row of board tiles
		int tempx = bigTileSizeW;

		for (int i = 0; i < 9; i++) {
			addtile(smallTileTopW, smallTileTopH, i + 1, 0, tempx, 0, true); // top properties
			addtile(smallTileTopW, smallTileTopH, i + 1, 10, tempx, spriteTopPos, true); // bottom properties
			tempx = tempx + smallTileTopW;
		}

		// add left and right side of tiles
		int tempy = bigTileSizeH;
		for (int i = 0; i < 9; i++) {
			addtile(smallTileSideW, smallTileSideH, 0, i + 1, 0, tempy, true); // left properties
			addtile(smallTileSideW, smallTileSideH, 10, i + 1, spriteRightPos, tempy, true); // right properties
			tempy = tempy + smallTileSideH;
		}

		// add center tile
		addtile(centerTileWidth, centerTileHeight, 1, 1, bigTileSizeW, bigTileSizeH, false);

		// Player stuff
		addplayer(38, 52, 10, 0, 0, 0);
		addplayer(38, 52, 10, 0, 38, 0);
	}
	
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