import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[] zero, one;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        zero = new int[41];
        one = new int[41];
        zero[0] = 1;
        one[1] = 1;
        for (int i = 2; i <= 40; i++) {
            fibonacci(i);
        }
        int num = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            int n = Integer.parseInt(br.readLine());
            builder.append(zero[n]).append(" ").append(one[n]).append("\n");
        }
        System.out.print(builder);
    }

    private static int[] fibonacci(int n) {
        if (n == 0) {
            return new int[] {zero[0], one[0]};
        } else if (n == 1) {
            return new int[] {zero[1], one[1]};
        } else {
            if (one[n] == 0 && zero[n] == 0) {
                int[] n1 = fibonacci(n - 1);
                int[] n2 = fibonacci(n - 2);
                zero[n] = n1[0] + n2[0];
                one[n] = n1[1] + n2[1];
            }
            return new int[]{zero[n], one[n]};
        }
    }

}