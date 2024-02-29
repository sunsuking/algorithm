import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int count = 0;
    private static Map<Integer, Integer> map;
    private static boolean isUp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int D = Math.abs(H - N);
        long[][] dp = new long[D + 1][D + 1];
        Arrays.fill(dp[0], 1);
        for (int x = 1; x <= D; x++) {
            dp[x][x] = dp[x - 1][x];
            for (int y = x + 1; y <= D; y++) {
                dp[x][y] = dp[x][y - 1] + dp[x - 1][y];
            }
        }
//        for (long[] d : dp) System.out.println(Arrays.toString(d));
        System.out.println(dp[D][D]);
    }
}