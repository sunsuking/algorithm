import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][][] squares;
    private static int row, col, box;
    private static final int[][] direction = {{0, 1, 0}, {0, -1, 0}, {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        box = Integer.parseInt(st.nextToken());
        squares = new int[box][col][row];
        Queue<int[]> queue = new LinkedList<>();
        for (int x = 0; x < col * box; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < row; y++) {
                squares[x / col][x % col][y] = Integer.parseInt(st.nextToken());
                if (squares[x / col][x % col][y] == 1) {
                    queue.offer(new int[]{x % col, y, x / col});
                }
            }
        }

        int day = 0;
        if (isFinish()) {
            System.out.println(day);
            System.exit(0);
        }

        while (!queue.isEmpty()) {
            if (isFinish()) break;
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] pop = queue.poll();

                for (int[] ints : direction) {
                    int newX = pop[0] + ints[0];
                    int newY = pop[1] + ints[1];
                    int newZ = pop[2] + ints[2];

                    if (isIn(newX, newY, newZ) && squares[newZ][newX][newY] == 0) {
                        squares[newZ][newX][newY] = 1;
                        queue.offer(new int[]{newX, newY, newZ});
                    }
                }

            }
            day++;
        }

        if (isFinish()) {
            System.out.println(day);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean isIn(int x, int y, int z) {
        return z >= 0 && x >= 0 && y >= 0 && x < col && y < row && z < box;
    }

    private static boolean isFinish() {
        for (int x = 0; x < col * box; x++) {
            for (int y = 0; y < row; y++) {
                if (squares[x / col][x % col][y] == 0) return false;
            }
        }
        return true;
    }
}