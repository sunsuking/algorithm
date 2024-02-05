import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();
        int num = Integer.parseInt(br.readLine());
        int[] nums = new int[num];
        int[] answer = new int[num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < num; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = num - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) answer[stack.pop()] = i + 1;
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i : answer) sb.append(i + " ");
        System.out.println(sb);
    }
}