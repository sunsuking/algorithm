import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean[][] squares = new boolean[100][100];

        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            for (int a = x; a < x + 10; a++) {
                for (int b = y; b < y + 10; b++) {
                    squares[a][b] = true;
                }
            }
        }

        int sum = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (!squares[x][y]) continue;

                int count = 0;
                for (int[] ints : direction) {
                    int newX = x + ints[0];
                    int newY = y + ints[1];

                    if (isIn(newX, newY) && squares[newX][newY]) count++;
                }

                if (count == 3) {
                    sum += 1;
                } else if (count == 2) {
                    sum += 2;
                }
            }
        }
        System.out.println(sum);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < 100 && y < 100;
    }
}