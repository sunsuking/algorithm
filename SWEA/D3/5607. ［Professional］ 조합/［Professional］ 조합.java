import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
    private static final int MOD = 1234567891;
    private static long[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dp = new long[4_000_001];
        dp[0] = 1;
        for (int i = 1; i <= 4_000_000; i++) {
            dp[i] = ((dp[i - 1] % MOD) * i) % MOD;
        }
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long n = dp[N];
            long r = (dp[R] * dp[N - R]) % MOD;

            long sum = pow(r, MOD - 2);
            sum *= n;
            sum %= MOD;
            builder.append("#").append(testCase).append(" ").append(sum).append("\n");
        }
        System.out.print(builder);
    }

    private static long pow(long a, long b) {
        if (b == 0) return 1;
        if (b % 2 > 0) return (pow(a, b - 1) * a) % MOD;
        long half = pow(a, b / 2) % MOD;
        return (half * half) % MOD;
    }
}