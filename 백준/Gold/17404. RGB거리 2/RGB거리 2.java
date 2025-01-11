import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] squares = new int[N][3];
        StringTokenizer st;
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < 3; y++) {
                squares[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        int INF = 1000 * 1000 + 1;
        int result = INF;

        for (int firstColor = 0; firstColor < 3; firstColor++) {
            int[][] dp = new int[N][3];

            for (int i = 0; i < 3; i++) {
                if (i == firstColor) {
                    dp[0][i] = squares[0][i];
                } else {
                    dp[0][i] = INF;
                }
            }

            for (int i = 1; i < N; i++) {
                dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + squares[i][0];
                dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + squares[i][1];
                dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + squares[i][2];
            }

            for (int i = 0; i < 3; i++) {
                if (i != firstColor) {
                    result = Math.min(result, dp[N - 1][i]);
                }
            }
        }

        System.out.println(result);
    }
}