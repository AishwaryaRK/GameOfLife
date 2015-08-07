package GameOfLife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private static final int SCORE_THREE = 3;
	private static final int SCORE_FOUR = 4;
	private List<Cell> liveCells;
	private List<Cell> nextGeneration;

	public Game() {
		liveCells = new ArrayList<Cell>();
		nextGeneration = new ArrayList<Cell>();
	}

	public void initialize(String fileName) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] s = line.split(",");
				int x = Integer.parseInt(s[0]);
				int y = Integer.parseInt(s[1]);
				this.liveCells.add(new Cell(x, y));
				line = bufferedReader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void play(int numOfIterations) {
		for (int i = 1; i <= numOfIterations; i++) {
			getNextGeneration();
		}
	}

	private void getNextGeneration() {
		for (Cell cell : liveCells) {
			decideOnCell(cell);
		}
		liveCells = nextGeneration;
		nextGeneration = new ArrayList<Cell>();
	}

	private void decideOnCell(Cell cell) {
		if (nextGeneration.contains(cell)) {
			return;
		}
		int score = getScore(cell);
		switch (score) {
		case SCORE_THREE:
			nextGeneration.add(cell);
			break;
		case SCORE_FOUR:
			if (isLive(cell)) {
				nextGeneration.add(cell);
			}
			break;
		default:
			return;
		}
		List<Cell> neighbours = cell.getNeighbours();
		for (Cell neighbour : neighbours) {
			decideOnCell(neighbour);
		}
	}

	private int getScore(Cell cell) {
		int score = isLive(cell) ? 1 : 0;
		List<Cell> neighbours = cell.getNeighbours();
		for (Cell neighbour : neighbours) {
			if (isLive(neighbour)) {
				score++;
			}
		}
		return score;
	}

	private boolean isLive(Cell cell) {
		return liveCells.contains(cell);
	}

	public void display() {
		System.out.println(liveCells);
	}
}
