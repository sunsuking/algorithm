import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken());
            int row = Integer.parseInt(st.nextToken());
            if (col == 0 && row == 0) break;
            int[][] squares = new int[row][col];
            for (int i = 0; i < row; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < col; j++) {
                    squares[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            Queue<int[]> queue = new LinkedList<>();
            int count = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (squares[i][j] == 1) {
                        queue.offer(new int[]{i, j});
                        squares[i][j] = 0;
                        while (!queue.isEmpty()) {
                            int[] pop = queue.poll();
                            for (int[] ints : direction) {
                                int newX = pop[0] + ints[0];
                                int newY = pop[1] + ints[1];
                                if (isIn(newX, newY, col, row) && squares[newX][newY] == 1) {
                                    queue.offer(new int[]{newX, newY});
                                    squares[newX][newY] = 0;
                                }
                            }
                        }
                        count++;
                    }
                }
            }
            builder.append(count).append("\n");
        }
        System.out.print(builder);
    }

    private static boolean isIn(int x, int y, int col, int row) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }
}