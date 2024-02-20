import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class N2252Node {
    int num;
    List<Integer> nums;

    public N2252Node(int num) {
        this.num = num;
        this.nums = new ArrayList<>();
    }
}

public class Main {
    private static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            nums[i] = 1;
        }
        N2252Node[] nodes = new N2252Node[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            N2252Node node;
            if (nodes[n] == null) {
                nodes[n] = new N2252Node(n);
            }
            if (nodes[k] == null) {
                nodes[k] = new N2252Node(k);
            }

            nodes[n].nums.add(k);
            nums[k]++;

        }
        StringBuilder end = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (nodes[i] == null) {
                end.append(i).append(" ");
                nums[i] = 0;
            }
        }
        StringBuilder builder = new StringBuilder();

        while (!isZero()) {
            Queue<N2252Node> queue = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (nums[i] == 1) {
                    nums[i] = 0;
                    builder.append(i).append(" ");
                    queue.offer(nodes[i]);
                }
            }

            while (!queue.isEmpty()) {
                N2252Node pop = queue.poll();
                for (int popN : pop.nums) {
                    if (nums[popN] != 0) {
                        nums[popN] = Math.max(1, nums[popN] - 1);
                    }
                }
            }
        }
        builder.append(end);
        System.out.println(builder);
    }

    private static boolean isZero() {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) return false;
        }
        return true;
    }
}