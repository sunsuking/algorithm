import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());
        Map<String, String> pocketName = new HashMap<>();
        Map<String, String> pocketNum = new HashMap<>();
        for (int i = 0; i < num; i++) {
            String name = br.readLine();
            String n = String.valueOf(i + 1);
            pocketName.put(name, n);
            pocketNum.put(n, name);
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < count; i++) {
            String keyword = br.readLine();
            String pocket = pocketName.getOrDefault(keyword, pocketNum.get(keyword));
            builder.append(pocket).append("\n");
        }
        System.out.println(builder);
    }
}
