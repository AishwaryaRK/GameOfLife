package GameOfLife;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	private int x;
	private int y;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public List<Cell> getNeighbours() {
		List<Cell> neighbours = new ArrayList<Cell>();
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + "]";
	}

}
