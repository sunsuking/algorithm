import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            char[] chars = st.nextToken().toCharArray();
            for (char c : chars) {
                repeat(builder, c, count);
            }
            builder.append("\n");
        }
        System.out.print(builder);
    }

    private static StringBuilder repeat(StringBuilder builder, char c, int count) {
        for (int i = 0; i < count; i++) {
            builder.append(c);
        }
        return builder;
    }
}