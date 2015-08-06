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
		System.out.println(liveCells);
	}

	private void getNextGeneration() {
		for (Cell cell : liveCells) {
			
			int sum = getScore(cell);
			switch (sum) {
			case 3:
				nextGeneration.add(cell);
				break;
			case 4:
				if (liveCells.contains(cell)) {
					nextGeneration.add(cell);
				}
				break;
			default:
				break;
			}
		}
		liveCells = nextGeneration;
		nextGeneration = new ArrayList<Cell>();
	}

	private int getScore(Cell cell) {
		// TODO Auto-generated method stub
		return 0;
	}
}
