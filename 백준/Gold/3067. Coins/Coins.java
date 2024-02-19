import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] nums;
    private static int count, num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        for (int testCase = 0; testCase < T; testCase++) {
            count = 0;
            num = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            nums = new int[num];
            for (int i = num - 1; i >= 0; i--) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            int price = Integer.parseInt(br.readLine());
            dfs(0, price);
            builder.append(count).append("\n");
        }
        System.out.print(builder);
    }

    private static void dfs(int index, int remain) {
        if (index == nums.length - 1) {
            if (remain % nums[index] == 0) count++;
            return;
        }
        int n = nums[index];
        for (int i = 0; i <= remain / n; i++) {
            dfs(index + 1, remain - (n * i));
        }
    }
}