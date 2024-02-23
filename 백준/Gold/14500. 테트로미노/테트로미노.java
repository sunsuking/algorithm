import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int col, row;
    private static final int[][][][] direction = {
            {{{0, 0}, {0, 1}, {0, 2}, {0, 3}}, {{0, 0}, {1, 0}, {2, 0}, {3, 0}}},
            {{{0, 0}, {0, 1}, {1, 0}, {1, 1}}},
            {{{0, 0}, {1, 0}, {2, 0}, {2, 1}}, {{0, 0}, {0, 1}, {0, 2}, {-1, 2}}, {{0, 0}, {0, 1}, {1, 1}, {2, 1}}, {{0, 0}, {0, 1}, {0, 2}, {1, 0}}, {{0, 0}, {1, 0}, {2, 0}, {2, -1}}, {{0, 0}, {1, 0}, {1, 1}, {1, 2}}, {{0, 0}, {0, 1}, {1, 0}, {2, 0}}, {{0, 0}, {0, 1}, {0, 2}, {1, 2}}},
            {{{0, 0}, {1, 0}, {1, 1}, {2, 1}}, {{0, 0}, {1, 0}, {1, -1}, {2, -1}}, {{0, 0}, {0, 1}, {1, 0}, {1, -1}}, {{0, 0}, {0, 1}, {1, 1}, {1, 2}}},
            {{{0, 0}, {0, 1}, {0, 2}, {1, 1}}, {{0, 0}, {1, 0}, {1, 1}, {2, 0}}, {{0, 0}, {1, 0}, {2, 0}, {1, -1}}, {{0, 0}, {1, 0}, {1, -1}, {1, 1}}},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        int[][] squares = new int[col][row];
        for (int x = 0; x < col; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < row; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int max = getMax(squares);

        System.out.println(max);
    }

    private static int getMax(int[][] squares) {
        int max = Integer.MIN_VALUE;

        for (int x = 0; x < col; x++) {
            for (int y = 0; y < row; y++) {
                for (int[][][] tetromino : direction) {
                    main:
                    for (int[][] available : tetromino) {
                        int sum = 0;
                        for (int[] ints : available) {
                            int newX = x + ints[0];
                            int newY = y + ints[1];

                            if (!isIn(newX, newY)) continue main;
                            sum += squares[newX][newY];
                        }
                        max = Math.max(sum, max);
                    }
                }
            }
        }
        return max;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < col && y < row;
    }
}