import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 10; i++) {
            int num = Integer.parseInt(br.readLine());
            Queue<Integer> queue = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < 8; x++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }

            int minus = 1;
            while(true) {
                int number = queue.poll();
                number -= minus++;

                if (minus == 6) {
                    minus = 1;
                }

                number = Math.max(number, 0);
                queue.offer(number);

                if (number == 0) break;
            }

            builder.append("#").append(num).append(" ");
            queue.forEach(n -> builder.append(n).append(" "));
            builder.append("\n");
        }
        System.out.println(builder);
    }
}