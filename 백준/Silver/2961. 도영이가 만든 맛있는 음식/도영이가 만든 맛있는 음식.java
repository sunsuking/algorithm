import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N, nums[][], min;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N][2];
        visited = new boolean[N];
        StringTokenizer st;
        min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i][0] = Integer.parseInt(st.nextToken());
            nums[i][1] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 1L, 0L);
        System.out.println(min);
    }

    private static void dfs(int index, long multiple, long sum) {
        if (index == N) {
            if (multiple == 1 && sum == 0) return;
//            System.out.println(multiple + " : " + sum);
            min = Math.min(min, (int) Math.abs(sum - multiple));
            return;
        }

        visited[index] = true;
        dfs(index + 1, multiple * nums[index][0], sum + nums[index][1]);
        visited[index] = false;
        dfs(index + 1, multiple, sum);
    }
}
