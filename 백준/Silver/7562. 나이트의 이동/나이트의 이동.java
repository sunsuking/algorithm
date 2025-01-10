import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] directions = {
            {-2, 1},
            {-2, -1},
            {-1, 2},
            {-1, -2},
            {1, 2},
            {1, -2},
            {2, 1},
            {2, -1},
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            boolean[][] visited = new boolean[N][N];

            st = new StringTokenizer(br.readLine());
            int[] start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0};
            st = new StringTokenizer(br.readLine());
            int[] end = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(start);

            while (!queue.isEmpty()) {
                int[] pop = queue.poll();

                if (pop[0] == end[0] && pop[1] == end[1]) {
                    builder.append(pop[2]).append("\n");
                    break;
                }

                for (int[] direction : directions) {
                    int newX = pop[0] + direction[0];
                    int newY = pop[1] + direction[1];

                    if (isIn(newX, newY, N) && !visited[newX][newY]) {
                        visited[newX][newY] = true;
                        queue.offer(new int[]{newX, newY, pop[2] + 1});
                    }
                }
            }
        }

        System.out.println(builder);
    }

    private static boolean isIn(int x, int y, int N) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
}
