import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        boolean[] v = new boolean[100001];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{start, 0});
        if (start >= end) {
            System.out.println(start - end);
            System.exit(0);
        }

        while (!queue.isEmpty()) {
            int[] pop = queue.poll();
            if (v[pop[0]]) continue;

            if (pop[0] == end) {
                System.out.println(pop[1]);
                System.exit(0);
            }

            v[pop[0]] = true;

            if (pop[0] * 2 <= 100000) {
                queue.offer(new int[]{pop[0] * 2, pop[1] + 1});
            }
            if (pop[0] + 1 <= 100000) {
                queue.offer(new int[]{pop[0] + 1, pop[1] + 1});
            }
            if (pop[0] - 1 > 0) {
                queue.offer(new int[]{pop[0] - 1, pop[1] + 1});
            }
        }
    }
}