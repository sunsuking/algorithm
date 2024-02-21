import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] nums = new int[11];
        nums[1] = 1;
        nums[2] = 2;
        nums[3] = 4;
        for (int i = 4; i <= 10; i++) {
            nums[i] = nums[i - 1] + nums[i - 2] + nums[i - 3];
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            builder.append(nums[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.print(builder);
    }
}