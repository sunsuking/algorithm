import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class N15683Position {
    int x;
    int y;
    int num;

    public N15683Position(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }
}

public class Main {
    private static final int[][][][] direction = {
            {{{-1, 0}}, {{1, 0}}, {{0, 1}}, {{0, -1}}},
            {{{-1, 0}, {1, 0}}, {{0, 1}, {0, -1}}},
            {{{-1, 0}, {0, 1}}, {{0, 1}, {1, 0}}, {{1, 0}, {0, -1}}, {{0, -1}, {-1, 0}}},
            {{{0, -1}, {-1, 0}, {0, 1}}, {{-1, 0}, {0, 1}, {1, 0}}, {{0, 1}, {1, 0}, {0, -1}}, {{1, 0}, {0, -1}, {-1, 0}}},
            {{{0, 1}, {0, -1}, {1, 0}, {-1, 0}}}
    };
    private static List<N15683Position> array;
    private static int N, M, min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] squares = new int[N][M];

        array = new ArrayList<>();
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (1 <= n && n <= 5) {
                    array.add(new N15683Position(i, j, n - 1));
                }
                squares[i][j] = n;
            }
        }
        if (array.isEmpty()) {
            int min = countZero(squares);
            System.out.println(min);
            System.exit(0);
        }
        dfs(0, squares);
        System.out.println(min);
    }

    private static void dfs(int index, int[][] squares) {
        N15683Position position = array.get(index);
        int[][][] action = direction[position.num];
        for (int[][] ints : action) {
//            for (int[] p : ints) System.out.println(Arrays.toString(p));
//            System.out.println("=================");
            int[][] newSquares = clone(squares);
            for (int[] value : ints) {
                int newX = position.x + value[0];
                int newY = position.y + value[1];
                while (isIn(newX, newY) && newSquares[newX][newY] != 6) {
                    if (newSquares[newX][newY] == 0) {
                        newSquares[newX][newY] = -1;
                    }
                    newX += value[0];
                    newY += value[1];
                }
            }
            if (index == array.size() - 1) {
//                System.out.println(array.size());
//                for (int i = 0; i < N; i++) System.out.println(Arrays.toString(newSquares[i]));
//                System.out.println("=================");
                min = Math.min(min, countZero(newSquares));
                if (min == 0) {
                    System.out.println(0);
                    System.exit(0);
                }
                continue;
            }
            dfs(index + 1, newSquares);
        }
    }

    private static int countZero(int[][] sqaures) {
        int count = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (sqaures[x][y] == 0) count++;
            }
        }
        return count;
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    private static int[][] clone(int[][] squares) {
        int[][] newSquares = new int[squares.length][];
        for (int i = 0; i < squares.length; i++) newSquares[i] = squares[i].clone();
        return newSquares;
    }
}