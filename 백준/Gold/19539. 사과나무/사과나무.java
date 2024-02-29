import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[num];
        int sum = 0;
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < num; i++) {
            int n = Integer.parseInt(st.nextToken());
            sum += n;
            num1 += n % 2;
            num2 += n / 2;
        }

        if (sum % 3 != 0) {
            System.out.println("NO");
            System.exit(0);
        }

        if (num1 == num2) {
            System.out.println("YES");
            System.exit(0);
        }

        while (num1 <= num2) {
            num1 += 2;
            num2 -= 1;
            if (num1 == num2) {
                System.out.println("YES");
                System.exit(0);
            }
        }

        System.out.println("NO");
    }
}