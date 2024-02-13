import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int[] array;
    private static boolean[] visited;
    private static int[][] map;
    private static int min, N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        array = new int[N + 1];
        visited = new boolean[N];
        min = Integer.MAX_VALUE;

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        permutation(0);
        System.out.println(min);
    }

    private static void permutation(int index) {
        if (index == N) {
            int sum = 0;
            array[N] = array[0];
            for (int i = 0; i < N; i++) {
                if (map[array[i]][array[i + 1]] == 0) return;
                sum += map[array[i]][array[i + 1]];
            }
//            System.out.println(Arrays.toString(array));
            min = Math.min(sum, min);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                array[index] = i;
                visited[i] = true;
                permutation(index + 1);
                visited[i] = false;
            }
        }
    }


}