import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());


        if (start == end) {
            System.out.println(0);
            System.out.println(1);
            System.exit(0);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        int time = 0;
        int count = 0;
        int[] visited = new int[100_001];
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int ___x = 0; ___x < size; ___x++) {
                int pop = queue.poll();
                if (pop * 2 <= 100_000 && (visited[pop * 2] == 0 || visited[pop * 2] == visited[pop] + 1)) {
                    if (pop * 2 == end) {
                        count++;
                    } else {
                        visited[pop * 2] = time;
                        queue.offer(pop * 2);
                    }
                }
                if (pop + 1 <= 100_000 && (visited[pop + 1] == 0 || visited[pop + 1] == visited[pop] + 1)) {
                    if (pop + 1 == end) {
                        count++;
                    } else {
                        visited[pop + 1] = time;
                        queue.offer(pop + 1);
                    }
                }
                if (pop - 1 >= 0 && (visited[pop - 1] == 0 || visited[pop - 1] == visited[pop] + 1)) {
                    if (pop - 1 == end) {
                        count++;
                    } else {
                        visited[pop - 1] = time;
                        queue.offer(pop - 1);
                    }
                }
            }

            if (count > 0) {
                break;
            }
        }
        System.out.println(time);
        System.out.println(count);
    }
}