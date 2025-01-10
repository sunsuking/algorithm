import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static boolean[] visited;
    private static boolean[] finished;
    private static int[] nums;
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;

        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());
            nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken()) - 1;

            visited = new boolean[N];
            finished = new boolean[N];
            count = 0;

            for (int i = 0; i < N; i++) {
                dfs(i);
            }

            builder.append(N - count).append("\n");
        }

        System.out.println(builder);
    }

    private static void dfs(int n) {
        if (visited[n]) return;

        visited[n] = true;
        int next = nums[n];

        if (!visited[next]) {
            dfs(next);
        } else {
            if (!finished[next]) {
                count++;
                for (int i = next; i != n; i = nums[i]) count++;
            }
        }

        finished[n] = true;
    }
}
