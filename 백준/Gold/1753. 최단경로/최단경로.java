import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());
        List<N1753Node>[] nodes = new List[V + 1];
        for (int i = 0; i <= V; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodes[s].add(new N1753Node(w, e));
        }

        int[] weight = new int[V + 1];
        Arrays.fill(weight, Integer.MAX_VALUE);
        weight[start] = 0;
        Queue<N1753Node> queue = new PriorityQueue<>();
        queue.offer(new N1753Node(0, start));
        while (!queue.isEmpty()) {
            N1753Node pop = queue.poll();

            if (weight[pop.end] != pop.weight) continue;
            for (N1753Node node : nodes[pop.end]) {
                if (weight[node.end] <= weight[pop.end] + node.weight) continue;
                weight[node.end] = weight[pop.end] + node.weight;
                queue.offer(new N1753Node(weight[node.end], node.end));
            }

        }
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= V; i++) {
            builder.append(weight[i] == Integer.MAX_VALUE ? "INF" : weight[i]).append("\n");
        }
        System.out.print(builder);
    }

    static class N1753Node implements Comparable<N1753Node> {
        int weight;
        int end;

        public N1753Node(int weight, int end) {
            this.weight = weight;
            this.end = end;
        }

        @Override
        public int compareTo(N1753Node other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
}