/**
 * This programs tests the Dijkstra algorithm
 * @author JLepere2
 */
public class DijkstraTester {
	public static void main(String[] args) {
		int[][] adjMatrix = new int[6][6];
		adjMatrix[0][1] = 80;
		adjMatrix[0][3] = 10;
		adjMatrix[1][2] = 20;
		adjMatrix[3][2] = 50;
		adjMatrix[3][4] = 100;
		adjMatrix[3][5] = 200;
		adjMatrix[4][0] = 5;
		adjMatrix[4][5] = 40;
		adjMatrix[5][2] = 10;
		for (int i = 0; i < adjMatrix.length; i ++) {
			for (int j = 0; j < adjMatrix[0].length; j ++) {
				if (adjMatrix[i][j] == 0) {
					adjMatrix[i][j] = -1;
				}
			}
		}
		
		Dijkstra dij = new Dijkstra(adjMatrix, 0);
		dij.solve();
		dij.returnTable();
	}
}
