import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        boolean[] v = new boolean[100001];
        Queue<Object[]> queue = new LinkedList<>();
        queue.offer(new Object[]{start, 0, new ArrayList<>()});
        if (start >= end) {
            StringBuilder builder = new StringBuilder();
            for (int i = start; i >= end; i--) {
                builder.append(i).append(" ");
            }
            System.out.println(start - end);
            System.out.println(builder);
            System.exit(0);
        }

        while (!queue.isEmpty()) {
            Object[] pop = queue.poll();
            int s = (int) pop[0];
            int c = (int) pop[1];
            List<Integer> l = (ArrayList<Integer>) pop[2];
            if (v[s]) continue;

            if (s == end) {
                System.out.println(pop[1]);
                StringBuilder builder = new StringBuilder();
                ((ArrayList<Integer>) pop[2]).forEach(n -> builder.append(n).append(" "));
                builder.append(end).append("\n");
                System.out.print(builder);
                System.exit(0);
            }

            v[s] = true;

            if (s * 2 <= 100000) {
                List<Integer> copy = new ArrayList<>(l);
                copy.add(s);
                queue.offer(new Object[]{s * 2, c + 1, copy});
            }
            if (s + 1 <= 100000) {
                List<Integer> copy = new ArrayList<>(l);
                copy.add(s);
                queue.offer(new Object[]{s + 1, c + 1, copy});
            }
            if (s - 1 > 0) {
                List<Integer> copy = new ArrayList<>(l);
                copy.add(s);
                queue.offer(new Object[]{s - 1, c + 1, copy});
            }
        }
    }
}