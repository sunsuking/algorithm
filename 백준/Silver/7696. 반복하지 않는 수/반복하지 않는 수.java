import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int[] nums;
    private static int count;
    private static boolean[] visited;
    private static int MAX_SIZE = 1_000_000;
    public static void main(String[] args) throws IOException {
        nums = new int[MAX_SIZE];
        visited = new boolean[10];

        for (int size = 1; size <= 8; size++) {
            for (int i = 1; i <= 9; i++) {
                if (count >= MAX_SIZE) break;
                visited[i] = true;
                combination(1, size, i);
                visited[i] = false;
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int n;
        while ((n = Integer.parseInt(br.readLine())) != 0) builder.append(nums[n - 1]).append("\n");
        System.out.print(builder);
    }

    private static void combination(int depth, int size, int sum) {
        if (count >= MAX_SIZE) return;

        if (depth == size) {
            nums[count++] = sum;
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            combination(depth + 1, size, sum * 10 + i);
            visited[i] = false;
        }
    }
}