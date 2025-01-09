import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        long min = Long.MAX_VALUE;

        long[] nums = new long[num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        long[] ans = new long[3];

        Arrays.sort(nums);

        for (int i = 0; i < num - 2; i++) {
            int L = i + 1;
            int R = num - 1;

            while (L < R) {
                long sum = nums[i] + nums[L] + nums[R];

                if (Math.abs(sum) < min) {
                    ans = new long[]{nums[i], nums[L], nums[R]};
                    min = Math.abs(sum);
                }

                if (sum < 0) L++;
                else R--;
            }
        }

        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
}
