import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class N2412Node {
        int x;
        int y;

        public N2412Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        N2412Node[] nodes = new N2412Node[N + 1];
        int[] visited = new int[N + 1];
        nodes[0] = new N2412Node(0, 0);
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            nodes[i] = new N2412Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(nodes, (a1, a2) -> {
            int compare = Integer.compare(a1.y, a2.y);
            if (compare == 0) {
                return Integer.compare(a1.x, a2.x);
            }
            return compare;
        });

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = 0;
        while (!queue.isEmpty()) {
            int index = queue.poll();

            if (nodes[index].y == T) {
                System.out.println(visited[index]);
                System.exit(0);
            }

            int nowX = nodes[index].x;
            int nowY = nodes[index].y;

            for (int next = index + 1; next <= N; next++) {
                if (visited[next] != 0) continue;
                int nextX = nodes[next].x;
                int nextY = nodes[next].y;

                if (nextY - nowY > 2) break;
                if (Math.abs(nextX - nowX) > 2) continue;
                visited[next] = visited[index] + 1;
                queue.offer(next);
            }

            for (int next = index - 1; next > 0; next--) {
                if (visited[next] != 0) continue;

                int nextX = nodes[next].x;
                int nextY = nodes[next].y;

                if (nextY - nowY < -2) break;
                if (Math.abs(nextX - nowX) > 2) continue;
                visited[next] = visited[index] + 1;
                queue.offer(next);
            }
        }
        System.out.println(-1);
    }
}