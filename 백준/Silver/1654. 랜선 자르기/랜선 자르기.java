import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int sum = 0;
        int[] nums = new int[N];
        int maxValue = Integer.MIN_VALUE;

        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            maxValue = Math.max(maxValue, nums[i]);
            sum += nums[i];
        }

        if (K == 1) {
            System.out.println(maxValue);
            System.exit(0);
        }

        long min = 1;
        long max = maxValue;
        long current = (min + max) / 2;

        while(min <= max) {
            long count = 0;
            for(int i = 0; i < N; i++) {
                count += nums[i] / current;
            }

            if (count >= K) {
                min = (current + 1);
                current = (min + max) / 2;
            } else {
                max = current - 1;
                current = (min + max) / 2;
            }
        }
        System.out.println(current);
    }
}