import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class CustomComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer o1, Integer o2) {
        int compare = Integer.compare(Math.abs(o1), Math.abs(o2));
        if (compare == 0) {
            return Integer.compare(o1, o2);
        }
        return compare;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        Queue<Integer> queue = new PriorityQueue<>(1, new CustomComparator());
        for (int i = 0; i < count; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                if (queue.isEmpty()) builder.append("0").append("\n");
                else builder.append(queue.poll()).append("\n");
            } else {
                queue.offer(num);
            }
        }
        System.out.println(builder);
    }
}