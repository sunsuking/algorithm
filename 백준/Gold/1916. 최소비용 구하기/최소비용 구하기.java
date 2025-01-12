import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(node -> node.weight));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node pop = pq.poll();

            if (pop.weight > dp[pop.n]) continue;

            for (Node n : graph.get(pop.n)) {
                int distance = dp[pop.n] + n.weight;
                if (distance < dp[n.n]) {
                    dp[n.n] = distance;
                    pq.offer(new Node(n.n, pop.weight + n.weight));
                }
            }
        }

        System.out.println(dp[end]);
    }

    static class Node {
        int n;
        int weight;

        public Node(int n, int weight) {
            this.n = n;
            this.weight = weight;
        }
    }
}
