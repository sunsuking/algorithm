import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        char[] chars = new StringBuilder(br.readLine()).reverse().toString().toCharArray();
        Deque<Integer> deque = new ArrayDeque<>();
        int number = 1;
        for (char action : chars) {
            switch (action) {
                case ' ':
                    continue;
                case '1':
                    deque.addFirst(number);
                    break;
                case '2':
                    int pop = deque.pop();
                    deque.addFirst(number);
                    deque.addFirst(pop);
                    break;
                case '3':
                    deque.addLast(number);
                    break;
            }
            number++;
        }

        StringBuilder builder = new StringBuilder();
        while (!deque.isEmpty()) builder.append(deque.pop()).append(" ");
        System.out.println(builder);
    }
}