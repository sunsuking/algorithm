import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder builder = new StringBuilder();
        for (int testCase = 1; testCase <= T; testCase++) {
            int num = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            int startX = Integer.parseInt(st.nextToken());
            int startY = Integer.parseInt(st.nextToken());
            Node[] nodes = new Node[num];
            for (int x = 0; x < num; x++) {
                st = new StringTokenizer(br.readLine());
                nodes[x] = new Node(x + 1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            st = new StringTokenizer(br.readLine());
            Node end = new Node(-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{startX, startY, 0});
            boolean[][] visited = new boolean[num + 1][num + 1];
            String str = "sad";
            while (!queue.isEmpty()) {
                int[] pop = queue.poll();

                if (end.distance(pop[0], pop[1]) <= 1000) {
                    str = "happy";
                    break;
                }

                Arrays.sort(nodes, Comparator.comparingInt(other -> other.distance(pop[0], pop[1])));
                for (Node node : nodes) {
                    if (node.distance(pop[0], pop[1]) <= 1000 && !visited[pop[2]][node.index]) {
                        visited[pop[2]][node.index] = true;
                        queue.offer(new int[] {node.x, node.y, node.index});
                    }
                }
            }
            builder.append(str).append("\n");
        }
        System.out.print(builder);
    }

    static class Node {
        int index;
        int x;
        int y;

        public Node(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
        }

        public int distance(int x, int y) {
            return Math.abs(x - this.x) + Math.abs(y - this.y);
        }
    }
}