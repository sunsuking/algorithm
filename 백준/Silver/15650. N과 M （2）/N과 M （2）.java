import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder builder;
    private static int[] array;
    private static int num, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        builder = new StringBuilder();
        num = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new int[num];
        combination(0, 0);
        System.out.print(builder);
    }

    private static void combination(int count, int start) {
        if (count == M) {
            for (int i = 0; i < count; i++) {
                builder.append(array[i]).append(" ");
            }
            builder.append("\n");
        }

        for (int i = start; i < num; i++) {
            array[count] = i + 1;
            combination(count + 1, i + 1);
        }
    }
}
