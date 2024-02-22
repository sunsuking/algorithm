import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class N3124Node implements Comparable<N3124Node> {
    int from;
    int to;
    int weight;

    public N3124Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(N3124Node other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class Solution {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            parent = new int[V + 1];
            for (int i = 1; i <= V; i++) {
                create(i);
            }

            Queue<N3124Node> queue = new PriorityQueue<>();
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                queue.offer(new N3124Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            long weight = 0;
            while (!queue.isEmpty()) {
                N3124Node pop = queue.poll();
                if (!merge(pop.from, pop.to)) continue;
                weight += pop.weight;
            }

            builder.append("#").append(testCase).append(" ").append(weight).append("\n");
        }
        System.out.print(builder);
    }

    private static void create(int x) {
        parent[x] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static boolean merge(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX == parentY) return false;
        parent[parentY] = parentX;
        return true;
    }
}