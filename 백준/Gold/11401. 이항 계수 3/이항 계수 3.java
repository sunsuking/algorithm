import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static final int MOD = 1_000_000_007;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        long n = 1;
        long r = 1;
        for (int i = 1; i <= N; i++) {
            n *= i;
            n %= MOD;
        }

        for (int i = 1; i <= R; i++) {
            r *= i;
            r %= MOD;
        }

        for (int i = 1; i <= N - R; i++) {
            r *= i;
            r %= MOD;
        }
        long sum = pow(r, MOD - 2);
        sum *= n;
        sum %= MOD;
        System.out.println(sum);
    }

    private static long pow(long a, long b) {
        if (b == 0) return 1;
        if (b % 2 > 0) return (pow(a, b - 1) * a) % MOD;
        long half = pow(a, b / 2) % MOD;
        return (half * half) % MOD;
    }
}