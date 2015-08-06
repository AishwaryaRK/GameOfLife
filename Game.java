package GameOfLife;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private List<Cell> liveCells;
	List<Cell> nextGeneration;

	public Game() {
		liveCells = new ArrayList<Cell>();
		nextGeneration = new ArrayList<Cell>();
	}

	public void initializeGrid(String fileName) {
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
		case 3:
			nextGeneration.add(cell);
			break;
		case 4:
			if (isLive(cell)) {
				nextGeneration.add(cell);
			}
			break;
		default:
			return;
		}
		List<Cell> neighbours = getNeighbours(cell);
		for (Cell neighbour : neighbours) {
			decideOnCell(neighbour);
		}
	}

	private int getScore(Cell cell) {
		int score = isLive(cell) ? 1 : 0;
		List<Cell> neighbours = getNeighbours(cell);
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

	private List<Cell> getNeighbours(Cell cell) {
		List<Cell> neighbours = new ArrayList<Cell>();
		int x = cell.getX();
		int y = cell.getY();
		neighbours.add(new Cell(x - 1, y + 1));
		neighbours.add(new Cell(x - 1, y));
		neighbours.add(new Cell(x - 1, y - 1));
		neighbours.add(new Cell(x + 1, y + 1));
		neighbours.add(new Cell(x + 1, y));
		neighbours.add(new Cell(x + 1, y - 1));
		neighbours.add(new Cell(x, y - 1));
		neighbours.add(new Cell(x, y + 1));
		return neighbours;
	}

	public void display() {
		
	}
}
