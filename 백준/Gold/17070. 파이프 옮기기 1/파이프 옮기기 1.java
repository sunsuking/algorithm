import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[][][][] direction = {
            {{{0, 0}, {0, 1}}, {}, {{0, 0}, {0, 1}, {1, 0}, {1, 1}}},
            {{}, {{0, 0}, {1, 0}}, {{0, 0}, {0, 1}, {1, 0}, {1, 1}}},
            {{{0, 0}, {0, 1}}, {{0, 0}, {1, 0}}, {{0, 0}, {0, 1}, {1, 0}, {1, 1}}},
    };
    private static int count, num;
    private static int[][] squares;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());
        squares = new int[num][num];

        StringTokenizer st;
        for (int x = 0; x < num; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < num; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        squares[0][0] = 1;
        dfs(0, 1, 0);
        System.out.println(count);
    }

    private static void dfs(int x, int y, int position) {
        if (x == num - 1 && y == num - 1) {
            count++;
            return;
        }

        for (int index = 0; index < direction[position].length; index++) {
            int[][] delta = direction[position][index];
            if (delta.length == 0) continue;
            boolean can = true;
            for (int[] ints : delta) {
                int newX = x + ints[0];
                int newY = y + ints[1];
                if (!(isIn(newX, newY) && squares[newX][newY] == 0)) {
                    can = false;
                    break;
                }
            }

            if (can) {
                int newX = 0;
                int newY = 0;
                for (int d = 0; d < delta.length; d++) {
                    int[] ints = delta[d];
                    newX = x + ints[0];
                    newY = y + ints[1];

                    if (d == delta.length - 1) continue;
                    squares[newX][newY] = 1;
                }
                dfs(newX, newY, index);
                for (int d = 0; d < delta.length - 1; d++) {
                    int[] ints = delta[d];
                    newX = x + ints[0];
                    newY = y + ints[1];

                    if (d == delta.length - 1) continue;
                    squares[newX][newY] = 0;
                }
            }
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < num && y < num;
    }
}