import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;


public class Solution {
    private static int[][] direction = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            char[][] squares = new char[n][];
            for (int i = 0; i < n; i++) squares[i] = br.readLine().toCharArray();
            int sum = 0;
            boolean[][] visited = new boolean[n][n];
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (squares[x][y] == '.') {
                        visited[x][y] = true;
                        Queue<int[]> queue = new LinkedList<>();
                        queue.offer(new int[]{x, y});
                        boolean isZero = false;
                        while (!queue.isEmpty()) {
                            int[] pop = queue.poll();
                            int count = 0;
                            for (int[] ints : direction) {
                                int newX = pop[0] + ints[0];
                                int newY = pop[1] + ints[1];
                                if (isIn(newX, newY, n) && squares[newX][newY] == '*') count++;
                            }
                            if (count == 0) {
                                isZero = true;
                                squares[x][y] = 'X';
                                for (int[] ints : direction) {
                                    int newX = pop[0] + ints[0];
                                    int newY = pop[1] + ints[1];
                                    if (isIn(newX, newY, n) && squares[newX][newY] == '.') {
                                        squares[newX][newY] = 'X';
                                        visited[newX][newY] = true;
                                        queue.offer(new int[]{newX, newY});
                                    }
                                }
                            }
                        }
//                        for (int i = 0; i < n; i++) System.out.println(Arrays.toString(squares[i]));
                        if (isZero) sum++;
                    }
                }
            }
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    if (squares[x][y] == '.') sum++;
                }
            }

//            System.exit(0);

            builder.append("#").append(testCase).append(" ").append(sum).append("\n");
        }
        System.out.print(builder);
    }

    private static boolean isOk(char[][] squares) {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares.length; j++) {
                if (squares[i][j] == '.') return false;
            }
        }
        return true;
    }

    private static boolean isIn(int x, int y, int n) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    private static char[][] copy(char[][] squares) {
        char[][] newSquares = new char[squares.length][];
        for (int i = 0; i < squares.length; i++) newSquares[i] = squares[i].clone();
        return newSquares;
    }
}
