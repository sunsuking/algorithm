import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] nums = new int[num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{Integer.parseInt(st.nextToken()) - 1, 0});
        int end = Integer.parseInt(st.nextToken()) - 1;
        boolean[] visited = new boolean[num];
        visited[queue.peek()[0]] = true;

        while (!queue.isEmpty()) {
            int[] pop = queue.poll();
            if (pop[0] == end) {
                System.out.println(pop[1]);
                System.exit(0);
            }

            int n = nums[pop[0]];

            int newNum = pop[0];
            while (newNum - n >= 0) {
                newNum -= n;
                if (!visited[newNum]) {
                    visited[newNum] = true;
                    queue.offer(new int[]{newNum, pop[1] + 1});
                }
            }
            newNum = pop[0];
            while (newNum + n <= num - 1) {
                newNum += n;
                if (!visited[newNum]) {
                    visited[newNum] = true;
                    queue.offer(new int[]{newNum, pop[1] + 1});
                }
            }
        }
        System.out.println(-1);
    }
}