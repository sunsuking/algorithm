import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[] dp = new int[n + 1];

            List<List<Node>> graph = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
                dp[i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                graph.get(b).add(new Node(a, s));
            }

            PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.weight));
            dp[c] = 0;
            pq.add(new Node(c, 0));

            while (!pq.isEmpty()) {
                Node pop = pq.poll();

                if (pop.weight > dp[pop.n]) continue;

                for (Node next : graph.get(pop.n)) {
                    int weight = dp[pop.n] + next.weight;
                    if (weight < dp[next.n]) {
                        dp[next.n] = weight;
                        pq.offer(new Node(next.n, weight));
                    }
                }
            }

            int count = 0;
            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (dp[i] != Integer.MAX_VALUE) {
                    count++;
                    max = Math.max(max, dp[i]);
                }
            }

            builder.append(count).append(" ").append(max).append("\n");
        }

        System.out.println(builder);
    }

    static class Node {
        int n;
        int weight;

        public Node (int n, int weight) {
            this.n = n;
            this.weight = weight;
        }
    }
}
