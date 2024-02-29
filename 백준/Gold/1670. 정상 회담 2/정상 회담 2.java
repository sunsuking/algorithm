import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final int MOD = 987654321;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[10001];
        dp[0] = 1;
        dp[2] = 1;
        dp[4] = 2;
        for (int i = 6; i <= 10000; i += 2) {
            long sum = 0;
            for (int x = 0; x <= i - 2; x += 2) {
                sum += ((dp[x] % MOD) * (dp[i - 2 - x] % MOD) % MOD);
            }
            dp[i] = sum % MOD;
        }
        
        System.out.println(dp[N]);
    }
}