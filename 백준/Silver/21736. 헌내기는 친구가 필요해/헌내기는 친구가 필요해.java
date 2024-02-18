import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    private static int col, row;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        char[][] squares = new char[col][row];
        int startX = 0;
        int startY = 0;
        for (int i = 0; i < col; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < row; j++) {
                squares[i][j] = chars[j];
                if (squares[i][j] == 'I') {
                    startX = i;
                    startY = j;
                }
            }
        }

        int count = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        squares[startX][startY] = 'V';
        while (!queue.isEmpty()) {
            int[] pop = queue.poll();

            for (int[] ints : direction) {
                int newX = pop[0] + ints[0];
                int newY = pop[1] + ints[1];

                if (isIn(newX, newY)) {
                    if (squares[newX][newY] == 'O') {
                        squares[newX][newY] = 'V';
                        queue.offer(new int[]{newX, newY});
                    } else if (squares[newX][newY] == 'P') {
                        squares[newX][newY] = 'V';
                        count++;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
        }

        if (count > 0) {
            System.out.println(count);
        } else {
            System.out.println("TT");
        }

    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < col && y < row;
    }
}