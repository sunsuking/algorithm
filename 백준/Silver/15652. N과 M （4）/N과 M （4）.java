import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder builder;
    private static int[] array;
    private static int num, count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        builder = new StringBuilder();
        num = Integer.parseInt(st.nextToken());
        count = Integer.parseInt(st.nextToken());
        array = new int[num + 1];
        dfs(0);
        System.out.print(builder);
    }

    private static void dfs(int index) {
        if (count == index) {
            StringBuilder sb = new StringBuilder();
            int prev = array[0];
            sb.append(prev).append(" ");
            for (int i = 1; i < index; i++) {
                if (prev > array[i]) return;
                sb.append(array[i]).append(" ");
                prev = array[i];
            }
            builder.append(sb).append("\n");
            return;
        }

        for (int i = 1; i <= num; i++) {
            array[index] = i;
            dfs(index + 1);
        }
    }
}
