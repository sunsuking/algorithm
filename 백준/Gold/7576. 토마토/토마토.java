import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int row, col;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        int[][] squares = new int[col][row];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < col; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < row; j++) {
                squares[i][j] = Integer.parseInt(st.nextToken());
                if (squares[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
            }
        }

//        for (int[] l : squares) System.out.println(Arrays.toString(l));
        if (isOk(squares)) {
            System.out.println(0);
            System.exit(0);
        }
        int count = 0;
        while (!queue.isEmpty()) {
            if (isOk(squares)) break;
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] pop = queue.poll();
                for (int[] ints : direction) {
                    int newX = pop[0] + ints[0];
                    int newY = pop[1] + ints[1];
                    if (isIn(newX, newY) && squares[newX][newY] == 0) {
                        squares[newX][newY] = 1;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }

        if (!isOk(squares)) {
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(count);

    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < col && y < row;
    }

    private static boolean isOk(int[][] squares) {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (squares[i][j] == 0) return false;
            }
        }
        return true;
    }
}