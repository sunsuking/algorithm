import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static Map<Integer, Integer> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] bam = new int[100];
        int[] visited = new int[100];
        for (int i = 0; i < 100; i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            bam[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {1, 0});
        while(!queue.isEmpty()) {
            int[] nums = queue.poll();
            if (nums[0] > 100) continue;
            if (nums[0] == 100) {
                System.out.println(nums[1]);
                System.exit(0);
            }
            if (bam[nums[0]] != 0) {
                nums[0] = bam[nums[0]];
            }
            visited[nums[0]] = Math.min(visited[nums[0]], nums[1]);

            if (visited[nums[0]] == nums[1]) {
                queue.offer(new int[]{ nums[0] + 1, nums[1] + 1 });
                queue.offer(new int[]{ nums[0] + 2, nums[1] + 1 });
                queue.offer(new int[]{ nums[0] + 3, nums[1] + 1 });
                queue.offer(new int[]{ nums[0] + 4, nums[1] + 1 });
                queue.offer(new int[]{ nums[0] + 5, nums[1] + 1 });
                queue.offer(new int[]{ nums[0] + 6, nums[1] + 1 });
            }
        }
    }
}
