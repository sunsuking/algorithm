import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    private static int[] array, biskets;
    private static int max, maxWeight, N;
    public static final int MAX_BISKETS = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            max = -1;
            N = Integer.parseInt(st.nextToken());
            biskets = new int[N];
            array = new int[N];
            maxWeight = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                biskets[i] = Integer.parseInt(st.nextToken());
            }

            combination(0, 0);
            builder.append("#").append(testCase).append(" ").append(max).append("\n");
        }
        System.out.println(builder);
    }

    private static void combination(int index, int start) {
        if (index == MAX_BISKETS) {
            int sum = biskets[array[0]] + biskets[array[1]];
            if (sum <=   maxWeight) {
                max = Math.max(sum, max);
            }
            return;
        }

        for (int i = start; i < N; i++) {
            array[index] = i;
            combination(index + 1, i + 1);
        }
    }
}