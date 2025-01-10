import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] squares;
    private static int MIN = Integer.MAX_VALUE;
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        squares = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int z = 0; z < N; z++) {
                    squares[x][y] = Math.min(squares[x][y], squares[x][z] + squares[z][y]);
                }
            }
        }

        dfs(K, 0, 1 << K);

        System.out.println(MIN);
    }

    private static void dfs(int n, int weight, int visited) {
        if (visited == (1 << N) - 1) {
            MIN = Math.min(MIN, weight);
        }

        for (int i = 0; i < N; i++) {
            if (i != n && (visited & (1 << i)) == 0) {
                dfs(i, weight + squares[n][i], visited | (1 << i));
            }
        }
    }
}