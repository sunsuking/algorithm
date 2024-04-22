import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;


/**
 * 통나무 옮기기 G2
 *  단순 구현 
 *
 */

public class Main {
	
	
	static int N;
	static int[] moveX = {-1, 1, 0, 0};
	static int[] moveY = {0, 0, -1, 1};
	static int[][] board;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		
		board = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < N; j++) {
				char tmp = input.charAt(j);
				if (tmp == 'B') {
					board[i][j] = -2;
				}else if (tmp == 'E') {
					board[i][j] = -1;
				} else {
					board[i][j] = tmp - '0';
				}
			}
		}

		Log log = null;
		for (int i = 0 ; i < N; i++) {
			for (int j = 0 ; j < N; j++) {
				
				
				if (isIn(i,j-1) && isIn(i,j+1) &&  board[i][j-1] == -2 && board[i][j] == -2 && board[i][j+1] == -2) {
					log = new Log(i, j, 0, 0);
					board[i][j] = 0;
					board[i][j-1] = 0;
					board[i][j+1] = 0;
					
					break;
				}
				
				if (isIn(i-1,j) && isIn(i+1,j) &&  board[i-1][j] == -2 && board[i][j] == -2 && board[i+1][j] == -2) {
					log = new Log(i, j, 1, 0);
					board[i+1][j] = 0;
					board[i-1][j] = 0;
					board[i][j] = 0;
					break;
				}
			}
		}
		
		
		
		boolean[][][] visited = new boolean[2][N][N];
		Queue<Log> q = new LinkedList<>();
		
		q.add(log);
		visited[log.isUp][log.y][log.x] = true;
		
		
		while(!q.isEmpty()) {
			
			
			Log cur = q.poll();
			
			
			if (cur.isUp == 1) {
				
				if (board[cur.y][cur.x]== -1
						&& board[cur.y-1][cur.x]== -1
						&& board[cur.y+1][cur.x]== -1) {
					System.out.println(cur.cnt);
					System.exit(0);
				}
			}
			
			
			
			
			if (cur.isUp == 0) {
				
				if (board[cur.y][cur.x]== -1
						&& board[cur.y][cur.x-1]== -1
						&& board[cur.y][cur.x+1]== -1) {
					System.out.println(cur.cnt);
					System.exit(0);
				}
			}
			
			
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + moveY[i];
				int nx = cur.x + moveX[i];
				
				
				if (cur.isUp == 1) {
					
					if (!isIn(ny + 1, nx) || !isIn(ny-1, nx)) continue;
					if (board[ny-1][nx] == 1 || board[ny][nx] == 1 || board[ny+1][nx] == 1) continue;
					if (visited[1][ny][nx]) continue;
					
					q.add(new Log(ny,nx,1,cur.cnt+1));
					visited[1][ny][nx] = true;
					
				}else if(cur.isUp == 0){
					
					if (!isIn(ny, nx + 1) || !isIn(ny, nx-1)) continue;
					if (board[ny][nx-1] == 1 || board[ny][nx] == 1 || board[ny][nx+1] == 1) continue;
					if (visited[0][ny][nx]) continue;
					
					q.add(new Log(ny,nx,0,cur.cnt+1));
					visited[0][ny][nx] = true;
				}
			}
			
			
			int next = (cur.isUp + 1) % 2;
			if (cur.turnPos() && !visited[next][cur.y][cur.x]) {

				q.add(new Log(cur.y, cur.x, next, cur.cnt+1));
				visited[next][cur.y][cur.x] = true;
			}
		}
		
		
		System.out.println(0);
		
	}
	
	
	
	
	
	
	static class Log{
		int y, x, isUp, cnt;

		
		
		
		
		
		public Log(int y, int x, int isUp, int cnt) {
			this.y = y;
			this.x = x;
			this.isUp = isUp;
			this.cnt = cnt;
		}



		public boolean turnPos() {
			
			for (int i = y-1; y + 1 >= i; i++) {
				for (int j = x - 1; x + 1 >= j; j++) {
					if (!isIn(i, j) || board[i][j] == 1) return false;
				}
			}
			return true;
		}



		@Override
		public String toString() {
			return "Log [y=" + y + ", x=" + x + ", isUp=" + isUp + ", cnt=" + cnt + "]";
		}



		
		
	}
	
	
	static boolean isIn(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}
}