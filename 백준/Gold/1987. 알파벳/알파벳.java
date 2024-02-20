import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class N1987Position {
    int x;
    int y;
    int count;
    char[] chars;

    public N1987Position(int x, int y, int count, char[] chars) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.chars = chars;
    }
}

public class Main {
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int col, row, max;
    private static char[][] squares;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        max = 1;
        squares = new char[col][];
        for (int i = 0; i < col; i++) squares[i] = br.readLine().toCharArray();
        dfs(0, 0, new char[] {squares[0][0]}, 1);
        System.out.println(max);
    }

    private static void dfs(int x, int y, char[] chars, int count) {
        for (int[] ints : direction) {
            int newX = x + ints[0];
            int newY = y + ints[1];
            if (isIn(newX, newY)) {
                if (contains(chars, squares[newX][newY])) {
                    if (count == 26) {
                        System.out.println(26);
                        System.exit(0);
                    }
                    max = Math.max(max, count);
                } else {
                    char[] newChars = Arrays.copyOf(chars, count + 1);
                    newChars[count] = squares[newX][newY];
                    dfs(newX, newY, newChars, count + 1);
                }
            }
        }
    }

    private static boolean contains(char[] chars, char c) {
        for(char ch: chars) if (ch == c) return true;
        return false;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < col && y < row;
    }
}