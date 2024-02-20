import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < T; i++) {
            char[] chars = br.readLine().toCharArray();
            int prevSolve = chars[0] == 'O' ? 1 : 0;
            int sum = prevSolve;
            for (int x = 1; x < chars.length; x++) {
                if (chars[x] == 'O') {
                    prevSolve++;
                    sum += prevSolve;
                } else {
                    prevSolve = 0;
                }
            }
            builder.append(sum).append("\n");
        }
        System.out.print(builder);
    }
}