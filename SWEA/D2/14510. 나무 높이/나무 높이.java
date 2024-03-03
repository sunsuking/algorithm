import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            int max = Integer.MIN_VALUE;
            st = new StringTokenizer(br.readLine());
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
                max = Math.max(nums[i], max);
            }
            int day = 0;
            int one = 0;
            int two = 0;
            for (int i = 0; i < n; i++) {
                nums[i] = max - nums[i];
                two += nums[i] / 2;
                one += nums[i] % 2;
            }

            while (one + 1 < two) {
                one += 2;
                two -= 1;
            }

            while (one >= 1 && two >= 1) {
                one--;
                two--;
                day += 2;
            }

            if (one >= 1) day += 1 + (one - 1) * 2;
            else if (two == 1) day += 2;

            builder.append("#").append(testCase).append(" ").append(day).append("\n");
        }
        System.out.print(builder);
    }
}
