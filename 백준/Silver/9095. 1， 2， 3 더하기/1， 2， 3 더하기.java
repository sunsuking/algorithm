import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static Set<String> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] count = new int[11];
        for (int i = 1; i <= 10; i++) {
            nums = new HashSet<>();
            makeNums(i, "");
//            System.out.println(nums);
            count[i] = nums.size();
        }

        StringBuilder builder = new StringBuilder();
        for (int testCase = 0; testCase < T; testCase++) {
            int n = Integer.parseInt(br.readLine());
            builder.append(count[n]).append("\n");
        }
        System.out.print(builder);
    }

    private static void makeNums(int remain, String num) {
        if (remain < 0) return;
        if (remain == 0) {
            nums.add(num);
        }

        if (remain >= 1) {
            makeNums(remain - 1, num + "1+");
        }
        if (remain >= 2) {
            makeNums(remain - 2, num + "2+");
        }
        if (remain >= 3) {
            makeNums(remain - 3, num + "3+");
        }
    }
}