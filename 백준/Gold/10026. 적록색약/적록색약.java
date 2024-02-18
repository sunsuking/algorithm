import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

class N10026Position {
    int x;
    int y;

    public N10026Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        N10026Position other = (N10026Position) obj;
        return this.x == other.x && this.y == other.y;
    }
}

public class Main {
    private static final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(br.readLine());
        char[][] squares = new char[num][];
        for (int i = 0; i < num; i++) {
            squares[i] = br.readLine().toCharArray();
        }

        boolean[][] visited = new boolean[num][num];

        int count = 0;
        char key;
        Queue<N10026Position> queue = new LinkedList<>();
        for (int x = 0; x < num; x++) {
            for (int y = 0; y < num; y++) {
                if (!visited[x][y]) {
                    key = squares[x][y];
                    queue.offer(new N10026Position(x, y));
                    visited[x][y] = true;
                    while (!queue.isEmpty()) {
                        N10026Position pop = queue.poll();
                        if (squares[pop.x][pop.y] == 'R') {
                            squares[pop.x][pop.y] = 'G';
                        }
                        for (int[] ints : direction) {
                            int newX = pop.x + ints[0];
                            int newY = pop.y + ints[1];

                            if (isIn(newX, newY) && !visited[newX][newY] && squares[newX][newY] == key) {

                                visited[newX][newY] = true;
                                queue.offer(new N10026Position(newX, newY));
                            }
                        }
                    }
                    count++;
                }
            }
        }
        int newCount = 0;
        visited = new boolean[num][num];
        queue = new LinkedList<>();
        for (int x = 0; x < num; x++) {
            for (int y = 0; y < num; y++) {
                if (!visited[x][y]) {
                    key = squares[x][y];
                    queue.offer(new N10026Position(x, y));
                    visited[x][y] = true;
                    while (!queue.isEmpty()) {
                        N10026Position pop = queue.poll();
                        for (int[] ints : direction) {
                            int newX = pop.x + ints[0];
                            int newY = pop.y + ints[1];

                            if (isIn(newX, newY) && !visited[newX][newY] && squares[newX][newY] == key) {

                                visited[newX][newY] = true;
                                queue.offer(new N10026Position(newX, newY));
                            }
                        }
                    }
                    newCount++;
                }
            }
        }
//        for (int i = 0; i < num; i++) System.out.println(Arrays.toString(squares[i]));

        System.out.println(count + " " + newCount);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < num && y < num;
    }
}