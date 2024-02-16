import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] nums = new int[1000002];
        for (int i = 1; i <= 1000000; i++) {
            if (nums[i + 1] == 0) {
                nums[i + 1] = nums[i] + 1;
            } else {
                nums[i + 1] = Math.min(nums[i] + 1, nums[i + 1]);
            }

            if (i * 3 <= 1000000) {
                if (nums[i * 3] == 0) {
                    nums[i * 3] = nums[i] + 1;
                } else {
                    nums[i * 3] = Math.min(nums[i] + 1, nums[i * 3]);
                }
            }

            if (i * 2 <= 1000000) {
                if (nums[i * 2] == 0) {
                    nums[i * 2] = nums[i] + 1;
                } else {
                    nums[i * 2] = Math.min(nums[i] + 1, nums[i * 2]);
                }
            }

            if (i * 6 <= 1000000) {
                nums[i * 6] = Math.min(nums[i * 3] + 1, nums[i * 2] + 1);
            }
        }
        System.out.println(nums[num]);
    }
}