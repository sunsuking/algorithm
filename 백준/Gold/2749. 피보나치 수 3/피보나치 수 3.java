import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    private static final int MOD = 1_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger integer = new BigInteger(br.readLine());
        int num = MOD / 10 * 15;
        int[] dp = new int[num + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }

        int size = integer.mod(BigInteger.valueOf(num)).intValue();

        System.out.println(dp[size]);
    }
}