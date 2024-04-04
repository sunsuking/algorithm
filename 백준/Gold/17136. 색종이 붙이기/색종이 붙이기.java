import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int min;
	private static int[][] squares;
	private static int[] paper = {0, 5, 5, 5, 5, 5};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        squares = new int[10][10];
        for (int x = 0; x < 10; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < 10; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        min = Integer.MAX_VALUE;
        
        dfs(0, 0, 0);
        
        if (min == Integer.MAX_VALUE) {
        	min = -1;
        }
    	System.out.println(min);
    }
    
    private static void dfs(int x, int y, int count) {
    	if (x >= 9 && y > 9) {
    		min = Math.min(min, count);
    		return;
    	}
    	
    	if (min <= count) return;
    	
    	if (y > 9) {
    		dfs(x + 1, 0, count);
    		return;
    	}
    	
    	if (squares[x][y] == 1) {
    		for (int i = 5; i >= 1; i--) {
    			if (paper[i] > 0 && canAttach(x, y, i)) {
    				attach(x, y, i, 0);
    				paper[i]--;
    				dfs(x, y + 1, count + 1);
    				attach(x, y, i, 1);
    				paper[i]++;
    			}
    		}
    	} else {
    		dfs(x, y + 1, count);
    	}
    }
    
    private static void attach(int x, int y, int size, int state) {
    	for (int dx = 0; dx < size; dx++) {
    		for (int dy = 0; dy < size; dy++) {
    			squares[x + dx][y + dy] = state;
    		}
    	}
    }
    
    private static boolean canAttach(int x, int y, int size) {
    	for (int dx = 0; dx < size; dx++) {
    		for (int dy = 0; dy < size; dy++) {
    			 int newX = x + dx;
    			 int newY = y + dy;
    			 if (!isIn(newX, newY) || squares[newX][newY] != 1) return false;
    		}
    	}
    	return true;
    }
    
    private static boolean isOk(boolean[][] visited) {
    	for (int x = 0; x < 10; x++) {
    		for (int y = 0; y < 10; y++) {
    			if (!visited[x][y]) return false;
    		}
    	}
    	return true;
    }
    
    private static void visit(int x, int y, int size, boolean[][] visited, boolean visit) {
    	for (int dx = 0; dx <= size; dx++) {
    		for (int dy = 0; dy <= size; dy++) {
    			visited[x + dx][y + dy] = visit;
    		}
    	}
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < 10 && y < 10;
    }
}