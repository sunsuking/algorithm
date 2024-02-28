import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        dp = new int[31][31];
        for (int i = 1; i <= 30; i++) Arrays.fill(dp[i], 1);
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            builder.append(combination(Math.max(n, k), Math.min(n, k))).append("\n");
        }
        System.out.print(builder);
    }

    private static int combination(int n, int k) {
        if (n == k) return dp[n][k];
        if (k == 1) {
            dp[n][k] = n;
            return dp[n][k];
        }

        if (dp[n - 1][k - 1] == 1) {
            dp[n - 1][k - 1] = combination(n - 1, k - 1);
        }
        if (dp[n - 1][k] == 1) {
            dp[n - 1][k] = combination(n - 1, k);
        }
        return dp[n - 1][k - 1] + dp[n - 1][k];
    }
}