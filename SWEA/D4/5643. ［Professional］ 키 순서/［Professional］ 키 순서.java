import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder builder = new StringBuilder();
		for (int testCase = 1; testCase <= T; testCase++) {
			StringTokenizer st;
			int N = Integer.parseInt(br.readLine());
			int E = Integer.parseInt(br.readLine());
			List<Integer>[] nodes = new List[N];
			for (int i = 0; i < N; i++) {
				nodes[i] = new ArrayList<>();
			}
			
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				nodes[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
			}
			
			int[] nums = new int[N];
			for (int i = 0; i < N; i++) {
				Queue<Integer> queue = new LinkedList<>();
				boolean[] visited = new boolean[N];
				visited[i] = true;
				nums[i] += 1;
				queue.offer(i);
				int count = -1;
				while(!queue.isEmpty()) {
					int pop = queue.poll();
					count++;
					
					for(int x = 0; x < nodes[pop].size(); x++) {
						int n = nodes[pop].get(x);
						if (!visited[n]) {
							visited[n] = true;
							nums[n]++;
							queue.offer(n);
						}
					}
				}
				nums[i] += count;
			}
			
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if (nums[i] == N) sum++;
			}
			builder.append("#").append(testCase).append(" ").append(sum).append("\n");
		}
		System.out.print(builder);
	}
}