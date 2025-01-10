import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = new int[N];
        int[] sums = new int[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i - 1] = Integer.parseInt(st.nextToken());
            sums[i] = nums[i - 1] + sums[i - 1];
        }

        int min = Integer.MAX_VALUE;

        int L = 0;
        int R = 1;

        while (R < N + 1 && L < N + 1) {
            int sum = sums[R] - sums[L];
//            System.out.println("L: " + L + ", R: " + R + ", sum: " + sum);

            if (sum < S) R++;
            else {
                L++;
                min = Math.min(R - L + 1, min);
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }
}

// [5, 6, 9, 14, 24, 31, 35, 44, 46, 54]