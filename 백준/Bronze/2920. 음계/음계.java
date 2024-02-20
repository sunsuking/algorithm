import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        int prev = Integer.parseInt(st.nextToken());
        String s = num1 > prev ? "descending" : "ascending";
        for (int i = 0; i < 6; i++) {
            int now = Integer.parseInt(st.nextToken());
            if (prev > now && s.equals("ascending")) {
                System.out.println("mixed");
                System.exit(0);
            } else if (prev < now && s.equals("descending")) {
                System.out.println("mixed");
                System.exit(0);
            }
            prev = now;
        }
        System.out.println(s);
    }
}