import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int N, M, min;
    private static int[][] squares;
    private static int[] array;
    private static List<N17779Position> virus;
    private static List<int[][]> virusSquares;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        squares = new int[N][N];
        virus = new ArrayList<>();
        virusSquares = new ArrayList<>();
        min = Integer.MAX_VALUE;
        array = new int[M];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    squares[x][y] = 100;
                } else if (n == 2) {
                    virus.add(new N17779Position(x, y, 0));
                    squares[x][y] = Integer.MAX_VALUE;
                } else {
                    squares[x][y] = Integer.MAX_VALUE;
                }
            }
        }

        for (int i = 0; i < virus.size(); i++) {
            boolean[][] visited = new boolean[N][N];
            Queue<N17779Position> queue = new LinkedList<>();
            N17779Position v = virus.get(i);
            queue.offer(v);
            int[][] newSquares = clone(squares);
            newSquares[v.x][v.y] = 0;
            visited[v.x][v.y] = true;
            while (!queue.isEmpty()) {
                N17779Position pop = queue.poll();

                for (int[] ints : direction) {
                    int newX = pop.x + ints[0];
                    int newY = pop.y + ints[1];

                    if (isIn(newX, newY) && newSquares[newX][newY] == Integer.MAX_VALUE && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        newSquares[newX][newY] = pop.time + 1;
                        queue.offer(new N17779Position(newX, newY, pop.time + 1));
                    }
                }
            }
            virusSquares.add(newSquares);

//            System.out.println("=======" + i + "======");
//            for (int x = 0; x < N; x++) System.out.println(Arrays.toString(newSquares[x]));
//            System.out.println("=============");
        }

        combination(0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }

    private static int[][] clone(int[][] squares) {
        int[][] newSquares = new int[N][N];
        for (int i = 0; i < N; i++) newSquares[i] = squares[i].clone();
        return newSquares;
    }

    private static void combineSquares(int[][] squaresA, int[][] squaresB) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                squaresA[x][y] = Math.min(squaresA[x][y], squaresB[x][y]);
            }
        }
    }

    private static int minValue(int[][] squares) {
        int max = Integer.MIN_VALUE;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (squares[x][y] == 100) continue;
                max = Math.max(squares[x][y], max);
            }
        }
        return max;
    }

    private static void combination(int index, int start) {
        if (index == M) {
            List<N17779Position> inActiveVirus = new ArrayList<>();
//            System.out.println(Arrays.toString(array));
            inActiveVirus.addAll(virus);

            int[][] sumSquares = clone(virusSquares.get(array[0]));
            inActiveVirus.remove(virus.get(array[0]));

            for (int i = 1; i < index; i++) {
                combineSquares(sumSquares, clone(virusSquares.get(array[i])));
                inActiveVirus.remove(virus.get(array[i]));
            }

            for (N17779Position v : inActiveVirus) {
                sumSquares[v.x][v.y] = 0;
            }

//            for (int x = 0; x < N; x++) System.out.println(Arrays.toString(sumSquares[x]));
//            System.out.println("=============");
            min = Math.min(minValue(sumSquares), min);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static class N17779Position {
        int x;
        int y;
        int time;

        public N17779Position(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}