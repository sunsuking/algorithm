import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        HashSet<String> has = new HashSet<>();
        for (int i = 0; i < N; i++) {
            has.add((i + 1) + " " + (i + 1));
            int prev = i - 1;
            int next = i + 1;
            while (isIn(next) && nums[i] == nums[next]) {
                has.add((i + 1) + " " + (next + 1));
                next++;
            }
            prev = i - 1;
            next = i + 1;
            while (isIn(prev) && isIn(next) && nums[prev] == nums[next]) {
                has.add((prev + 1) + " " + (next + 1));
                prev--;
                next++;
            }
        }

        StringBuilder builder = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            if (has.contains(str)) builder.append("1").append("\n");
            else builder.append("0").append("\n");
        }
        System.out.print(builder);
    }

    private static boolean isIn(int n) {
        return n >= 0 && n < N;
    }
}