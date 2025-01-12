import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());

        int[] nums = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            for (int x = 1; x < n; x++) {
                int end = Integer.parseInt(st.nextToken());

                graph.get(start).add(end);
                nums[end]++;
                start = end;
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (nums[i] == 0) {
                pq.add(i);
            }
        }

        StringBuilder builder = new StringBuilder();

        while (!pq.isEmpty()) {
            int pop = pq.poll();
            builder.append(pop).append("\n");

            for (int next : graph.get(pop)) {
                nums[next]--;
                if (nums[next] == 0) {
                    pq.add(next);
                }
            }
        }

        for (int i = 0; i <= N; i++) {
            if (nums[i] != 0) {
                System.out.println(0);
                System.exit(0);
            }
        }

        System.out.println(builder);
    }
}
