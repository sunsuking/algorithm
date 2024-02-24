import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[][] direction = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        List<N16236Position> fishs = new ArrayList<>();
        int startX = 0;
        int startY = 0;

        int[][] squares = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
                if (squares[x][y] != 0) {
                    if (squares[x][y] == 9) {
                        startX = x;
                        startY = y;
                        squares[x][y] = 0;
                    } else {
                        fishs.add(new N16236Position(x, y, Integer.MAX_VALUE));
                    }
                }
            }
        }

        int size = 2;
        int day = 0;
        int count = 0;
        while (!fishs.isEmpty()) {
            List<N16236Position> canFishs = new ArrayList<>();
            for (N16236Position position : fishs) {
                if (squares[position.x][position.y] < size)
                    canFishs.add(position);
            }

            if (canFishs.isEmpty()) break;

            main:
            for (N16236Position position : canFishs) {
                Queue<int[]> queue = new LinkedList<>();
                boolean[][] visited = new boolean[N][N];
                queue.offer(new int[]{startX, startY, 0});
                while (!queue.isEmpty()) {
                    int[] pop = queue.poll();
                    for (int[] ints : direction) {
                        int newX = pop[0] + ints[0];
                        int newY = pop[1] + ints[1];

                        if (isIn(newX, newY) && squares[newX][newY] <= size && !visited[newX][newY]) {
                            visited[newX][newY] = true;
                            if (newX == position.x && newY == position.y) {
                                position.distance = pop[2] + 1;
                                continue main;
                            }
                            queue.offer(new int[]{newX, newY, pop[2] + 1});
                        }
                    }
                }
            }

            canFishs.sort(Comparator.comparingInt((N16236Position p) -> p.distance).thenComparingInt(p -> p.x).thenComparingInt(p -> p.y));
            N16236Position position = canFishs.get(0);
            if (position.distance == Integer.MAX_VALUE) {
                break;
            }
            squares[position.x][position.y] = 0;
            day += position.distance;
            startX = position.x;
            startY = position.y;
            count++;
            if (size == count) {
                size++;
                count = 0;
            }
            fishs.remove(position);
        }
        System.out.println(day);
    }

    private static boolean isIn(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static class N16236Position {
        int x;
        int y;
        int distance;

        public N16236Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}