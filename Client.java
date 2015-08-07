package GameOfLife;

public class Client {

	public static void main(String[] args) {
		Game game = new Game();
		game.initializeGrid("game.txt");
		int numOfIterations = 2;
		game.play(numOfIterations);
		game.display();
	}

}
