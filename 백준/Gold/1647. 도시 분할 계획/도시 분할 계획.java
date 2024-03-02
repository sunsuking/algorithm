import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        Queue<N1647Node> queue = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            queue.offer(new N1647Node(from, to, weight));
        }

        int sum = 0;
        int max = 0;
        while (!queue.isEmpty()) {
            N1647Node pop = queue.poll();
            if (merge(pop.from, pop.to)) {
                sum += pop.weight;
                max = Math.max(pop.weight, max);
            }
        }
        System.out.println(sum - max);
    }

    private static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merge(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX == parentY) return false;
        parent[parentY] = parentX;
        return true;
    }

    static class N1647Node implements Comparable<N1647Node> {
        int from;
        int to;
        int weight;

        public N1647Node(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(N1647Node other) {
            return this.weight - other.weight;
        }
    }
}