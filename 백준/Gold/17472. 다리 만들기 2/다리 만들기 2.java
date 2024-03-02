import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int row, col;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        int[][] squares = new int[row][col];
        for (int x = 0; x < row; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < col; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        List<List<N17472Position>> array = new ArrayList<>();

        boolean[][] visited = new boolean[row][col];
        int num = 1;
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (!visited[x][y] && squares[x][y] == 1) {
                    List<N17472Position> nodes = new ArrayList<>();
                    Queue<int[]> queue = new LinkedList<>();
                    nodes.add(new N17472Position(x, y, num));
                    squares[x][y] = num;
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                    while (!queue.isEmpty()) {
                        int[] pop = queue.poll();

                        for (int[] ints : direction) {
                            int newX = pop[0] + ints[0];
                            int newY = pop[1] + ints[1];

                            if (isIn(newX, newY) && squares[newX][newY] == 1 && !visited[newX][newY]) {
                                squares[newX][newY] = num;
                                visited[newX][newY] = true;
                                queue.offer(new int[]{newX, newY});
                                nodes.add(new N17472Position(newX, newY, num));
                            }
                        }
                    }
                    num++;
                    array.add(nodes);
                }
            }
        }

        parent = new int[num];
        for (int i = 1; i < num; i++) {
            parent[i] = i;
        }

        Queue<N17472Node> queue = new PriorityQueue<>();

        for (List<N17472Position> nodes : array) {
            for (N17472Position node : nodes) {
                for (int[] ints : direction) {
                    int distance = 0;
                    int newX = node.x + ints[0];
                    int newY = node.y + ints[1];

                    while (isIn(newX, newY) && squares[newX][newY] != node.num) {
                        if (squares[newX][newY] != 0) {
                            if (distance != 1) {
                                queue.offer(new N17472Node(node.num, squares[newX][newY], distance));
                            }
                            break;
                        }

                        distance++;
                        newX += ints[0];
                        newY += ints[1];
                    }
                }
            }
        }

        int weight = 0;
        while (!queue.isEmpty()) {
            N17472Node pop = queue.poll();
            if (merge(pop.from, pop.to)) {
                weight += pop.weight;
            }
        }
        int count = 0;
        for (int i = 1; i < num; i++) {
            if (parent[i] == i) {
                count++;
            }
        }
        if (count > 1) {
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(weight);
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merge(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);

        if (parentX == parentY) return false;
        parent[parentY] = parentX;
        return true;
    }

    private static void printSquares(int[][] squares) {
        for (int i = 0; i < row; i++) System.out.println(Arrays.toString(squares[i]));
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    static class N17472Node implements Comparable<N17472Node> {
        int from;
        int to;
        int weight;

        public N17472Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(N17472Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    static class N17472Position {
        int x;
        int y;
        int num;

        public N17472Position(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }
}