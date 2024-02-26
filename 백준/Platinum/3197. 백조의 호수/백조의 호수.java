import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int R, C;
	private final static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		char[][] squares = new char[R][C];
				
		List<int[]> ices = new ArrayList<>(); 
		int[][] humans = new int[2][2];
		int humanIndex = 0;
		
		for (int x = 0; x < R; x++) {
			char[] chars = br.readLine().toCharArray();
			for (int y = 0; y < C; y++) {
				squares[x][y] = chars[y];
				
				if (squares[x][y] == '.') {
					ices.add(new int[] {x, y});
				} else if (squares[x][y] == 'L') {
					ices.add(new int[] {x, y});
					humans[humanIndex][0] = x;
					humans[humanIndex++][1] = y;
				}
			}
		}
		
		int day = 0;
		
		int startX = humans[0][0];
		int startY = humans[0][1];
		int endX = humans[1][0];
		int endY = humans[1][1];
		squares[startX][startY] = 'E';
		squares[endX][endY] = 'S';
		
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {startX, startY, 0});
		queue.add(new int[] {endX, endY, 1});
		Queue<int[]> nextQueue = new LinkedList<>();
		
		while(true) {
			boolean[][] visited = new boolean[R][C];

//			for (int i = 0; i < R; i++) System.out.println(Arrays.toString(squares[i]));
//			System.out.println("======================");

			while(!queue.isEmpty()) {
				int[] pop = queue.poll();
				for (int[] ints : direction) {
					int newX = pop[0] + ints[0];
					int newY = pop[1] + ints[1];
					
					if (isIn(newX, newY)) {
						if ((squares[pop[0]][pop[1]] == 'E' && squares[newX][newY] == 'S') || (squares[pop[0]][pop[1]] == 'S' && squares[newX][newY] == 'E') ) {
							System.out.println(day);
							System.exit(0);
						}

						if (!visited[newX][newY]) {
							if (pop[2] == 0) {
								if (squares[newX][newY] == '.') {
									squares[newX][newY] = 'E';
									visited[newX][newY] = true;
									queue.offer(new int[] {newX, newY, pop[2]});
								} else if (squares[newX][newY] == 'X') {
									visited[newX][newY] = true;
									nextQueue.offer(new int[] {pop[0], pop[1], pop[2]});
								}
							} else {
								if (squares[newX][newY] == '.') {
									squares[newX][newY] = 'S';
									visited[newX][newY] = true;
									queue.offer(new int[] {newX, newY, pop[2]});
								} else if (squares[newX][newY] == 'X') {
									visited[newX][newY] = true;
									nextQueue.offer(new int[] {pop[0], pop[1], pop[2]});
								}
							}
						}
					}
				}
			}
			
			if (nextQueue.isEmpty()) {
				System.out.println(day);
				System.exit(0);
			}
			List<int[]> newIces = new ArrayList<>();
			for (int[] ice : ices) {
				for (int[] ints : direction) {
					int newX = ice[0] + ints[0];
					int newY = ice[1] + ints[1];
					
					if (isIn(newX, newY) && squares[newX][newY] == 'X') {
						squares[newX][newY] = '.';
						newIces.add(new int[] {newX, newY});
					}
				}
			}
			day++;
			ices = newIces;
			while(!nextQueue.isEmpty()) queue.offer(nextQueue.poll());
		}
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < R && y < C;
	}
}