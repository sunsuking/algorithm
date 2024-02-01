import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.valueOf(br.readLine());
        int c = 0;
        for (int i = 0; i < num; i++) {
            String str = br.readLine();
            int[] count = new int['Z' - 'A' + 1];
            char[] chars = str.toCharArray();
            char prev = chars[0];
            count[prev - 'a'] += 1;
            for (int x = 1; x < chars.length; x++) {
                if (count[chars[x] - 'a'] > 0) {
                    if (prev != chars[x]) {
                        c++;
                        break;
                    }
                }
                prev = chars[x];
                count[chars[x] - 'a'] += 1;
            }
        }
        System.out.println(num - c);
    }
}
