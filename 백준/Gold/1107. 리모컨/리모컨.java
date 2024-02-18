import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {
    private static int size = 10;
    private static int channel, min;
    private static char[] chars;
    private static List<Integer> available;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int now = 100;
        String c = br.readLine();
        channel = Integer.parseInt(c);
        if (now == channel) {
            System.out.println(0);
            System.exit(0);
        }
        chars = c.toCharArray();
        min = Math.abs(channel - now);
        available = new ArrayList<>();

        for (int i = 0; i <= 9; i++) {
            available.add(i);
        }
        int count = Integer.parseInt(br.readLine());

        StringTokenizer st = null;
        if (count != 0) {
            st = new StringTokenizer(br.readLine());
        }
        for (int i = 0; i < count; i++) {
            available.remove(new Integer(Integer.parseInt(st.nextToken())));
        }

        permutation(0, new char[0]);
        if (chars.length > 1) {
            permutation2(1, new char[]{'0'});
        }
        permutation3(0, new char[0]);

        System.out.println(min);
    }

    private static void permutation(int index, char[] nums) {
        if (nums.length == chars.length) {
            int num = Integer.parseInt(new String(nums));
            min = Math.min(min, Math.abs(channel - num) + index);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (available.contains(i)) {
                char[] newChars = Arrays.copyOf(nums, index + 1);
                newChars[index] = (char) (i + '0');
                permutation(index + 1, newChars);
            }
        }
    }

    private static void permutation2(int index, char[] nums) {
        if (nums.length == chars.length) {
            int num = Integer.parseInt(new String(nums));
            min = Math.min(min, Math.abs(channel - num) + (index - 1));
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (available.contains(i)) {
                char[] newChars = Arrays.copyOf(nums, index + 1);
                newChars[index] = (char) (i + '0');
                permutation2(index + 1, newChars);
            }
        }
    }

    private static void permutation3(int index, char[] nums) {
        if (nums.length == chars.length + 1) {
            int num = Integer.parseInt(new String(nums));
            min = Math.min(min, Math.abs(channel - num) + index);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (available.contains(i)) {
                char[] newChars = Arrays.copyOf(nums, index + 1);
                newChars[index] = (char) (i + '0');
                permutation3(index + 1, newChars);
            }
        }
    }
}