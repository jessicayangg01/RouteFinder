/**
 * Class: Path
 * Description: This class finds the path from the starting cell to the destination cell
 * @author jessica yang
 *
 */
public class Path {
	// initialized needed variables
	Map cityMap;
	ArrayStack<MapCell> path;
	MapCell currentCell;

	/**
	 * Constructor will initialize the path stack and pushes the starting cell as the first cell in the map. Then, goes to find path method
	 * @param theMap This will be used to get the starting cell
	 */
	public Path(Map theMap) {
		cityMap = theMap;
		path = new ArrayStack<MapCell>(10, 10, 5);
		currentCell = cityMap.getStart();
		path.push(currentCell);
		currentCell.markInStack();
	}

	/**
	 * FindPath method finds the path from the starting position to the destination
	 */
	public void findPath() {
		boolean foundPath = false;
		try {
		// while the path is not empty and the destination is not found, continues to loop to find a path
		while (!path.isEmpty() && !foundPath) {
			// sets the current cell to what is at the top of the stack
			currentCell = path.peek();
			// if the cell at the top is the destination then prints statement and ends loop
			if (currentCell.isDestination()) {
				foundPath = true;
				System.out.println("Destination was found. There was " + path.size() + " cells from the start to destination.");
				break;
			}
			// if the cell at the top is not the destination then checks if there are valid neighbor cells
			else {				
				// if there is no valid neighbor cells, then continues to take out cells from the stack and backtracks until it finds a path with a valid neighbor
				while (nextCell(currentCell) == null) {
					path.peek().markOutStack();
					path.pop();
					currentCell = path.peek();

				}
				// if there is a valid neighbor, then adds it to the stack and marks as in stack
				if (!nextCell(currentCell).equals(null)) {
					currentCell = nextCell(currentCell);
					currentCell.markInStack();
					path.push(currentCell);
				}
			}
		}
		// catches exceptions
		} catch (EmptyStackException e) {
			System.out.println("No path to the destination was found.");
		} catch (InvalidNeighbourIndexException e) {
			System.out.println("Invalid Neighbour.");
		} catch (InvalidMapException e) {
			System.out.println("Invalid map.");
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal Argument.");
		}
	}

	/**
	 * nextCell method finds and returns the next neighboring cell or returns null if there are no valid neighbors
	 * @param cell This will be the current cell which we will find the neighboring cells off of
	 * @return This method returns neighbor cell of type MapCell
	 */
	private MapCell nextCell(MapCell cell) {
		// checks if the destination is a valid neighboring cell
		for (int j = 0; j<=3 ; j++) {
			if (cell.getNeighbour(j)!= null && !cell.getNeighbour(j).isMarked() && cell.getNeighbour(j).isDestination()) {
				if (cell.isNorthRoad() && j==0 || cell.isEastRoad()&& j==1 || cell.isSouthRoad() && j==2 || cell.isWestRoad()&& j==3 || cell.isIntersection()) {
					return cell.getNeighbour(j);

				}
			}
		}
		// checks if an intersection is a valid neighboring cell
		for (int k = 0; k<=3; k++) {
			if (cell.getNeighbour(k)!= null && !cell.getNeighbour(k).isMarked() && cell.getNeighbour(k).isIntersection()) {
				if (cell.isNorthRoad() && k==0 || cell.isEastRoad()&& k==1 || cell.isSouthRoad() && k==2 || cell.isWestRoad()&& k==3 || cell.isIntersection()) {
					return cell.getNeighbour(k);
				}

			}
		}
		// checks if a directional path is a neighboring cell
		int i = 0;
		while(i<=3) {
			if (cell.getNeighbour(i)!= null && !cell.getNeighbour(i).isMarked()) {
				if (i == 0  && (cell.isNorthRoad() || cell.isIntersection() || cell.isStart()) && (cell.getNeighbour(i).isNorthRoad()|| cell.getNeighbour(i).isIntersection())) {
					return cell.getNeighbour(i);
				} 
				else if (i == 1 && (cell.isEastRoad() || cell.isIntersection()|| cell.isStart()) && (cell.getNeighbour(i).isEastRoad() || cell.getNeighbour(i).isIntersection())) {
					return cell.getNeighbour(i);
				}
				else if (i== 2 && (cell.isSouthRoad() || cell.isIntersection()|| cell.isStart()) && (cell.getNeighbour(i).isSouthRoad()|| cell.getNeighbour(i).isIntersection())) {
					return cell.getNeighbour(i);
				}
				else if (i == 3  && (cell.isWestRoad() || cell.isIntersection()|| cell.isStart()) && (cell.getNeighbour(i).isWestRoad() || cell.getNeighbour(i).isIntersection())) {
					return cell.getNeighbour(i);
				}
			}
			i++;
		}
		// returns null if none of the above can be found
		return null; 
	}
}
