/*
 * Author: Ioan George Istrate
 */

/*
 * Holds main function; Creates instance of Game, initializes and runs it
 */
public class Main {
	public static void main(String[] args) {
		int width = 800;
		int height = 600;
	
		//instance of game
		Game theGame = new Game(width, height, "Monopoly");
		//game loop conditions set to true 
		theGame.init();
		//contains game loop
		theGame.run();
	}
}