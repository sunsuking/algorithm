import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N + 1];
        int[] weights = new int[N + 1];
        int[] dp = new int[N + 1];
        List<List<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            nodes.add(new ArrayList<>());
        }

        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            weights[i] = weight;
            int fin = Integer.parseInt(st.nextToken());
            while (fin != -1) {
                nums[i]++;
                nodes.get(fin).add(i);
                fin = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (nums[i] == 0) {
                pq.add(i);
                dp[i] = weights[i];
            }
        }

        while (!pq.isEmpty()) {
            int pop = pq.poll();

            for (int next : nodes.get(pop)) {
                nums[next]--;

                dp[next] = Math.max(dp[next], dp[pop] + weights[next]);
                if (nums[next] == 0) {
                    pq.offer(next);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            builder.append(dp[i]).append("\n");
        }
        System.out.println(builder);
    }
}
