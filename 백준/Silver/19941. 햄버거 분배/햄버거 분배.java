import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int count = 0;
    private static char[] chars;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        chars = br.readLine().toCharArray();

        for (int i = 0; i < N; i++) {
            if (chars[i] == 'P') {
                int index = Math.max(i - K, 0);
                boolean ate = false;
                for (int j = index; j < i; j++) {
                    if (canEat(j)) {
                        ate = true;
                        break;
                    }
                }

                if (!ate) {
                    index = i + K >= N ? N - 1 : i + K;
                    for (int j = i + 1; j <= index; j++) {
                        if (canEat(j)) break;
                    }
                }
            }
        }

        System.out.println(count);
    }

    private static boolean canEat(int index) {
        if (chars[index] == 'H') {
            chars[index] = 'X';
            count++;
            return true;
        }
        return false;
    }
}
