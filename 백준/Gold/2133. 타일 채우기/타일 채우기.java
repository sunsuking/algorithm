import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        array = new ArrayList<>();
        dfs(num, "");
        int sum = 0;
        for (String s : array) {
            int sum1 = 1;
            String[] nums = s.split("\\.");
            for (String n : nums) {
                int i = Integer.parseInt(n);
                if (i != 2) {
                    sum1 *= 2;
                } else {
                    sum1 *= 3;
                }
            }
            sum += sum1;
        }
        System.out.println(sum);
    }

    private static void dfs(int remain, String s) {
        if (remain == 0) {
            array.add(s.substring(0, s.length() - 1));
        }
        for (int i = 2; i <= 30; i += 2) {
            if (remain >= i) {
                dfs(remain - i, s + i + ".");
            }
        }
    }
}