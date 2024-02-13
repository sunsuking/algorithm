import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int win, lose;
    private static final int N = 9;
    private static int[] nums, array, remain;
    private static boolean[] visited;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        nums = new int[N];
        remain = new int[N];
        int[] all = new int[N * 2];
        visited = new boolean[N];
        array = new int[N];
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            win = 0; lose = 0;
            for (int i = 1; i <= N * 2; i++) {
                all[i - 1] = i;
            }
            for (int i = 0; i < N; i++) {
                int n = Integer.parseInt(st.nextToken());
                all[n - 1] = 0;
                nums[i] = n;
            }

            int x = 0;
            for (int j : all) {
                if (j != 0) remain[x++] = j;
            }

            permutation(0);
            builder.append("#").append(testCase).append(" ").append(win).append(" ").append(lose).append("\n");
        }
        System.out.print(builder);
    }

    private static void permutation(int index) {
        if (index == N) {
            int a = 0;
            int b = 0;
            for (int i = 0; i < N; i++) {
                if (array[i] > nums[i]) a += array[i] + nums[i];
                else if (array[i] < nums[i]) b += array[i] + nums[i];
            }
            if (a > b) lose++;
            if (b > a) win++;
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                array[index] = remain[i];
                visited[i] = true;
                permutation(index + 1);
                visited[i] = false;
            }
        }
    }
}