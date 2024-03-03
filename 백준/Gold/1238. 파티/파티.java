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
        int end = Integer.parseInt(st.nextToken());
        List<N1238Node>[] nodes = new List[V + 1];
        for (int i = 1; i <= V; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            nodes[s].add(new N1238Node(w, e));
        }

        int[] weights = new int[V + 1];
        Arrays.fill(weights, Integer.MAX_VALUE);
        Queue<N1238Node> queue = new PriorityQueue<>();
        weights[end] = 0;
        queue.offer(new N1238Node(0, end));
        while (!queue.isEmpty()) {
            N1238Node pop = queue.poll();

            if (weights[pop.end] != pop.weight) continue;
            for (N1238Node node : nodes[pop.end]) {
                if (weights[node.end] <= weights[pop.end] + node.weight) continue;
                weights[node.end] = weights[pop.end] + node.weight;
                queue.offer(new N1238Node(weights[node.end], node.end));
            }
        }

        int max = Integer.MIN_VALUE;

        main:
        for (int start = 1; start <= V; start++) {
            if (start == end) continue;
            int[] newWeights = new int[V + 1];
            Arrays.fill(newWeights, Integer.MAX_VALUE);
            Queue<N1238Node> newQueue = new PriorityQueue<>();
            newWeights[start] = 0;
            newQueue.offer(new N1238Node(0, start));
            while (!newQueue.isEmpty()) {
                N1238Node pop = newQueue.poll();
                if (newWeights[pop.end] != pop.weight) continue;
                for (N1238Node node : nodes[pop.end]) {
                    if (newWeights[node.end] <= newWeights[pop.end] + node.weight) continue;
                    newWeights[node.end] = newWeights[pop.end] + node.weight;
                    newQueue.offer(new N1238Node(newWeights[node.end], node.end));
                }
            }
            max = Math.max(max, newWeights[end] + weights[start]);
        }
        System.out.println(max);
    }

    static class N1238Node implements Comparable<N1238Node> {
        int weight;
        int end;

        public N1238Node(int weight, int end) {
            this.weight = weight;
            this.end = end;
        }

        @Override
        public int compareTo(N1238Node other) {
            return this.weight - other.weight;
        }
    }
}