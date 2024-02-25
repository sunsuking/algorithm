import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int minute = Integer.parseInt(br.readLine());

        ArrayDeque<N17952Work> stack = new ArrayDeque<>();

        StringTokenizer st;
        int score = 0;
        for (int m = 1; m <= minute; m++) {
            st = new StringTokenizer(br.readLine());
            if (Integer.parseInt(st.nextToken()) == 1) {
                stack.push(new N17952Work(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            if (!stack.isEmpty()) {
                N17952Work now = stack.peek();
                now.remain -= 1;
                if (now.remain == 0) {
                    score += now.score;
                    stack.pop();
                }
            }
        }
        System.out.println(score);
    }

    static class N17952Work {
        int score;
        int remain;

        public N17952Work(int score, int remain) {
            this.score = score;
            this.remain = remain;
        }
    }
}