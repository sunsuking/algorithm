import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= num; i++) {
            queue.offer(i);
        }
        StringBuilder builder = new StringBuilder();
        builder.append("<");
        int count = 0;
        while (!queue.isEmpty()) {
            int pop = queue.poll();
            if (++count % k == 0) {
                builder.append(pop);
                if (!queue.isEmpty()) {
                    builder.append(", ");
                }
            } else {
                queue.offer(pop);
            }
        }
        builder.append(">");
        System.out.println(builder);
    }

}