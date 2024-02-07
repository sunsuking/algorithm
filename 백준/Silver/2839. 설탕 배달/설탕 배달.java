import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        dfs(num, 0);
        System.out.println(-1);
    }

    private static void dfs(int weight, int count) {
        if (weight == 0) {
            System.out.println(count);
            System.exit(0);
        }

        if (weight >= 5) {
            dfs(weight - 5, count + 1);
        }
        if (weight >= 3) {
            dfs(weight - 3, count + 1);
        }
    }


}