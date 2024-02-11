import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] pianos = new int[num];
        int[] diff = new int[num];
        int[] sum = new int[num + 1];
        StringBuilder builder = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            pianos[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < num - 1; i++) {
            if (pianos[i] > pianos[i + 1]) diff[i] = 1;
        }

        for (int i = 1; i <= num; i++) {
            sum[i] = sum[i - 1] + diff[i - 1];
        }

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int s = sum[end] - sum[start];
            builder.append(s).append("\n");
        }
        System.out.println(builder);

    }
}