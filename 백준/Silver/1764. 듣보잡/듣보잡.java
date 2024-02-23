import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num1 = Integer.parseInt(st.nextToken());
        int num2 = Integer.parseInt(st.nextToken());
        Map<String, Boolean> names = new HashMap<>();

        for (int i = 0; i < num1; i++) {
            names.put(br.readLine(), true);
        }

        Queue<String> noHearNoSee = new PriorityQueue<>();
        for (int i = 0; i < num2; i++) {
            String name = br.readLine();
            if (names.get(name) != null) {
                noHearNoSee.offer(name);
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(noHearNoSee.size()).append("\n");
        while (!noHearNoSee.isEmpty()) builder.append(noHearNoSee.poll()).append("\n");
        System.out.print(builder);
    }
}