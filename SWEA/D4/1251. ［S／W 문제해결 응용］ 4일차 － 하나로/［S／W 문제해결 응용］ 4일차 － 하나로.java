import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class N1251Node implements Comparable<N1251Node> {
    int from;
    int to;
    double resource;

    public N1251Node(int from, int to, double resource) {
        this.from = from;
        this.to = to;
        this.resource = resource;
    }

    @Override
    public int compareTo(N1251Node other) {
        return Double.compare(this.resource, other.resource);
    }
}

public class Solution {
    private static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int num = Integer.parseInt(br.readLine());
            parent = new int[num];

            int[][] position = new int[num][2];

            // position X, Y 값 및 환경 부담 세율 입력 받기
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < num; i++) {
                create(i);
                position[i][0] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < num; i++) {
                position[i][1] = Integer.parseInt(st.nextToken());
            }
            double E = Double.parseDouble(br.readLine());
            
            
            Queue<N1251Node> queue = new PriorityQueue<>();
            for (int x = 0; x < num; x++) {
                for (int y = 0; y < num; y++) {
                    if (x == y) continue;
                    double distancePow = Math.pow(position[x][0] - position[y][0], 2) + Math.pow(position[x][1] - position[y][1], 2);
                    queue.offer(new N1251Node(x, y, distancePow * E));
                }
            }

            double sum = 0;
            while (!queue.isEmpty()) {
                N1251Node pop = queue.poll();

                if (!merge(pop.from, pop.to)) continue;
                sum += pop.resource;
            }

            builder.append("#").append(testCase).append(" ").append(Math.round(sum)).append("\n");
        }

        System.out.print(builder);
    }

    private static void create(int x) {
        parent[x] = x;
    }

    private static int find(int x) {
        if (x == parent[x]) return x;
        else return parent[x] = find(parent[x]);
    }

    private static boolean merge(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        if (parentX == parentY) return false;
        parent[parentY] = parentX;
        return true;
    }
}