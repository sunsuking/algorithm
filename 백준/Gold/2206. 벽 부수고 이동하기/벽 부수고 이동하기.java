import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int row, col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        int[][] squares = new int[row][col];
        for (int x = 0; x < row; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < col; y++) {
                squares[x][y] = chars[y] - '0';
            }
        }

        Queue<N2206Node> queue = new LinkedList<>();
        queue.offer(new N2206Node(0, 0, 1, false));

        boolean[][] visited = new boolean[row][col];
        boolean[][] breakVisited = new boolean[row][col];
        while (!queue.isEmpty()) {
            N2206Node pop = queue.poll();

            if (pop.x == (row - 1) && pop.y == (col - 1)) {
                System.out.println(pop.count);
                System.exit(0);
            }

            for (int[] ints : direction) {
                int newX = pop.x + ints[0];
                int newY = pop.y + ints[1];

                if (isIn(newX, newY)) {
                    if (pop.isBreak && breakVisited[newX][newY]) continue;
                    if (!pop.isBreak && visited[newX][newY]) continue;

                    if (pop.isBreak) {
                        breakVisited[newX][newY] = true;
                    } else {
                        visited[newX][newY] = true;
                    }

                    if (squares[newX][newY] == 1 && !pop.isBreak) {
                        queue.offer(new N2206Node(newX, newY, pop.count + 1, true));
                    }
                    if (squares[newX][newY] == 0) {
                        queue.offer(new N2206Node(newX, newY, pop.count + 1, pop.isBreak));
                    }

                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    static class N2206Node {
        int x;
        int y;
        int count;
        boolean isBreak;

        public N2206Node(int x, int y, int count, boolean isBreak) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.isBreak = isBreak;
        }
    }
}