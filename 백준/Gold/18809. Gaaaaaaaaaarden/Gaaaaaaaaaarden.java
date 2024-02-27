import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M, G, R, max;
    private static int[] array, rArray;
    private static int[][] squares;
    private static List<int[]> available;
    private static final int[][] direction = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        squares = new int[N][M];
        available = new ArrayList<>();
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
                if (squares[x][y] == 2) {
                    available.add(new int[]{x, y});
                }
            }
        }

        array = new int[R + G];
        rArray = new int[R];
        max = Integer.MIN_VALUE;

        combination(0, 0);
        System.out.println(max);
    }

    private static void newCombination(int index, int start) {
        if (index == R) {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] v = new boolean[N][M];
            int[][] newSquares = copy(squares);

            List<Integer> nums = new ArrayList<>();
            for (int n : array) {
                nums.add(n);
            }

            for (int r : rArray) {
                int[] newPosition = new int[3];
                newPosition[0] = available.get(r)[0];
                newPosition[1] = available.get(r)[1];
                newSquares[newPosition[0]][newPosition[1]] = 10;
                v[newPosition[0]][newPosition[1]] = true;
                newPosition[2] = 0;
                nums.remove(new Integer(r));
                queue.offer(newPosition);
            }

            for (int g : nums) {
                int[] newPosition = new int[3];
                newPosition[0] = available.get(g)[0];
                newPosition[1] = available.get(g)[1];
                newSquares[newPosition[0]][newPosition[1]] = 20;
                v[newPosition[0]][newPosition[1]] = true;
                newPosition[2] = 1;
                queue.offer(newPosition);
            }
//            System.out.println(Arrays.toString(rArray));
//            System.out.println(nums);
//            System.out.println("=============");

            Queue<int[]> toBeZero = new LinkedList<>();
            while (!queue.isEmpty()) {

                while (!toBeZero.isEmpty()) {
                    int[] pop = toBeZero.poll();
                    if (newSquares[pop[0]][pop[1]] == 7) continue;
                    newSquares[pop[0]][pop[1]] = 4;
                }

                int size = queue.size();
                for (int __x = 0; __x < size; __x++) {
                    int[] pop = queue.poll();

                    if (newSquares[pop[0]][pop[1]] == 7) continue;

                    for (int[] ints : direction) {
                        int newX = pop[0] + ints[0];
                        int newY = pop[1] + ints[1];


                        if (isIn(newX, newY)) {

                            if ((pop[2] == 0 && newSquares[newX][newY] == 5) || (pop[2] == 1 && newSquares[newX][newY] == 6)) {
                                newSquares[newX][newY] = 7;
                                continue;
                            }

                            if (!v[newX][newY] && (newSquares[newX][newY] == 1 || newSquares[newX][newY] == 2)) {
                                newSquares[newX][newY] = pop[2] == 0 ? 6 : 5;
                                queue.offer(new int[]{newX, newY, pop[2]});
                                toBeZero.offer(new int[]{newX, newY});
                            }
                            v[newX][newY] = true;
                        }
                    }
                }
            }

            int count = 0;
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    if (newSquares[x][y] == 7) count++;
                }
            }
            max = Math.max(max, count);
            return;
        }
        for (int i = start; i < array.length; i++) {
            rArray[index] = array[i];
            newCombination(index + 1, i + 1);
        }
    }

    private static void combination(int index, int start) {
        if (index == G + R) {
            newCombination(0, 0);
            return;
        }
        for (int i = start; i < available.size(); i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    private static int[][] copy(int[][] squares) {
        int[][] newSquares = new int[N][M];
        for (int i = 0; i < N; i++) newSquares[i] = squares[i].clone();
        return newSquares;
    }
}