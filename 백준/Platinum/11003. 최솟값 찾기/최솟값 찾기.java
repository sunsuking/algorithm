import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        ArrayDeque<N11003Node> deque = new ArrayDeque<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            while (!deque.isEmpty() && deque.peekLast().num > num) {
                deque.removeLast();
            }
            deque.offer(new N11003Node(i, num));
            if (!deque.isEmpty() && deque.peekFirst().index <= i - L) {
                deque.removeFirst();
            }
            builder.append(deque.peekFirst().num).append(" ");
        }
        System.out.print(builder);

    }

    static class N11003Node {
        int index;
        int num;

        public N11003Node(int index, int num) {
            this.index = index;
            this.num = num;
        }
    }
}