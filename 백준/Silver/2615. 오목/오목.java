import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 자바 문제 5번 풀이
 * 
 * @author 광주 5반 민준수
 */
public class Main {
	private static int sColor = 0;
	private static int[][] squares;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		squares = new int[19][19];
		StringTokenizer st;
		for(int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				squares[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean isWin = false;
		
		loop:
		for (int x = 0; x < 19; x++) {
			for (int y = 0; y < 19; y++) {
				int color = squares[x][y];
				if (color == 0) continue;
				int startX = x;
				int startY = y;
				sColor = color;
				if (omok(x, y, 1, 0)) {
					isWin = true;
					builder.append(color + "\n");
					builder.append((startX + 1) + " " + (startY + 1));
					break loop;
				}
			}
		}
		
		if (isWin) {
			System.out.println(builder);	
		} else {
			System.out.println("0");
		}
	}
	
	private static boolean omok(int x, int y, int count, int type) {
		if (x > 18 || y > 18 || x < 0 || y < 0 || squares[x][y] != sColor) return false;
		if (squares[x][y] == sColor && count == 5) {
			if (type == 0 && ((x + 1 <= 18 && squares[x+1][y] == sColor) || (x - 5 >= 0 && squares[x-5][y] == sColor))) return false;
			else if (type == 1 && ((y + 1 <= 18 && squares[x][y+1] == sColor) || (y - 5 >= 0 && squares[x][y-5] == sColor))) return false;
			else if (type == 2 && ((x + 1 <= 18 && y + 1 <= 18 && squares[x+1][y+1] == sColor) || (x - 5 >= 0 && y - 5 >= 0 && squares[x-5][y-5] == sColor))) return false;
			else if (type == 3 && ((x - 1 >= 0 && y + 1 <= 18 && squares[x-1][y+1] == sColor) || (x + 5 <= 18 && y - 5 >= 0 && squares[x+5][y-5] == sColor))) return false;
			return true;
		}
		else {
			if (count == 1) {
				return omok(x + 1, y, count + 1, 0) || omok(x, y + 1, count + 1, 1) || omok(x + 1, y + 1, count + 1, 2) || omok(x - 1, y + 1, count + 1, 3);	
			} else {
				if (type == 0) return omok(x + 1, y, count + 1, 0);
				else if (type == 1) return omok(x, y + 1, count + 1, 1);
				else if (type == 2) return omok(x + 1, y + 1, count + 1, 2);
				return omok(x - 1, y + 1, count + 1, 3);
			}
		}
	}
}

