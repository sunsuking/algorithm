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
        List<Integer> temp = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            nums[i] = 1;
            temp.add(i);
        }
        Map<Integer, N2252Node> map = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            N2252Node node;
            if (map.containsKey(n)) {
                node = map.get(n);
            } else {
                node = new N2252Node(n);
                map.put(n, node);
            }
            if (!map.containsKey(k)) {
                map.put(k, new N2252Node(k));
            }
            node.nums.add(k);
            nums[k]++;
            temp.remove(new Integer(n));
            temp.remove(new Integer(k));
        }

        StringBuilder builder = new StringBuilder();
        for (int n : temp) {
            nums[n] = 0;
        }

        while (!isZero()) {
            Queue<N2252Node> queue = new LinkedList<>();
            for (int i = 1; i <= N; i++) {
                if (nums[i] == 1 && map.containsKey(i)) {
                    nums[i] = 0;
                    builder.append(i).append(" ");
                    queue.offer(map.get(i));
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
        for (int n : temp) {
            builder.append(n).append(" ");
        }
        System.out.println(builder);
    }

    private static boolean isZero() {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) return false;
        }
        return true;
    }
}