
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder builder;
    private static int[] array;
    private static boolean[] visited;
    private static int num, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        builder = new StringBuilder();
        num = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());
        visited = new boolean[num + 1];
        array = new int[num + 1];
        dfs(0);
        System.out.print(builder);
    }

    private static void dfs(int index) {
        if (count == index) {
            for (int i = 0; i < index; i++) {
                builder.append(array[i]).append(" ");
            }
            builder.append("\n");
            return;
        }

        for (int i = 1; i <= num; i++) {
            array[index] = i;
            dfs(index + 1);
        }
    }
}
