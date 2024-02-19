import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        Map<Integer, Boolean> map = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            map.put(Integer.parseInt(st.nextToken()), true);
        }
        int T = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < T; i++) {
            builder.append(map.containsKey(Integer.parseInt(st.nextToken())) ? 1 : 0).append("\n");
        }
        System.out.print(builder);
    }
}