
/**
 * This program performs Dijkstra's algorithm for minimal mapping
 * @author JLepere2
 */
public class Dijkstra {

	private int[][] adjacencyMatrix; //The adjacency Matrix
	private VertexNode[] table; //The table (will be complete at the end)
	private int currentIndex; //The current vertex index to evaluate
	
	/**
	 * Initializes the parameters to solve the algorithm
	 * @param adjacencyMatrix the initial adjacency matrix
	 * @param startingIndex the starting index
	 */
	public Dijkstra(int[][] adjacencyMatrix, int startingIndex) {
		this.adjacencyMatrix = adjacencyMatrix;
		this.currentIndex = startingIndex;
		
		table = new VertexNode[adjacencyMatrix.length];
		initTable(); //Initializes the table
	}
	
	/**
	 * Initializes the table 
	 */
	private void initTable() {
		for(int i = 0; i < table.length; i ++) {
			if (i == currentIndex) {
				table[i] = new VertexNode(i, false, 0, null);
			} else {
				table[i] = new VertexNode(i, false, -1, null);
			}
		}
	}
	
	/**
	 * Solves the algorithm
	 * The table will be the final result
	 */
	public void solve() {
		while (!allKnown()) {
			VertexNode v = table[currentIndex];
			v.known = true;
			for (int j = 0; j < adjacencyMatrix.length; j ++) {
				if (adjacencyMatrix[currentIndex][j] >= 0) {
					VertexNode w = table[j];
					int cvw = adjacencyMatrix[currentIndex][j];
					if (w.dv < 0) {
						w.dv = cvw + v.dv;
						w.pv = v;
					} else {
						if (cvw + v.dv < w.dv) {
							w.dv = cvw + v.dv;
							w.pv = v;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Checks if there are more unknown vertexes to evaluate
	 * @return true if all vertexes are known
	 */
	private boolean allKnown() {
		for (int i = 0; i < table.length; i ++) {
			if (!table[i].known) {
				nextV();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Sets the new current index
	 */
	private void nextV() {
		int next = 0;
		for (int i = 0; i < table.length; i ++) {
			if (table[i].known == false) {
				if (table[next].known == true) {
					next = i;
				} else {
					if (table[i].dv >= 0) {
						if (table[i].dv < table[next].dv) {
							next = i;
						}
					}
				}
			}
		}
		currentIndex = next;
	}
	
	/**
	 * Returns the table as a string
	 * Used for testing
	 */
	public void returnTable() {
		for (int i = 0; i < table.length; i ++) {
			System.out.println(table[i].toString());
		}
	}
	
	class VertexNode {
		
		private int name;
		private boolean known;
		private int dv; //-1 if infinity
		private VertexNode pv;
		
		private VertexNode(int name, boolean known, int dv, VertexNode pv) {
			this.name = name;
			this.known = known;
			this.dv = dv;
			this.pv = pv;
		}
		
		public String toString() {
			String result = "";
			result += name + " ";
			result += known + " ";
			result += dv + " ";
			if (pv != null) {
				result += pv.name + " ";
			} else {
				result += "null ";
			}
			return result;
		}
	}
	
}
