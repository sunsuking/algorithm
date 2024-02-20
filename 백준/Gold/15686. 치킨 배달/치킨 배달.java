import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static int[] array;
    private static int[][] squares;
    private static List<int[]> chicken, house;
    private static int min, max;
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new int[M];
        house = new ArrayList<>();
        chicken = new ArrayList<>();
        min = Integer.MAX_VALUE;
        squares = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 1) {
                    squares[i][j] = 1;
                    house.add(new int[]{i, j});
                } else if (n == 2) {
                    squares[i][j] = 0;
                    chicken.add(new int[]{i, j});
                } else {
                    squares[i][j] = 0;
                }
            }
        }
        combination(0, 0);
        System.out.println(min);
    }

    private static void combination(int index, int start) {
        if (index == M) {
            for (int i = 0; i < index; i++) {
                int[] position = chicken.get(array[i]);
                squares[position[0]][position[1]] = 2;
            }
            int distance = 0;
            for (int[] p : house) {
                Queue<int[]> queue = new LinkedList<>();
                queue.offer(p);
                boolean[][] visited = new boolean[N][N];
                while (!queue.isEmpty()) {
                    int[] pop = queue.poll();
                    if (squares[pop[0]][pop[1]] == 2) {
                        distance += Math.abs(p[0] - pop[0]) + Math.abs(p[1] - pop[1]);
                        break;
                    }

                    for (int[] ints : direction) {
                        int newX = pop[0] + ints[0];
                        int newY = pop[1] + ints[1];

                        if (isIn(newX, newY) && !visited[newX][newY]) {
                            visited[newX][newY] = true;
                            queue.offer(new int[]{newX, newY});
                        }
                    }
                }
            }
            for (int i = 0; i < index; i++) {
                int[] position = chicken.get(array[i]);
                squares[position[0]][position[1]] = 0;
            }
            min = Math.min(distance, min);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}