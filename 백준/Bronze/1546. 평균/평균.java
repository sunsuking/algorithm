import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        double[] scores = new double[num];
        double max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, scores[i]);
        }

        for (int i = 0; i < num; i++) {
            scores[i] = scores[i] / max * 100;
        }

        System.out.println(Arrays.stream(scores).average().orElse(0.0));
    }
}