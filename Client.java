package GameOfLife;

public class Client {

	public static void main(String[] args) {
		Game game = new Game();
		game.initializeGrid("game.txt");
		game.play(2);
	}

}
