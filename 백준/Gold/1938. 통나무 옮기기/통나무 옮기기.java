import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	private static int num;
	private static char[][] squares;
	private static boolean[][][] visited;
	private static int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; 
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(br.readLine());
		squares = new char[num][num];
		List<int[]> positions = new ArrayList<>();
		List<int[]> ends = new ArrayList<>();
		for (int x = 0; x < num; x++) {
			char[] chars = br.readLine().toCharArray();
			for (int y = 0; y < num; y++) {
				squares[x][y] = chars[y];
				if (squares[x][y] == 'B') {
					positions.add(new int[] {x, y});
					squares[x][y] = '0';
				} else if (squares[x][y] == 'E') {
					ends.add(new int[] {x, y});
					squares[x][y] = '0';
				}
			}
		}
		
		Train start = Train.newInstance(positions);
		Train end = Train.newInstance(ends);
		
		Queue<Train> queue = new LinkedList<>();
		queue.offer(start);
		visited = new boolean[2][num][num];
		while (!queue.isEmpty()) {
			Train pop = queue.poll();
			
			if (pop.centerX == end.centerX && pop.centerY == end.centerY) {
				System.out.println(pop.count);
				System.exit(0);
			}
			
			for (int i = 0; i < directions.length; i++) {
				int newX = pop.centerX + directions[i][0];
				int newY = pop.centerY + directions[i][1];
				int index = pop.isVertical ? 0 : 1;
				
				if (isIn(newX, newY) && !visited[index][newX][newY] && pop.canMove(i)) {
					visited[index][newX][newY] = true;
					queue.offer(new Train(newX, newY, pop.isVertical, pop.count + 1));
				}
			}
			
			if (pop.canRotate() && !visited[pop.isVertical ? 1 : 0][pop.centerX][pop.centerY]) {
				visited[pop.isVertical ? 1 : 0][pop.centerX][pop.centerY] = true;
				queue.offer(new Train(pop.centerX, pop.centerY, !pop.isVertical, pop.count + 1));
			}
		}
		System.out.println(0);
	}
	
	static class Train {
		int centerX;
		int centerY;
		int count;
		boolean isVertical;
		
		public Train(int centerX, int centerY, boolean isVertical, int count) {
			this.centerX = centerX;
			this.centerY = centerY;
			this.isVertical = isVertical;
			this.count = count;
		}
		
		public boolean canMove(int index) {
			if (index == 0) {
				return this.canRight();
			} else if (index == 1) {
				return this.canLeft();
			} else if (index == 2) {
				return this.canDown();
			}
			return this.canUp();
		}
		
		public boolean canRotate() {
			for (int x = centerX - 1; x <= centerX + 1; x++) {
				for (int y = centerY - 1; y <= centerY + 1; y++) {
					if (!isIn(x, y) || squares[x][y] != '0') {
						return false;
					}
				}
			}
			return true;
		}
		
		private boolean canUp() {
			if (isVertical) {
				for (int x = centerX - 1; x <= centerX + 1; x++) {
					if (!isIn(x - 1, centerY) || squares[x - 1][centerY] != '0') {
						return false;
					}
				}
				return true;
			} else {
				for (int y = centerY - 1; y <= centerY + 1; y++) {
					if (!isIn(centerX - 1, y) || squares[centerX - 1][y] != '0') {
						return false;
					}
				}
				return true;
			}
		}
		
		private boolean canDown() {
			if (isVertical) {
				for (int x = centerX - 1; x <= centerX + 1; x++) {
					if (!isIn(x + 1, centerY) || squares[x + 1][centerY] != '0') {
						return false;
					}
				}
				return true;
			} else {
				for (int y = centerY - 1; y <= centerY + 1; y++) {
					if (!isIn(centerX + 1, y) || squares[centerX + 1][y] != '0') {
						return false;
					}
				}
				return true;
			}
		}
		
		private boolean canLeft() {
			if (isVertical) {
				for (int x = centerX - 1; x <= centerX + 1; x++) {
					if (!isIn(x, centerY - 1) || squares[x][centerY - 1] != '0') {
						return false;
					}
				}
				return true;
			} else {
				for (int y = centerY - 1; y <= centerY + 1; y++) {
					if (!isIn(centerX, y - 1) || squares[centerX][y - 1] != '0') {
						return false;
					}
				}
				return true;
			}
		}
		
		private boolean canRight() {
			if (isVertical) {
				for (int x = centerX - 1; x <= centerX + 1; x++) {
					if (!isIn(x, centerY + 1) || squares[x][centerY + 1] != '0') {
						return false;
					}
				}
				return true;
			} else {
				for (int y = centerY - 1; y <= centerY + 1; y++) {
					if (!isIn(centerX, y + 1) || squares[centerX][y + 1] != '0') {
						return false;
					}
				}
				return true;
			}
		}
		
		public static Train newInstance(List<int[]> positions) {
			int centerX = 0;
			int centerY = 0;
			for (int i = 0; i < 3; i++) {
				centerX += positions.get(i)[0];
				centerY += positions.get(i)[1];
			}
			centerX /= 3;
			centerY /= 3;
			
			boolean isVertical = positions.get(0)[0] != centerX;
			return new Train(centerX, centerY, isVertical, 0);
		}

		@Override
		public String toString() {
			return "Train [centerX=" + centerX + ", centerY=" + centerY + ", count=" + count + ", isVertical="
					+ isVertical + "]";
		}
		
		
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < num && y < num;
	}
}