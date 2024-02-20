import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= num; i++) {
            builder.append(repeat(" ", num - i));
            builder.append(repeat("*", i));
            builder.append("\n");
        }
        System.out.println(builder);
    }

    private static String repeat(String s, int count) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            builder.append(s);
        }
        return builder.toString();
    }
}