import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static String[] numbers;
    private static int x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        numbers = new String[1000];
        int[] nums = new int[0];
        makeNums(num, nums);
        int i = 0;
        for (int j = 0; j < numbers.length; j++) {
            if (numbers[j] == null) break;
            i++;
        }
        String[] newNumbers = Arrays.copyOf(numbers, i);
        if (k - 1 >= newNumbers.length) {
            System.out.println(-1);
        } else {
            System.out.println(newNumbers[k - 1]);
        }
    }

    private static void makeNums(int remain, int... nums) {
        if (remain < 0) return;
        if (remain == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nums.length; i++) {
                sb.append(nums[i]);
                if (i != nums.length - 1) {
                    sb.append("+");
                }
            }
            numbers[x++] = sb.toString();
        } else {
            if (remain >= 1) {
                int[] newNums = Arrays.copyOf(nums, nums.length + 1);
                newNums[newNums.length - 1] = 1;
                makeNums(remain - 1, newNums);
            }
            if (remain >= 2) {
                int[] newNums = Arrays.copyOf(nums, nums.length + 1);
                newNums[newNums.length - 1] = 2;
                makeNums(remain - 2, newNums);
            }
            if (remain >= 3) {
                int[] newNums = Arrays.copyOf(nums, nums.length + 1);
                newNums[newNums.length - 1] = 3;
                makeNums(remain - 3, newNums);
            }
        }
    }
}
