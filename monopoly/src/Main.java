public class Main {
	public static void main(String[] args) {
		Game theGame = new Game(1200, 960, "Monopoly");
		theGame.init();
		theGame.run();
	}
}