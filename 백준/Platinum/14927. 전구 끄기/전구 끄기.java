import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static final int[][] direction = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        boolean[][] squares = new boolean[N][N];
        StringTokenizer st;
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                squares[x][y] = st.nextToken().equals("1");
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 1 << N; i++) {
            int count = 0;
            boolean[][] newSquares = clone(squares);

            for (int n = 0; n < N; n++) {
                if ((i & (1 << n)) == 1 << n) {
                    count++;
                    for (int[] ints : direction) {
                        int newX = ints[0];
                        int newY = n + ints[1];

                        if (isIn(newX, newY)) {
                            newSquares[newX][newY] = !newSquares[newX][newY];
                        }
                    }
                }
            }

            for (int x = 1; x < N; x++) {
                for (int y = 0; y < N; y++) {
                    if (newSquares[x - 1][y]) {
                        for (int[] ints : direction) {
                            int newX = x + ints[0];
                            int newY = y + ints[1];

                            if (isIn(newX, newY)) {
                                newSquares[newX][newY] = !newSquares[newX][newY];
                            }
                        }
                        count++;
                    }
                }
            }

            if (isAllOut(newSquares)) {
                min = Math.min(min, count);
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }
    private static boolean[][] clone(boolean[][] squares) {
        boolean[][] newSquares = new boolean[N][];
        for (int i = 0; i < N; i++) newSquares[i] = squares[i].clone();
        return newSquares;
    }

    private static boolean isAllOut(boolean[][] squares) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (squares[x][y]) return false;
            }
        }
        return true;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}