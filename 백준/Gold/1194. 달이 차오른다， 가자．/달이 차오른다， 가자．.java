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

        char[][] squares = new char[row][col];
        int startX = 0;
        int startY = 0;
        for (int x = 0; x < row; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < col; y++) {
                if (chars[y] == '0') {
                    startX = x;
                    startY = y;
                }
                squares[x][y] = chars[y];
            }
        }

        squares[startX][startY] = '.';
        int[][] visited = new int[row][col];
        Queue<N1194Node> queue = new LinkedList<>();
        visited[startX][startY] = 1;
        queue.offer(new N1194Node(startX, startY, (short) 1, 0));
        while (!queue.isEmpty()) {
            N1194Node pop = queue.poll();
//            System.out.println(pop);

//            for (int i = 0; i < row; i++) System.out.println(Arrays.toString(visited[i]));
//            System.out.println("===========");

            for (int[] ints : direction) {
                int newX = pop.x + ints[0];
                int newY = pop.y + ints[1];

                if (isIn(newX, newY)) {
                    if (squares[newX][newY] == '1') {
                        System.out.println(pop.count + 1);
                        System.exit(0);
                    }
                    if ((visited[newX][newY] & pop.key) != pop.key) {
                        if (squares[newX][newY] == '.') {
                            visited[newX][newY] = pop.key;
                            queue.offer(new N1194Node(newX, newY, pop.key, pop.count + 1));
                        } else if ('a' <= squares[newX][newY] && squares[newX][newY] <= 'f') {
                            int newKey = (1 << ((squares[newX][newY] - 'a' + 1)) | pop.key);
                            visited[newX][newY] = newKey;
                            queue.offer(new N1194Node(newX, newY, newKey, pop.count + 1));
                        } else if ('A' <= squares[newX][newY] && squares[newX][newY] <= 'F') {
                            boolean hasKey = (1 << ((squares[newX][newY] - 'A' + 1)) & pop.key) != 0;
                            if (hasKey) {
                                visited[newX][newY] = pop.key;
                                queue.offer(new N1194Node(newX, newY, pop.key, pop.count + 1));
                            }
                        }
                    }

                }
            }
        }
        System.out.println(-1);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    static class N1194Node {
        int x;
        int y;
        int key;
        int count;

        public N1194Node(int x, int y, int key, int count) {
            this.x = x;
            this.y = y;
            this.key = key;
            this.count = count;
        }
    }
}