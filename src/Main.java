/*
 * Author: Ioan George Istrate
 */

/*
 * Holds main function; Creates instance of Game, initializes and runs it
 */
public class Main {
	public static void main(String[] args) {
		//instance of game
		Game theGame = new Game(1200, 960, "Monopoly");
		//game loop conditions set to true 
		theGame.init();
		//contains game loop
		theGame.run();
	}
}