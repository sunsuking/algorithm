import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int col, row;
    private static int[][] squares;
    private static int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        squares = new int[col][row];
        for (int i = 0; i < col; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < row; j++) {
                squares[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        int size = 0;
        while (!isEmpty()) {
            size = 0;
            addAir();
            for (int x = 0; x < col; x++) {
                for (int y = 0; y < row; y++) {
                    if (squares[x][y] != 1) continue;
                    size++;
                    for (int[] ints : direction) {
                        int newX = x + ints[0];
                        int newY = y + ints[1];
                        if (isIn(newX, newY) && squares[newX][newY] == 2) {
                            squares[x][y] = 0;
                        };
                    }
                }
            }
            count++;
        }
        System.out.println(count);
        System.out.println(size);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < col && y < row;
    }

    private static boolean isEmpty() {
        for (int i = 0; i < col; i++) {
            for (int y = 0; y < row; y++) {
                if (squares[i][y] == 1) return false;
            }
        }
        return true;
    }

    private static void addAir() {
        boolean[][] visited = new boolean[col][row];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        visited[0][0] = true;
        squares[0][0] = 2;
        while (!queue.isEmpty()) {
            int[] pop = queue.poll();

            for (int[] ints : direction) {
                int newX = pop[0] + ints[0];
                int newY = pop[1] + ints[1];
                if (isIn(newX, newY) && squares[newX][newY] != 1 && !visited[newX][newY]) {
                    squares[newX][newY] = 2;
                    visited[newX][newY] = true;
                    queue.offer(new int[]{newX, newY});
                }
            }
        }
    }
}