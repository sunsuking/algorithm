import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] nums = new int[num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder builder = new StringBuilder();
        if (prevPermutation(nums)) {
            for (int n : nums) builder.append(n).append(" ");
        } else {
            builder.append("-1");
        }
        System.out.println(builder);
    }

    private static boolean prevPermutation(int[] array) {
        int n = array.length - 1;
        while(n > 0 && array[n - 1] < array[n]) n--;
        if (n == 0) return false;

        int m = array.length - 1;
        while(n < m && array[n - 1] < array[m]) m--;
        swap(array, n - 1, m);

        int k = array.length - 1;
        while(n < k) swap(array, n++, k--);
        return true;
    }

    private static void swap(int[] array, int x, int y) {
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }
}