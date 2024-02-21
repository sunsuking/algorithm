import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static boolean[] visited;
    private static int L, C;
    private static char[] array, chars;
    private static StringBuilder builder;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        array = new char[L];
        visited = new boolean[C];
        builder = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        chars = new char[C];
        for (int i = 0; i < C; i++) chars[i] = st.nextToken().charAt(0);
        Arrays.sort(chars);
        permutation(0);
        System.out.print(builder);
    }

    private static boolean isConsonant(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    private static void permutation(int index) {
        if (index == L) {
            int consonantCount = 0;
            int notConsonantCount = 0;
            for (int i = 0; i < index; i++) {
                if (isConsonant(array[i])) consonantCount++;
                else notConsonantCount++;
            }
            if (consonantCount >= 1 && notConsonantCount >= 2) builder.append(new String(array)).append("\n");
            return;
        }

        for (int i = 0; i < C; i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (index > 0) {
                    if (array[index - 1] < chars[i]) {
                        array[index] = chars[i];
                        permutation(index + 1);
                    }
                } else {
                    array[index] = chars[i];
                    permutation(index + 1);
                }
                visited[i] = false;
            }
        }
    }
}