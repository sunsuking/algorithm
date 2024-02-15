import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int max = Math.max(x, y);
        int min = Math.min(x, y);
        int remain = max - min;
        long sum = 0;
        if (w > s) {
            sum += (long) s * min;
            if (remain % 2 == 0) {
                sum += (long) remain * s;
            } else {
                sum += (long) (remain - 1) * s;
                sum += w;
            }
        } else if (w * 2 < s) {
            sum += (long) (x + y) * w;
        } else {
            sum += (long) min * s;
            sum += (long) remain * w;
        }
        System.out.println(sum);
    }
}