import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        char[] chars = s.toCharArray();

        StringBuilder builder = new StringBuilder();

        char prev = chars[0];
        int length = prev == 'M' ? 1 : 0;
        builder.append(prev == 'M' ? "" : "5");
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == 'K') {
                builder.append("5");
                for (int x = 0; x < length; x++) {
                    builder.append("0");
                }
                length = 0;
            } else {
                length++;
            }
        }
        if (length > 0) {
            for(int x = 0; x < length; x++) {
                builder.append("1");
            }
        }

        builder.append("\n").append(chars[0] == 'M' ? "1" : "5");
        prev = chars[0];
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == 'M') {
                if (chars[i] == prev) {
                    builder.append("0");
                } else {
                    builder.append("1");
                }
            } else {
                builder.append("5");
            }
            prev = chars[i];
        }
        System.out.println(builder);
    }
}