import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class N1197Node implements Comparable<N1197Node> {
    int from;
    int to;
    int weight;

    public N1197Node(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(N1197Node other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class Main {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            create(i);
        }

        Queue<N1197Node> queue = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            queue.offer(new N1197Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int weight = 0;
        while (!queue.isEmpty()) {
            N1197Node pop = queue.poll();
            if (!merge(pop.from, pop.to)) continue;
            weight += pop.weight;

        }
        System.out.println(weight);
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