import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());
        int[] sums = new int[n + 1];
        int sum = 0;
        sums[0] = sum;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            sum += Integer.parseInt(st.nextToken());
            sums[i] = sum;
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken());
            builder.append(sums[end] - sums[start]).append("\n");
        }
        System.out.println(builder);
    }
}
