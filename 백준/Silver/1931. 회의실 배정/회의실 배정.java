import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[][] nums = new int[num][2];

        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        Arrays.sort(nums, (o1, o2) -> {
            int compare = Integer.compare(o1[1], o2[1]);
            if (compare == 0) {
                return Integer.compare(o1[0], o2[0]);
            }
            return compare;
        });

//        for (int[] n : nums) System.out.println(Arrays.toString(n));

        int end = nums[0][1];
        int count = 1;
        for (int i = 1; i < num; i++) {
            if (nums[i][0] >= end) {
                end = nums[i][1];
                count++;
            }
        }
        System.out.println(count);
    }
}