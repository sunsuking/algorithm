import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int row, col;
    private static int[][] squares;
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        squares = new int[row][col];
        int[][] numSquares = new int[row][col];
        List<N16946Position> positions = new ArrayList<>();
        List<N16946Position> walls = new ArrayList<>();
        for (int x = 0; x < row; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < col; y++) {
                squares[x][y] = chars[y] - '0';
                if (squares[x][y] == 0) positions.add(new N16946Position(x, y));
                else {
                    walls.add(new N16946Position(x, y));
                    squares[x][y] = -1;
                }
            }
        }

        int num = 1;
        boolean[][] visited = new boolean[row][col];
        for (N16946Position position : positions) {
            if (visited[position.x][position.y]) continue;
            int count = 1;
            List<int[]> nums = new ArrayList<>();
            Queue<int[]> queue = new LinkedList<>();
            visited[position.x][position.y] = true;
            queue.offer(new int[]{position.x, position.y});
            nums.add(new int[]{position.x, position.y});
            while (!queue.isEmpty()) {
                int[] pop = queue.poll();

                for (int[] ints : direction) {
                    int newX = pop[0] + ints[0];
                    int newY = pop[1] + ints[1];

                    if (isIn(newX, newY) && !visited[newX][newY] && squares[newX][newY] == 0) {
                        count++;
                        visited[newX][newY] = true;
                        nums.add(new int[]{newX, newY});
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            for (int[] p : nums) {
                squares[p[0]][p[1]] = count;
                numSquares[p[0]][p[1]] = num;
            }
            num++;
        }

//        print(squares);
//        System.out.println("==========");
//        print(numSquares);

        int[][] newSquares = new int[row][col];
        for (N16946Position position : walls) {
            int count = 1;
            List<Integer> nums = new ArrayList<>();
            for (int[] ints : direction) {
                int newX = position.x + ints[0];
                int newY = position.y + ints[1];

                if (isIn(newX, newY) && squares[newX][newY] > 0 && !nums.contains(numSquares[newX][newY])) {
                    count += squares[newX][newY];
                    nums.add(numSquares[newX][newY]);
                }
            }
            newSquares[position.x][position.y] = count % 10;
        }

        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) builder.append(newSquares[x][y]);
            builder.append("\n");
        }
        System.out.print(builder);

    }

    private static void print(int[][] squares) {
        for (int i = 0; i < row; i++) System.out.println(Arrays.toString(squares[i]));
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }

    static class N16946Position {
        int x;
        int y;

        public N16946Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}