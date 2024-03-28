import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int col, row;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        char[][] squares = new char[row][col];
        int startX = -1;
        int startY = -1;
        List<int[]> waters = new ArrayList<>();
        for (int x = 0; x < row; x++) {
            char[] chars = br.readLine().toCharArray();
            for (int y = 0; y < col; y++) {
                squares[x][y] = chars[y];
                if (chars[y] == 'S') {
                    startX = x;
                    startY = y;
                    squares[x][y] = '.';
                } else if (chars[y] == '*') {
                    waters.add(new int[]{x, y});
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        boolean[][] visited = new boolean[row][col];

        int day = 0;
        while (true) {
            int size = queue.size();
            if (size == 0) break;

            List<int[]> newWaters = new ArrayList<>();
            for (int[] water : waters) {
                for (int[] ints : direction) {
                    int newX = water[0] + ints[0];
                    int newY = water[1] + ints[1];

                    if (isIn(newX, newY) && squares[newX][newY] == '.') {
                        squares[newX][newY] = '*';
                        newWaters.add(new int[]{newX, newY});
                    }
                }
            }

            for (int __x = 0; __x < size; __x++) {
                int[] pop = queue.poll();

                for (int[] ints : direction) {
                    int newX = pop[0] + ints[0];
                    int newY = pop[1] + ints[1];

                    if (isIn(newX, newY) && !visited[newX][newY] && squares[newX][newY] != '*' && squares[newX][newY] != 'X') {
                        if (squares[newX][newY] == 'D') {
                            System.out.println(day + 1);
                            System.exit(0);
                        }

                        visited[newX][newY] = true;
                        queue.offer(new int[] {newX, newY});
                    }
                }
            }

            waters = newWaters;
            day++;
        }
        System.out.println("KAKTUS");
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < row && y < col;
    }
}