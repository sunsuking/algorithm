import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final int[][] direction = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] squares = new char[10][10];
        for (int x = 0; x < 10; x++) squares[x] = br.readLine().toCharArray();

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 1024; i++) {
            int count = 0;
            char[][] newSquares = clone(squares);

            for (int n = 0; n < 10; n++) {
                if ((i & (1 << n)) == 1 << n) {
                    count++;
                    for (int[] ints : direction) {
                        int newX = ints[0];
                        int newY = n + ints[1];

                        if (isIn(newX, newY)) {
                            newSquares[newX][newY] = newSquares[newX][newY] == '#' ? 'O' : '#';
                        }
                    }
                }
            }

            for (int x = 1; x < 10; x++) {
                for (int y = 0; y < 10; y++) {
                    if (newSquares[x - 1][y] == 'O') {
                        for (int[] ints : direction) {
                            int newX = x + ints[0];
                            int newY = y + ints[1];

                            if (isIn(newX, newY)) {
                                newSquares[newX][newY] = newSquares[newX][newY] == '#' ? 'O' : '#';
                            }
                        }
                        count++;
                    }
                }
            }
            if (isAllOut(newSquares)) {
                max = Math.max(max, count);
            }
        }

        if (max == Integer.MIN_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(max);
        }
    }
    private static char[][] clone(char[][] squares) {
        char[][] newSquares = new char[10][];
        for (int i = 0; i < 10; i++) newSquares[i] = squares[i].clone();
        return newSquares;
    }

    private static boolean isAllOut(char[][] squares) {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                if (squares[x][y] == 'O') return false;
            }
        }
        return true;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < 10 && y < 10;
    }
}