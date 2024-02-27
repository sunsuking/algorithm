import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        BigInteger[] nums = new BigInteger[10001];
        nums[0] = new BigInteger("0");
        nums[1] = new BigInteger("1");
        for (int i = 2; i <= 10000; i++) {
            nums[i] = nums[i - 1].add(nums[i - 2]);
        }
        System.out.println(nums[num]);
    }
}